package org.mech.terminator.swing;

import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;

import org.mech.terminator.Terminal;
import org.mech.terminator.TerminalAppearance;
import org.mech.terminator.TerminalCharacter;
import org.mech.terminator.TerminalSize;

public class TerminalPanel extends JPanel implements ComponentListener {

    private static final long serialVersionUID = 1L;

    private boolean squareTerminal = false;

    public TerminalPanel(TerminalSize size) {
        this(new Terminal(size));
    }

    public TerminalPanel() {
        this(new Terminal());
    }

    private TerminalPanel(Terminal terminal) {
        super();
        Terminal.setInstance(terminal);
        setFocusTraversalKeysEnabled(false);
        setFocusable(true);

        addKeyListener(new KeyInputListener());
        addComponentListener(this);
    }

    private int getCharWidth() {
        return squareTerminal ? getCharHeight() : getGraphics().getFontMetrics(TerminalAppearance.DEFAULT_NORMAL_FONT).charWidth(' ');
    }

    private int getCharHeight() {
        final FontMetrics fontMetrics = getGraphics().getFontMetrics(TerminalAppearance.DEFAULT_NORMAL_FONT);
        return fontMetrics.getHeight();

    }

    @Override
    protected void paintComponent(final Graphics g) {
        super.paintComponent(g);
        synchronized (this) {
            final Graphics2D graphics2D = (Graphics2D) g.create();

            graphics2D.setFont(TerminalAppearance.DEFAULT_NORMAL_FONT);
            graphics2D.setColor(TerminalAppearance.DEFAULT_BG_COLOR);
            graphics2D.fillRect(0, 0, getWidth(), getHeight());

            final int charWidth = getCharWidth();
            final int charHeight = getCharHeight();

            try {
                final TerminalCharacter[][] data = getTerminal().retrieveLock();
                for (int line = 0; line < getLines(); line++) {
                    for (int col = 0; col < getColumns(); col++) {
                        final TerminalCharacter character = data[line][col];
                        boolean needToResetFont = false;

                        final int charStartX = col * charWidth;

                        graphics2D.setColor(TerminalAppearance.DEFAULT_FG_COLOR);

                        if (character.getBg() != null) {
                            graphics2D.setColor(character.getBg());
                            graphics2D.fillRect(charStartX, line * charHeight, charWidth, charHeight);
                            graphics2D.setColor(TerminalAppearance.DEFAULT_FG_COLOR);
                        }

                        if (character.getFg() != null) {
                            graphics2D.setColor(character.getFg());
                        }

                        if (character.isBold()) {
                            graphics2D.setFont(TerminalAppearance.DEFAULT_BOLD_FONT);
                            needToResetFont = true;
                        }

                        final FontMetrics fontMetrics = getGraphics().getFontMetrics(TerminalAppearance.DEFAULT_NORMAL_FONT);
                        final int startY = ((line + 1) * charHeight) - fontMetrics.getDescent();
                        final int startX = charStartX + ((charWidth - fontMetrics.charWidth(character.get())) / 2);
                        graphics2D.drawString(character.toString(), startX, startY);

                        if (needToResetFont) {
                            graphics2D.setFont(TerminalAppearance.DEFAULT_NORMAL_FONT);
                        }

                    }

                }
                graphics2D.dispose();
            } finally {
                getTerminal().releaseLock();
            }
        }
    }

    public int getLines() {
        return getTerminal().getSize().getLines();
    }

    public int getColumns() {
        return getTerminal().getSize().getColumns();
    }

    @Override
    public String toString() {
        return "[" + getLines() + "," + getColumns() + "]";
    }

    public Terminal getTerminal() {
        return Terminal.getInstance();
    }

    private class KeyInputListener extends KeyAdapter implements KeyListener {

        @Override
        public void keyPressed(final KeyEvent e) {
            TerminalPanel.this.dispatchInput(e);
            TerminalPanel.this.repaint();
        }
    }

    protected void dispatchInput(final KeyEvent e) {
    }

    @Override
    public void componentHidden(final ComponentEvent e) {
    }

    @Override
    public void componentMoved(final ComponentEvent e) {
    }

    @Override
    public void componentResized(final ComponentEvent e) {
        refreshTerminalSize();
    }

    @Override
    public void componentShown(final ComponentEvent e) {
        System.out.println("componentShown");
    }

    public boolean isSquareTerminal() {
        return squareTerminal;
    }

    protected void refreshTerminalSize() {
        getTerminal().releaseLock();

        final Dimension size = getSize();
        final int w = (int) (size.getWidth() / getCharWidth());
        final int h = (int) (size.getHeight() / getCharHeight());
        final TerminalSize terminalSize = new TerminalSize(h, w);
        getTerminal().setSize(terminalSize);
    }

    public void setSquareTerminal(final boolean squareTerminal) {
        this.squareTerminal = squareTerminal;
    }

}
