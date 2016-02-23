package org.mech.terminator;

import java.awt.Color;

import org.mech.terminator.geometry.Dimension;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.geometry.Rectangle;

public class TerminalRectangleWrapper extends TerminalWrapper {
    private final Rectangle rectangle;
    private final Rectangle boundary;
    private final TerminalSize size;

    public TerminalRectangleWrapper(final Rectangle rectangle, final ITerminal terminal) {
        super(terminal);
        this.rectangle = rectangle;
        this.size = new TerminalSize(rectangle.getHeight(), rectangle.getWidth());

        final Dimension recSize = this.rectangle.getSize();
        final Dimension termSize = terminal.getSize().toDimension();

        if (recSize.width > termSize.width || recSize.height > termSize.height) {
            throw new IllegalArgumentException("Rectangle must be smaller than terminal [" + recSize + ", " + termSize + "]");
        }

        final Position boundaryStart = terminal.getBoundary().getTopLeftPosition().add(rectangle.getTopLeftPosition());
        this.boundary = new Rectangle(boundaryStart, rectangle.getSize());
    }

    public TerminalRectangleWrapper(final ITerminal terminal) {
        this(new Rectangle(Position.at(0, 0), terminal.getSize().toDimension()), terminal);
    }

    @Override
    public TerminalSize getSize() {
        return size;
    }

    @Override
    public void put(final char c, final int line, final int column) {
        getTerminal().put(c, termLine(line), termColumn(column));
    }

    private int termLine(final int line) {
        final int termLine = rectangle.getTopLeftPosition().y + line;
        if (termLine > rectangle.getBottomRightPosition().y) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds for rectangle terminal " + getSize() + ". line=" + termLine);
        }

        return termLine;
    }

    private int termColumn(final int column) {
        final int termCol = rectangle.getTopLeftPosition().x + column;

        if (termCol > rectangle.getBottomRightPosition().x) {
            throw new ArrayIndexOutOfBoundsException("Index out of bounds for rectangle terminal " + getSize() + ". column=" + termCol);
        }

        return termCol;
    }

    @Override
    public void flush() {
        // nothing
    }

    @Override
    public void bg(final Color clr, final int line, final int column) {
        super.bg(clr, termLine(line), termColumn(column));
    }

    @Override
    public void fg(final Color clr, final int line, final int column) {
        super.fg(clr, termLine(line), termColumn(column));
    }

    @Override
    public void bold(final int line, final int col) {
        super.bold(termLine(line), termColumn(col));
    }

    @Override
    public void bg(final Color clr) {
        for (int i = 0; i < size.getLines(); i++) {
            for (int j = 0; j < size.getColumns(); j++) {
                bg(clr, i, j);
            }
        }
    }

    @Override
    public void put(final char c) {
        for (int i = 0; i < size.getLines(); i++) {
            for (int j = 0; j < size.getColumns(); j++) {
                put(c, i, j);
            }
        }
    }

    @Override
    public Rectangle getBoundary() {
        return boundary;
    }

}
