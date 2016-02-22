package org.mech.terminator.swing;

import org.mech.terminator.Terminal;
import org.mech.terminator.command.NativeWrapper;
import org.mech.terminator.command.PrintLine;
import org.mech.terminator.geometry.Position;

import javax.swing.*;
import java.awt.*;
import java.io.IOException;

public class TerminalFrame extends JFrame {
	private static final long serialVersionUID = -4607047642126274067L;

	private TerminalPanel terminalPanel;

	public TerminalFrame() {
		setTerminalPanel(new TerminalPanel());
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
        terminalFrame.setPreferredSize(new Dimension(800, 600));
        terminalFrame.pack();
        terminalFrame.setVisible(true);
        Terminal instance = Terminal.getInstance();

        final PrintLine printLine = new PrintLine(instance);
        printLine.println("Hello World!");
        printLine.println("This is SwingTerminator This is SwingTerminatorThis is SwingTerminator This is " +
                "SwingTerminator This is SwingTerminator This is SwingTerminator");
        printLine.println();

        // windows
        NativeWrapper lsWrapper = new NativeWrapper("cmd /c dir", instance);
        // linux
        // NativeWrapper lsWrapper = new NativeWrapper("ls", instance);
        Position position = lsWrapper.getPosition();
        position = position.addY(2);
        lsWrapper.setPosition(position);

        terminalFrame.repaint();
        printLine.flush();
        lsWrapper.flush();
    }
}
