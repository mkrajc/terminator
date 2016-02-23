package org.mech.terminator.command;


import java.awt.Color;

import org.mech.terminator.ITerminal;

public class Print extends Command {

    private StringBuilder sb = new StringBuilder();
    private boolean bold;
    private Color color;
    private Color bgColor;

    public Print(ITerminal instance) {
        super(instance);
    }

    public void print(String string) {
        sb.append(string);
        flush();
    }

    public void println(String string) {
        print(string);
        println();
    }

    public void println() {
        sb.append("\n");
        flush();
    }

    private void flush() {
        for (int i = 0; i < sb.length(); i++) {
            int lineIndex = getPosition().y;
            char c = sb.charAt(i);
            if (c == '\n' || c == '\r') {
                moveNextLine();
            } else {
                getTerminal().put(c, lineIndex, getPosition().x);
                if (bold) {
                    getTerminal().bold(lineIndex, getPosition().x);
                }
                if (color != null) {
                    getTerminal().fg(color, lineIndex, getPosition().x);
                }
                if (bgColor != null) {
                    getTerminal().bg(bgColor, lineIndex, getPosition().x);
                }
                moveNextColumn();
            }
        }
        sb.setLength(0);
    }

    public void bold() {
        this.bold = true;
    }

    public void normal() {
        this.bold = false;
    }

    public void color(Color color) {
        this.color = color;
    }

    public void background(Color color) {
        this.bgColor = color;
    }

    public void defaultColor() {
        this.color = null;
    }

    public void defaultBackground() {
        this.bgColor = null;
    }

}