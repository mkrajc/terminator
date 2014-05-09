package org.mech.terminator.ui;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Text {

	private static final int TAB_SPACES = 2;
	private static final char TAB = '\t';
	private static final char CONTROL_CHAR = '@';
	private static final char CONTROL_STOP_CHAR = '#';

	private static final Map<Character, Color> colorMap = new HashMap<Character, Color>();

	static {
		colorMap.put('b', Color.BLUE);
		colorMap.put('r', Color.RED);
		colorMap.put('w', Color.WHITE);
		colorMap.put('y', Color.YELLOW);
		colorMap.put('g', Color.GREEN);
		colorMap.put('G', Color.GRAY);
	}

	private char[] textArray;
	private List<CharControl> controls = new ArrayList<Text.CharControl>();

	int lastPutIndex = 0;

	public Text(String text) {
		char[] ar = text.toCharArray();
		textArray = new char[ar.length];

		for (int index = 0; index < ar.length; index++) {
			char c = ar[index];
			if (c == TAB) {
				index = putTab(index);
			} else if (c == CONTROL_CHAR) {
				index = handleControl(ar, index);
			} else {
				index = put(c, index);
			}
		}

		if (textArray.length != lastPutIndex) {
			textArray = Arrays.copyOf(textArray, lastPutIndex);
		}

	}

	private int handleControl(char[] ar, int index) {
		if (index + 1 > ar.length) {
			throw new IllegalArgumentException("invalid end of sequence");
		}
		char next = ar[index + 1];
		if (next == CONTROL_CHAR) {
			put(CONTROL_CHAR, index);
			return index + 1;
		} else {
			CharControl charControl = new CharControl();
			charControl.startIdx = lastPutIndex;

			if (next == CONTROL_STOP_CHAR) {
				charControl.fg = null;
			} else {

				Color color = colorMap.get(next);

				if (color == null) {
					throw new IllegalArgumentException("Color not defined for character=" + next + ", text=" + new String(ar));
				}

				charControl.fg = color;
			}

			controls.add(charControl);
			return index + 1;
		}

	}

	private int put(char c, int index) {
		if(lastPutIndex >= textArray.length){
			textArray = Arrays.copyOf(textArray, textArray.length*2);
		}
		textArray[lastPutIndex++] = c;
		return index;
	}

	private int putTab(int index) {
		for (int i = index; i < index + TAB_SPACES; i++) {
			put(' ', i);
		}
		return index;
	}

	public int length() {
		return textArray.length;
	}

	public static class CharControl {
		Color fg;
		int startIdx;
	}
	
	public CharControl getControl(int i) {
		if (controls == null) {
			return null;
		}

		CharControl control = null;
		for (CharControl c : controls) {
			if (c.startIdx == i) {
				control = c;
				break;
			}
		}
		
		return control;
	}
	
	public char[] getChars(){
		return textArray;
	}
	
	@Override
	public String toString() {
		return new String(textArray);
	}

}
