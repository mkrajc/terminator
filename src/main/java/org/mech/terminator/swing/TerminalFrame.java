package org.mech.terminator.swing;

import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class TerminalFrame extends JFrame {
	private static final long serialVersionUID = -4607047642126274067L;

	private TerminalPanel terminalPanel;

	public void showWindow() {
		pack();
		setVisible(true);
		
	}

	public TerminalFrame() {
		setTerminalPanel(new TerminalPanel());
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//		setIgnoreRepaint(true);
		
	}

	public TerminalPanel getTerminalPanel() {
		return terminalPanel;
	}

	public void setTerminalPanel(final TerminalPanel terminalPanel) {
		this.terminalPanel = terminalPanel;
		setContentPane(terminalPanel);
	}
}
