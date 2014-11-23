package org.mech.terminator.swing;

import org.mech.terminator.Terminal;
import org.mech.terminator.command.CommandWrapper;

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
        TerminalFrame terminalFrame = new TerminalFrame();
        terminalFrame.setPreferredSize(new Dimension(400, 200));
        terminalFrame.pack();
        terminalFrame.setVisible(true);
        CommandWrapper commandWrapper = new CommandWrapper(Terminal.getInstance());
        commandWrapper.setText("Hello World!\nThis is SwingTerminator");
        commandWrapper.flush();
    }
}
