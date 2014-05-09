package org.mech.terminator;

import java.awt.Color;
import org.mech.terminator.geometry.Rectangle;

public class TerminalBuffer implements ITerminal {

	private static final char DEFAULT_CHAR = ' ';

	private TerminalSize size;
	private TerminalCharacter[][] ready, progress;
	private final Mutex mutex = new Mutex();

	public TerminalBuffer(final TerminalSize size) {
		this.size = size;
		ready = new TerminalCharacter[size.getLines()][size.getColumns()];
		progress = new TerminalCharacter[size.getLines()][size.getColumns()];

		init(ready);
		init(progress);
	}

	private void init(TerminalCharacter[][] termData) {
		for (int i = 0; i < size.getLines(); i++) {
			for (int j = 0; j < size.getColumns(); j++) {
				termData[i][j] = new TerminalCharacter(DEFAULT_CHAR);
			}
		}
	}

	private void reset(TerminalCharacter[][] termData) {
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
	public void put(char c, int line, int column) {
		progress[line][column].set(c);
	}

	@Override
	public void flush() {
		swap();
	}

	@Override
	public void bg(Color clr, int line, int column) {
		progress[line][column].bg(clr);
	}

	@Override
	public void fg(Color clr, int line, int column) {
		progress[line][column].fg(clr);
	}

	@Override
	public void bold(int line, int column) {
		progress[line][column].bold();
	}

	@Override
	public void bg(Color clr) {
		for (int i = 0; i < size.getLines(); i++) {
			for (int j = 0; j < size.getColumns(); j++) {
				bg(clr, i, j);
			}
		}
	}

	@Override
	public void put(char c) {
		for (int i = 0; i < size.getLines(); i++) {
			for (int j = 0; j < size.getColumns(); j++) {
				put(' ', i, j);
			}
		}

	}

}
