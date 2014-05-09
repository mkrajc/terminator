package org.mech.terminator;

import java.awt.Color;

public class TerminalCharacter {
	private char ch;
	private Color fg, bg;
	private boolean bold;

	public TerminalCharacter(char ch) {
		this.ch = ch;
	}

	public char get() {
		return ch;
	}

	@Override
	public String toString() {
		return Character.toString(ch);
	}

	public void set(char ch) {
		this.ch = ch;
	}

	public void fg(Color clr) {
		this.fg = clr;
	}

	public void bg(Color clr) {
		if (clr != null) {
			this.bg = clr;
		}
	}

	public Color getBg() {
		return bg;
	}

	public Color getFg() {
		return fg;
	}

	public void reset(char c) {
		fg = null;
		bg = null;
		ch = c;
		bold = false;
	}

	public void bold() {
		this.bold = true;
	}

	public boolean isBold() {
		return bold;
	}
}
