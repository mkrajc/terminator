package org.mech.terminator.swing;

import static org.mech.terminator.TerminalAppearance.*;
import java.awt.Dimension;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import javax.swing.JPanel;
import org.mech.terminator.Terminal;
import org.mech.terminator.TerminalAppearance;
import org.mech.terminator.TerminalCharacter;
import org.mech.terminator.component.Component;
import org.mech.terminator.screen.Screen;
import org.mech.terminator.screen.ScreenManager;

public class TerminalPanel extends JPanel implements ComponentListener {

	public static final String DEFAULT = "default";
	private static final long serialVersionUID = 1L;

	private Terminal terminal = Terminal.INSTANCE;

	private ScreenManager screenManager = new ScreenManager();

	public TerminalPanel() {
		// setIgnoreRepaint(true);
		setFocusTraversalKeysEnabled(false);
		setFocusable(true);

		addKeyListener(new KeyInputListener(this));
		addComponentListener(this);
	}

	@Override
	public Dimension getPreferredSize() {
		final int screenWidth = getColumns() * getCharWidth();
		final int screenHeight = getLines() * getCharHeight();
		return new Dimension(screenWidth, screenHeight);
	}

	public Screen getDefaultScreen() {
		return getScreen(DEFAULT);
	}

	public Screen getScreen(String screenId) {
		Screen screen = screenManager.getScreen(screenId);
		if (screen == null) {
			screen = screenManager.createScreen(screenId);
		}
		return screen;
	}

	private int getCharWidth() {
		final FontMetrics fontMetrics = getGraphics().getFontMetrics(DEFAULT_NORMAL_FONT);
		return fontMetrics.charWidth(' ');
//				return getCharHeight();
	}

	private int getCharHeight() {
		final FontMetrics fontMetrics = getGraphics().getFontMetrics(DEFAULT_NORMAL_FONT);
		return fontMetrics.getHeight();
		
	}

	@Override
	protected void paintComponent(Graphics g) {
		screenManager.repaint();
		super.paintComponent(g);
		synchronized (this) {
			final Graphics2D graphics2D = (Graphics2D) g.create();

			graphics2D.setFont(DEFAULT_NORMAL_FONT);
			graphics2D.setColor(DEFAULT_BG_COLOR);
			graphics2D.fillRect(0, 0, getWidth(), getHeight());

			final int charWidth = getCharWidth();
			final int charHeight = getCharHeight();

			try {
				final TerminalCharacter[][] data = terminal.retrieveLock();
				for (int line = 0; line < getLines(); line++) {
					for (int col = 0; col < getColumns(); col++) {
						final TerminalCharacter character = data[line][col];
						boolean needToResetFont = false;

						int charStartX = col * charWidth;

						graphics2D.setColor(DEFAULT_FG_COLOR);

						if (character.getBg() != null) {
							graphics2D.setColor(character.getBg());
							graphics2D.fillRect(charStartX, line * charHeight, charWidth, charHeight);
							graphics2D.setColor(DEFAULT_FG_COLOR);
						}

						if (character.getFg() != null) {
							graphics2D.setColor(character.getFg());
						}

						if (character.isBold()) {
							graphics2D.setFont(TerminalAppearance.DEFAULT_BOLD_FONT);
							needToResetFont = true;
						}

						graphics2D.drawString(character.toString(), charStartX, ((line + 1) * charHeight)
								- getGraphics().getFontMetrics(DEFAULT_NORMAL_FONT).getDescent());

						if (needToResetFont) {
							graphics2D.setFont(TerminalAppearance.DEFAULT_NORMAL_FONT);
						}

					}

				}
				graphics2D.dispose();
			} finally {
				terminal.releaseLock();
			}
		}
	}

	public int getLines() {
		return terminal.getSize().getLines();
	}

	public int getColumns() {
		return terminal.getSize().getColumns();
	}

	@Override
	public String toString() {
		return "[" + getLines() + "," + getColumns() + "]";
	}

	public Terminal getTerminal() {
		return terminal;
	}

	public void setTerminal(Terminal terminal) {
		this.terminal = terminal;
	}

	private static class KeyInputListener implements KeyListener {
		private TerminalPanel owner;

		public KeyInputListener(TerminalPanel terminalPanel) {
			this.owner = terminalPanel;
		}

		@Override
		public void keyPressed(KeyEvent e) {
			if (Component.focused != null) {
				Component.focused.dispatchInput(e);
				owner.repaint();
			}
		}

		@Override
		public void keyReleased(KeyEvent e) {

		}

		@Override
		public void keyTyped(KeyEvent e) {}

	}

	@Override
	public void componentHidden(ComponentEvent e) {}

	@Override
	public void componentMoved(ComponentEvent e) {}

	@Override
	public void componentResized(ComponentEvent e) {}

	@Override
	public void componentShown(ComponentEvent e) {}

}
