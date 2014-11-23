package org.mech.terminator.command;

import org.mech.terminator.Terminal;
import org.mech.terminator.geometry.Position;

/**
 * Created by alberto on 23/11/14.
 */
public abstract class AbstractPrintLines {

    protected CommandWrapper wrapper;
    protected Terminal instance;

    protected AbstractPrintLines(CommandWrapper wrapper) {
        this.wrapper = wrapper;
        instance = wrapper.getInstance();
    }

    public void printLine(String line, int lineIndex) {
        boolean doBold = true;
        for (int col = 0; col < line.length(); col++) {
            char c = line.charAt(col);
            instance.put(c, lineIndex, col);
            wrapper.setPosition(new Position(col, lineIndex));
            if (c == ' ') {
                wrapper.incColor();
                doBold=true;
            } else {
                changeAppeareance(lineIndex, col);
                if (doBold) {
                    instance.bold(lineIndex, col);
                    doBold = false;
                }
            }
        }
    }

    protected abstract void changeAppeareance(int lineIndex, int col);
}
