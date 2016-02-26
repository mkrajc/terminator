package org.mech.terminator;

import java.awt.Color;

import org.apache.commons.lang3.SerializationUtils;
import org.mech.terminator.geometry.Rectangle;

public class TerminalBuffer implements ITerminal {

    private static final char DEFAULT_CHAR = ' ';

    private final TerminalSize size;
    private TerminalCharacter[][] write, read;
    private boolean copied = false;

    public TerminalBuffer(final TerminalSize size) {
        this.size = size;
        write = new TerminalCharacter[size.getLines()][size.getColumns()];
        read = new TerminalCharacter[size.getLines()][size.getColumns()];

        init(write);
        init(read);
    }

    TerminalCharacter[][] newBuffer() {
        return new TerminalCharacter[size.getLines()][size.getColumns()];
    }

    private void init(final TerminalCharacter[][] termData) {
        for (int i = 0; i < size.getLines(); i++) {
            for (int j = 0; j < size.getColumns(); j++) {
                termData[i][j] = new TerminalCharacter(DEFAULT_CHAR);
            }
        }
    }

    private void reset(final TerminalCharacter[][] termData) {
        for (int i = 0; i < size.getLines(); i++) {
            for (int j = 0; j < size.getColumns(); j++) {
                termData[i][j].reset(DEFAULT_CHAR);
            }
        }
    }

    void copy(TerminalCharacter[][] buffer) {
        if (!copied) {
            synchronized (this) {
                if (read.length != buffer.length || (read.length > 0 && buffer.length > 0 && read[0].length != buffer[0].length)) {
                    throw new IllegalArgumentException("Buffer is not same size as data");
                }
                for (int i = 0; i < read.length; i++) {
                    for (int j = 0; j < read[0].length; j++) {
                        buffer[i][j] = SerializationUtils.clone(read[i][j]);
                    }
                }
                copied = true;
            }
        }
    }

    private void swap() {
        synchronized (this) {
            final TerminalCharacter[][] swap = write;
            write = read;
            read = swap;
            reset(write);
            copied = false;
        }
    }


    @Override
    public TerminalSize getSize() {
        return size;
    }

    @Override
    public Rectangle getBoundary() {
        return size.toRectangle();
    }

    @Override
    public void put(final char c, final int line, final int column) {
        final TerminalCharacter ch = getChar(line, column);
        if (ch != null) {
            ch.set(c);
        }
    }

    @Override
    public void flush() {
        swap();
    }

    @Override
    public void bg(final Color clr, final int line, final int column) {
        final TerminalCharacter ch = getChar(line, column);
        if (ch != null) {
            ch.bg(clr);
        }
    }

    @Override
    public void fg(final Color clr, final int line, final int column) {
        final TerminalCharacter ch = getChar(line, column);
        if (ch != null) {
            ch.fg(clr);
        }
    }

    @Override
    public void bold(final int line, final int column) {
        final TerminalCharacter ch = getChar(line, column);
        if (ch != null) {
            ch.bold();
        }
    }

    TerminalCharacter getChar(final int line, final int col) {
        if (line >= write.length || col >= write[0].length) {
            return null;
        }
        return write[line][col];
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


    void initilize(final TerminalBuffer buffer) {
        final int maxLine = Math.min(size.getLines(), buffer.getSize().getLines());
        final int maxColumn = Math.min(size.getColumns(), buffer.getSize().getColumns());
        for (int i = 0; i < maxLine; i++) {
            for (int j = 0; j < maxColumn; j++) {
                read[i][j] = buffer.getChar(i, j);
            }
        }
    }

}
