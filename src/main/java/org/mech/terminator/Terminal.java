package org.mech.terminator;

public class Terminal extends TerminalWrapper {
	private static Terminal INSTANCE;

	public static Terminal getInstance() {
		return INSTANCE;
	}

	public static void setInstance(final Terminal terminal) {
		INSTANCE = terminal;
	}

	public Terminal() {
		setTerminal(new TerminalBuffer(new TerminalSize(25, 80)));
	}

	public Terminal(final TerminalSize size) {
		setTerminal(new TerminalBuffer(size));
	}
	
	public TerminalCharacter[][] retrieveLock() {
		final TerminalCharacter[][] buffer = getBuffer().getBuffer();
		return buffer;
	}

	public void releaseLock() {
		getBuffer().release();
	}

	protected TerminalBuffer getBuffer() {
		return (TerminalBuffer) getTerminal();
	}

	public void setSize(final TerminalSize terminalSize) {
		System.out.println(System.currentTimeMillis() + " terminal change size from " + getSize() + " to " + terminalSize);
		final TerminalBuffer terminalBuffer = new TerminalBuffer(terminalSize);
		terminalBuffer.initilize(getBuffer());
		setTerminal(terminalBuffer);
	}

}
