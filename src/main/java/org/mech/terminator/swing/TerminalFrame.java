package org.mech.terminator.swing;

import org.mech.terminator.Terminal;

import javax.swing.*;
import java.awt.*;

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

    public static void main(String[] args) {
        TerminalFrame terminalFrame = new TerminalFrame();
        terminalFrame.setPreferredSize(new Dimension(400, 200));
        terminalFrame.pack();
        terminalFrame.setVisible(true);
        composeExample();

    }

    private static void composeExample() {
        String test = "Hello World!";

        Color[] colors = buildColorList();
        final Terminal instance = Terminal.getInstance();
        int lineIndex = 0;
        int colorIndex = 0;
        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            instance.put(c, lineIndex, i);
            if (c == ' ') {
                colorIndex++;
                if (colorIndex == colors.length) {
                    colorIndex = 0;
                }
            } else {
                instance.bg(colors[colorIndex], lineIndex, i);
            }
        }
        colorIndex++;
        lineIndex++;
        lineIndex++;

        test = "This is SwingTerminator";
        for (int i = 0; i < test.length(); i++) {
            char c = test.charAt(i);
            instance.put(c, lineIndex, i);
            if (c == ' ') {
                colorIndex++;
                if (colorIndex == colors.length) {
                    colorIndex = 0;
                }
            } else {
                instance.fg(colors[colorIndex], lineIndex, i);
            }
        }
    }

    private static Color[] buildColorList() {
        return new Color[]{Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.ORANGE};
    }
}
