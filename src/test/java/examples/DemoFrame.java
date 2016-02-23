package examples;


import java.awt.Color;
import java.awt.Dimension;
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
        terminalFrame.setPreferredSize(new Dimension(400, 300));
        terminalFrame.pack();
        terminalFrame.setVisible(true);

        Terminal instance = Terminal.getInstance();

        final Print printLine = new Print(instance);
        printLine.bold();
        printLine.color(Color.RED);
        printLine.println("This is SwingTerminator");
        printLine.println();
        printLine.normal();
        printLine.defaultColor();

        printLine.background(Color.GREEN);
        printLine.print("It ");
        printLine.background(Color.BLUE);
        printLine.color(Color.YELLOW);
        printLine.print("supports");
        printLine.background(Color.CYAN);
        printLine.defaultColor();
        printLine.println(" bold,fg,bg");
        printLine.defaultBackground();


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
