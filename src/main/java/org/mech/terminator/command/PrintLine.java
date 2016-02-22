package org.mech.terminator.command;


import org.mech.terminator.ITerminal;

import java.io.IOException;

public class PrintLine extends CommandWrapper {

    private StringBuilder sb = new StringBuilder();

    public PrintLine(ITerminal instance) {
        super(instance);
    }

    public void print(String string) {
        sb.append(string);
    }

    public void println(String string) {
        print(string);
        println();
    }

    public void println() {
        sb.append("\n");
    }

    @Override
    public void flush() throws IOException {

        int lineIndex = getPosition().y;
        String[] lines = sb.toString().split("\n");
        for (String s : lines) {
            for (int col = 0; col < s.length(); col++) {
                char c = s.charAt(col);
                getTerminal().put(c, lineIndex, col);
            }
            lineIndex++;
        }
    }
}
