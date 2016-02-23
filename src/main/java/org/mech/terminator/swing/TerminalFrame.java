package org.mech.terminator.swing;

import org.mech.terminator.Terminal;
import org.mech.terminator.TerminalSize;
import org.mech.terminator.command.NativeWrapper;
import org.mech.terminator.command.PrintLine;
import org.mech.terminator.geometry.Position;

import javax.swing.*;
import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.CharArrayReader;
import java.io.IOException;

public class TerminalFrame extends JFrame {
	private static final long serialVersionUID = -4607047642126274067L;

	private TerminalPanel terminalPanel;

	public TerminalFrame() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
	}

	public TerminalPanel getTerminalPanel() {
		return terminalPanel;
	}

	public void setTerminalPanel(final TerminalPanel terminalPanel) {
		this.terminalPanel = terminalPanel;
		setContentPane(terminalPanel);
	}

    public static void main(String[] args) throws IOException {
        final TerminalFrame terminalFrame = new TerminalFrame();
        terminalFrame.setTerminalPanel(new TerminalPanel(new TerminalSize(40,150)));
        terminalFrame.setPreferredSize(new Dimension(800, 600));
        terminalFrame.pack();
        terminalFrame.setVisible(true);
        Terminal instance = Terminal.getInstance();

        final PrintLine printLine = new PrintLine(instance);
        printLine.println("Hello World!");
        String line = "This is SwingTerminator This is SwingTerminatorThis is SwingTerminator This is " +
                "SwingTerminator This is SwingTerminator This is SwingTerminator";
        printLine.println(line);
        printLine.println("line length: " + line.length());
        printLine.flush();
        printLine.println();
        printLine.flush();

        // windows
        final NativeWrapper lsWrapper = new NativeWrapper("cmd /c dir", instance);
        // linux
        // NativeWrapper lsWrapper = new NativeWrapper("ls", instance);
        Position position = printLine.getPosition().addY(1);
        lsWrapper.setPosition(position);
        lsWrapper.flush();

        printLine.setPosition(lsWrapper.getPosition().addY(1));
        printLine.print("TYPE HERE> ");
        printLine.flush();

        KeyAdapter keyAdapter =  new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if(!Character.isLetterOrDigit(e.getKeyChar()) && !Character.isWhitespace(e.getKeyChar())){
                    return;
                }

                printLine.print(Character.toString(e.getKeyChar()));
                try {
                    printLine.flush();
                } catch (IOException ioe) {
                    ioe.printStackTrace();
                }
            }
        };
        terminalFrame.getTerminalPanel().addKeyListener(keyAdapter);

    }
}
