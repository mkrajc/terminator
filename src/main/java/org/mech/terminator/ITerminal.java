package org.mech.terminator;

import java.awt.Color;
import org.mech.terminator.geometry.Rectangle;

public interface ITerminal {
	TerminalSize getSize();

	Rectangle getBoundary();

	void put(char c, int line, int column);

	void flush();

	void bg(Color clr, int line, int column);

	void fg(Color clr, int line, int column);

	void bg(Color clr);

	void put(char c);

	void bold(int line, int column);
}
