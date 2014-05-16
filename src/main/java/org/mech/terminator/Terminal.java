package org.mech.terminator;

public class Terminal extends TerminalWrapper {
	private static Terminal INSTANCE;
	private final TerminalSize size;
	
	public static Terminal getInstance(){
		return INSTANCE;
	}
	
	public static void setInstance(final Terminal terminal){
		INSTANCE = terminal;
	}

	public Terminal() {
		this.size = new TerminalSize(25, 80);
		setTerminal(new TerminalBuffer(size));
	}

	public Terminal(final TerminalSize size) {
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
