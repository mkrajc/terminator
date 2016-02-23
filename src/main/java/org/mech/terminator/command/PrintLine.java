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
        for (int i = 0; i < sb.length(); i++) {
            int lineIndex = getPosition().y;
            char c = sb.charAt(i);
            if (c == '\n' || c == '\r') {
                moveNextLine();
            } else {
                getTerminal().put(c, lineIndex, getPosition().x);
                moveNextColumn();
            }
        }
        sb.setLength(0);
    }

}