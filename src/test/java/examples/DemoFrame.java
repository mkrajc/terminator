package examples;


import java.awt.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.io.IOException;

import org.mech.terminator.Terminal;
import org.mech.terminator.TerminalSize;
import org.mech.terminator.command.Native;
import org.mech.terminator.command.Print;
import org.mech.terminator.swing.TerminalFrame;
import org.mech.terminator.swing.TerminalPanel;

public class DemoFrame {
    public static void main(String[] args) throws IOException {
        final TerminalFrame terminalFrame = new TerminalFrame();
        terminalFrame.setTitle("Terminator");
        terminalFrame.setTerminalPanel(new TerminalPanel(new TerminalSize(40, 150)));
        terminalFrame.setPreferredSize(new Dimension(800, 600));
        terminalFrame.pack();
        terminalFrame.setVisible(true);

        Terminal instance = Terminal.getInstance();

        final Print printLine = new Print(instance);
        printLine.bold();
        printLine.setColor(Color.RED);
        printLine.println("This is SwingTerminator");
        printLine.normal();
        printLine.defaultColor();

        final Native cmd = new Native(instance);
        cmd.setPosition(printLine.getPosition().addY(1));

        // windows dir command
        cmd.execute("cmd /c dir");

        printLine.setPosition(cmd.getPosition().addY(1));
        printLine.print("TYPE HERE> ");

        final KeyAdapter keyAdapter = new KeyAdapter() {
            @Override
            public void keyTyped(KeyEvent e) {
                if (!Character.isLetterOrDigit(e.getKeyChar()) && !Character.isWhitespace(e.getKeyChar())) {
                    return;
                }
                printLine.print(Character.toString(e.getKeyChar()));

            }
        };
        terminalFrame.getTerminalPanel().addKeyListener(keyAdapter);

    }
}
