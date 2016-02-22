package org.mech.terminator.command;

import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Position;

import java.io.IOException;


public abstract class CommandWrapper {

    private ITerminal instance;
    private Position pos;

    public CommandWrapper(ITerminal instance) {
        this.instance = instance;
    }

    public void setPosition(Position pos) {
        this.pos = pos;
    }

    public Position getPosition() {
        if (pos == null) {
            pos = new Position(0, 0);
        }
        return pos;
    }

    public abstract void flush() throws IOException;

    public ITerminal getTerminal() {
        return instance;
    }

}
