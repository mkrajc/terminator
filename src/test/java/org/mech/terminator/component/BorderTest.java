package org.mech.terminator.component;

import static org.junit.Assert.*;
import org.junit.Test;

public class BorderTest {

	@Test
	public void testIsTop() {
		Border border = new Border();
		border.setBorders(Border.TOP);
		assertTrue(border.isTop());
	}

	@Test
	public void testIsBottom() {
		Border border = new Border();
		border.setBorders(Border.BOTTOM);
		assertTrue(border.isBottom());
	}

	@Test
	public void testIsRight() {
		Border border = new Border();
		border.setBorders(Border.RIGHT);
		assertTrue(border.isRight());
	}

	@Test
	public void testIsLeft() {
		Border border = new Border();
		border.setBorders(Border.LEFT);
		assertTrue(border.isLeft());
	}

}
