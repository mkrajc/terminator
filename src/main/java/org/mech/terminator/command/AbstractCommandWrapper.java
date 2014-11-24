package org.mech.terminator.command;

import org.mech.terminator.geometry.Position;

import java.io.IOException;

/**
 * Created by alberto on 23/11/14.
 */
public interface AbstractCommandWrapper {
    public void setPosition(Position pos);
    public void flush() throws IOException;
}
