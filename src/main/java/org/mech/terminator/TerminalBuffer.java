package org.mech.terminator;

import java.awt.Color;
import org.mech.terminator.geometry.Rectangle;

public class TerminalBuffer implements ITerminal {

	private static final char DEFAULT_CHAR = ' ';

	private final TerminalSize size;
	private TerminalCharacter[][] ready, progress;
	private final Mutex mutex = new Mutex();

	public TerminalBuffer(final TerminalSize size) {
		this.size = size;
		ready = new TerminalCharacter[size.getLines()][size.getColumns()];
		progress = new TerminalCharacter[size.getLines()][size.getColumns()];

		init(ready);
		init(progress);
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

	public TerminalCharacter[][] getBuffer() {
		synchronized (mutex) {
			// System.out.println("locking");
			mutex.isLocked = true;
			return ready;
		}
	}

	public void swap() {
		synchronized (mutex) {
			if (mutex.isLocked) {
				reset(progress);
				// System.out.println("reseting in progress");
			} else {
				// System.out.println("swapping ... ");
				final TerminalCharacter[][] swap = progress;
				reset(ready);
				progress = ready;
				ready = swap;
			}
		}
	}

	public void release() {
		synchronized (mutex) {
			// System.out.println("releasing");
			mutex.isLocked = false;
		}
	}

	@Override
	public TerminalSize getSize() {
		return size;
	}

	private static class Mutex {
		boolean isLocked = false;
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

	private TerminalCharacter getChar(final int line, final int col) {
		if (line >= ready.length || col >= ready[0].length) {
			System.out.println("TODO fix by better thread sync");
			return null;
		}
		return ready[line][col];
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
	
	public void initilize(final TerminalBuffer buffer){
		final int maxLine = Math.min(size.getLines(), buffer.getSize().getLines());
		final int maxColumn = Math.min(size.getColumns(), buffer.getSize().getColumns());
		for (int i = 0; i < maxLine; i++) {
			for (int j = 0; j < maxColumn; j++) {
				ready[i][j] = buffer.getChar(i, j);
			}
		}
	}

}
