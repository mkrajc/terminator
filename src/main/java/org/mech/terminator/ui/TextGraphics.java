package org.mech.terminator.ui;

import java.awt.Color;
import org.mech.terminator.ITerminal;
import org.mech.terminator.geometry.Position;
import org.mech.terminator.ui.Text.CharControl;

public class TextGraphics {

	private boolean linesCounting = false;
	private int lines = 1;

	private Position cursor;
	private ITerminal terminal;
	private Color fgColorSet = null;
	private Color fgColorCurrent = null;

	public TextGraphics(ITerminal terminal) {
		cursor = new Position(0, 0);
		this.terminal = terminal;
	}

	public void setFgColor(Color color) {
		fgColorSet = color;
		fgColorCurrent = color;
	}

	public void drawString(String text) {
		drawText(new Text(text));
	}

	public void drawString(String text, int offset) {
		drawText(new Text(text), offset);
	}
	
	public void drawText(Text text, int offset) {
		cursor.add_x(offset);
		drawText(text);
	}

	public void drawText(Text text) {
		char[] ar = text.getChars();

		for (int index = 0; index < ar.length; index++) {
			CharControl c = text.getControl(index);
			if (c != null) {
				fgColorCurrent = c.fg == null ? fgColorSet : c.fg;
			}

			put(ar[index]);

		}
	}

	private void put(char c) {
		if (!linesCounting) {
			terminal.put(c, cursor.y, cursor.x);
			terminal.fg(fgColorCurrent, cursor.y, cursor.x);
		}
		moveCursorToRight();
	}

	private void moveCursorToRight() {
		if (cursor.x < (terminal.getSize().getColumns() - 1)) {
			cursor.add_x(1);
		} else {
			cursor.x = 0;
			if (cursor.y < (terminal.getSize().getLines() - 1)) {
				cursor.add_y(1);
				lines++;
			}
		}
	}

	public int length(String text) {
		return new Text(text).length();
	}

	public int lines(String text) {

		linesCounting = true;
		lines = 1;

		final Position old = Position.clone(cursor);

		drawString(text);

		cursor = old;

		linesCounting = false;
		return lines;
	}

	public void newLine() {
		if (cursor.y < (terminal.getSize().getLines() - 1)) {
			cursor.add_y(1);
			cursor.x = 0;
		}
	}

	public void newLines(int lines) {
		for (int i = 0; i < lines; i++) {
			newLine();
		}
	}
}
