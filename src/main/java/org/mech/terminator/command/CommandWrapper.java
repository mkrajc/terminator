package org.mech.terminator.command;

import org.mech.terminator.Terminal;
import org.mech.terminator.geometry.Position;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
* Created by alberto on 23/11/14.
*/
public class CommandWrapper implements AbstractCommandWrapper {
    private AbstractPrintLines printOddLines;
    private AbstractPrintLines printPairLines;
    private String text;
    private Terminal instance;
    private final Color[] colors;
    private int colorIndex;
    private Position pos;

    public CommandWrapper(Terminal instance) {
        this.instance = instance;
        colors = buildColorList();
        printOddLines = new AbstractPrintLines(this) {
            @Override
            protected void changeAppeareance(int lineIndex, int col) {
                instance.fg(getColor(), lineIndex, col);
            }
        };

        printPairLines = new AbstractPrintLines(this) {
            @Override
            protected void changeAppeareance(int lineIndex, int col) {
                instance.bg(getColor(), lineIndex, col);
            }
        };
        colorIndex = 0;
    }

    @Override
    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        if (pos == null) {
            pos = new Position(0,0);
        }
        return pos;
    }

    @Override
    public void flush() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new StringReader(text));
        String line;
        int lineReaded = 0;
        int lineIndex = getPosition().y;
        while ((line = bufferedReader.readLine()) != null) {
            if (lineReaded % 2 == 0) {
                printPairLines.printLine(line, lineIndex);
            } else {
                printOddLines.printLine(line, lineIndex);
            }
            lineIndex++;
            lineIndex++;

            lineReaded++;
        }
    }

    public void incColor() {
        colorIndex++;
        if (colorIndex == colors.length) {
            colorIndex = 0;
        }
    }

    private Color getColor() {
        return colors[colorIndex];
    }

    private Color[] buildColorList() {
        return new Color[]{Color.MAGENTA, Color.CYAN, Color.YELLOW, Color.ORANGE};
    }

    public void setText(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public Terminal getInstance() {
        return instance;
    }

    public void setInstance(Terminal instance) {
        this.instance = instance;
    }
}
