package org.mech.terminator;

import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class TerminalSize {
    private int lines;
    private int columns;

    private Rectangle rectangle;

    public TerminalSize(int lines, int columns) {
        super();
        this.lines = lines;
        this.columns = columns;
    }

    public int getLines() {
        return lines;
    }

    public void setLines(int lines) {
        this.lines = lines;
    }

    public int getColumns() {
        return columns;
    }

    public void setColumns(int columns) {
        this.columns = columns;
    }

    public Rectangle toRectangle() {
        if (rectangle == null) {
            rectangle = new Rectangle(Position.at(0, 0), Dimension.of(columns, lines));
        }
        return rectangle;
    }

    public Dimension toDimension() {
        return new Dimension(columns, lines);
    }

    @Override
    public String toString() {
        return "[" + lines + "x" + columns + "]";
    }
}
