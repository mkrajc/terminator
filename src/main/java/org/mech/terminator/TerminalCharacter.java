package org.mech.terminator;

import java.awt.Color;
import java.io.Serializable;

import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

public class TerminalCharacter implements Serializable {
    private char ch;
    private Color fg, bg;
    private boolean bold;

    public TerminalCharacter(char ch) {
        this.ch = ch;
    }

    public char get() {
        return ch;
    }

    @Override
    public String toString() {
        return Character.toString(ch);
    }

    public void set(char ch) {
        this.ch = ch;
    }

    public void fg(Color clr) {
        this.fg = clr;
    }

    public void bg(Color clr) {
        if (clr != null) {
            this.bg = clr;
        }
    }

    public Color getBg() {
        return bg;
    }

    public Color getFg() {
        return fg;
    }


    public void reset(char c) {
        fg = null;
        bg = null;
        ch = c;
        bold = false;
    }

    public void bold() {
        this.bold = true;
    }

    public boolean isBold() {
        return bold;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;

        if (o == null || getClass() != o.getClass()) return false;

        TerminalCharacter that = (TerminalCharacter) o;

        return new EqualsBuilder()
                .append(ch, that.ch)
                .append(bold, that.bold)
                .append(fg, that.fg)
                .append(bg, that.bg)
                .isEquals();
    }

    @Override
    public int hashCode() {
        return new HashCodeBuilder(17, 37)
                .append(ch)
                .append(fg)
                .append(bg)
                .append(bold)
                .toHashCode();
    }

}
