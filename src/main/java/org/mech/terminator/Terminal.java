package org.mech.terminator;

public class Terminal extends TerminalWrapper {
	public static Terminal INSTANCE;
	private TerminalSize size;

	public Terminal() {
		this.size = new TerminalSize(25, 80);
		setTerminal(new TerminalBuffer(size));
	}

	public Terminal(TerminalSize size) {
		this.size = size;
		setTerminal(new TerminalBuffer(size));
	}

	public TerminalCharacter[][] retrieveLock() {
		return getBuffer().getBuffer();
	}

	public void releaseLock() {
		getBuffer().release();
	}

	protected TerminalBuffer getBuffer() {
		return (TerminalBuffer) getTerminal();
	}

}
