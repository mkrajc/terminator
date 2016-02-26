package org.mech.terminator;

public class Terminal extends TerminalWrapper {
    private static Terminal INSTANCE;

    public static Terminal getInstance() {
        return INSTANCE;
    }

    public static void setInstance(final Terminal terminal) {
        System.out.println();
        INSTANCE = terminal;
    }

    public Terminal() {
        setTerminal(new TerminalBuffer(new TerminalSize(25, 80)));
    }

    public Terminal(final TerminalSize size) {
        setTerminal(new TerminalBuffer(size));
    }

    public void copy(TerminalCharacter[][] buffer) {
        getBuffer().copy(buffer);
    }

    public TerminalCharacter[][] newBuffer() {
        return getBuffer().newBuffer();
    }

    protected TerminalBuffer getBuffer() {
        return (TerminalBuffer) getTerminal();
    }

    public void setSize(final TerminalSize terminalSize) {
        final TerminalBuffer terminalBuffer = new TerminalBuffer(terminalSize);
        terminalBuffer.initilize(getBuffer());
        setTerminal(terminalBuffer);
    }

}
