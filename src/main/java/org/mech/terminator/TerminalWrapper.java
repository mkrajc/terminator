package org.mech.terminator;

import java.awt.Color;

import org.mech.terminator.geometry.Rectangle;

public class TerminalWrapper implements ITerminal {
    private ITerminal t;

    public TerminalWrapper() {
    }

    public TerminalWrapper(ITerminal terminal) {
        this.t = terminal;
    }

    public void flush() {
        t.flush();
    }

    @Override
    public TerminalSize getSize() {
        return t.getSize();
    }

    @Override
    public void put(char c, int line, int column) {
        t.put(c, line, column);
    }

    @Override
    public void fg(Color clr, int line, int column) {
        t.fg(clr, line, column);

    }

    @Override
    public void bg(Color clr, int line, int column) {
        t.bg(clr, line, column);
    }

    @Override
    public Rectangle getBoundary() {
        return t.getBoundary();
    }

    @Override
    public void bold(int line, int col) {
        t.bold(line, col);
    }

    protected ITerminal getTerminal() {
        return t;
    }

    protected void setTerminal(ITerminal t) {
        this.t = t;
    }

    @Override
    public void bg(Color clr) {
        t.bg(clr);
    }

    @Override
    public void put(char c) {
        t.put(c);
    }

}
