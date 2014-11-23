package org.mech.terminator.command;

import org.mech.terminator.Terminal;

import java.awt.*;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.StringReader;

/**
* Created by alberto on 23/11/14.
*/
public class CommandWrapper {
    private String text;
    private Terminal instance;
    private final Color[] colors;
    private int colorIndex;

    public CommandWrapper(Terminal instance) {
        this.instance = instance;
        colors = buildColorList();
    }

    public void flush() throws IOException {

        BufferedReader bufferedReader = new BufferedReader(new StringReader(text));
        String line;
        int lineReaded = 0;
        colorIndex = 0;
        int lineIndex = 0;
        while ((line = bufferedReader.readLine()) != null) {
            if (lineReaded % 2 == 0) {
                printPairLines(line, lineIndex);
            } else {
                printOddLines(line, lineIndex);
            }
            colorIndex++;
            lineIndex++;
            lineIndex++;

            lineReaded++;
        }
    }

    private void printOddLines(String line, int lineIndex) {
        boolean doBold = true;
        for (int col = 0; col < line.length(); col++) {
            char c = line.charAt(col);
            instance.put(c, lineIndex, col);
            if (c == ' ') {
                colorIndex++;
                if (colorIndex == colors.length) {
                    colorIndex = 0;
                }
                doBold=true;
            } else {
                instance.fg(colors[colorIndex], lineIndex, col);
                if (doBold) {
                    instance.bold(lineIndex, col);
                    doBold = false;
                }
            }
        }
    }

    private void printPairLines(String line, int lineIndex) {
        boolean doBold = true;
        for (int col = 0; col < line.length(); col++) {
            char c = line.charAt(col);
            instance.put(c, lineIndex, col);
            if (c == ' ') {
                colorIndex++;
                if (colorIndex == colors.length) {
                    colorIndex = 0;
                }
                doBold=true;
            } else {
                instance.bg(colors[colorIndex], lineIndex, col);
                if (doBold) {
                    instance.bold(lineIndex, col);
                    doBold = false;
                }
            }
        }
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
}
