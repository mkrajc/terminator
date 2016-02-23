package org.mech.terminator.command;

import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Position;


public abstract class Command {

    private ITerminal instance;
    private Position pos;

    public Command(ITerminal instance) {
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

    protected void moveNextLine() {
        pos.add_y(1);
        pos.x = 0;
    }

    protected void moveNextColumn() {
        pos.add_x(1);
    }

    public ITerminal getTerminal() {
        return instance;
    }

}
