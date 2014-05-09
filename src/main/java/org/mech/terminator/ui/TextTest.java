package org.mech.terminator.ui;

import static org.junit.Assert.*;
import org.junit.Test;
import org.mech.terminator.ui.Text.CharControl;

public class TextTest {

	@Test
	public void testText() {
		Text text = new Text("asd");
		assertEquals(3, text.length());
	}

	@Test
	public void testTextWithMacro() {
		Text text = new Text("@ga@wsd");
		assertEquals(3, text.length());

	}

	@Test
	public void testControl() {
		Text text = new Text("01@g2@w34");
		assertNull(text.getControl(0));
		assertNull(text.getControl(1));
		assertNotNull(text.getControl(2));
		assertNotNull(text.getControl(3));
		assertNull(text.getControl(4));
	}
	
	@Test
	public void testControlColor() {
		Text text = new Text("01@g2@#34");
		CharControl control = text.getControl(2);
		assertNotNull(control);
		assertNotNull(control.fg);
		CharControl controlAt3 = text.getControl(3);
		assertNotNull(controlAt3);
		assertNull(controlAt3.fg);
	}
	
	@Test
	public void testTab() {
		Text text = new Text("0\t@g3");
		assertNotNull(text.getControl(3));
	}
	

	@Test
	public void testTabs() {
		Text text = new Text("a\t\t\ta");
		assertEquals(8, text.length());
	}


}
