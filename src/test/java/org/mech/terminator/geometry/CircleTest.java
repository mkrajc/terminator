package org.mech.terminator.geometry;

import static org.junit.Assert.*;
import org.junit.Test;

public class CircleTest {

	@Test
	public void testPerimeter() {
		final Circle a = new Circle(Position.at(0, 0), 3);
		final Circle b = new Circle(Position.at(0, 0), 4);

		assertEquals(a.getPerimeter(3).size(), b.getPerimeter(3).size());
	}

	@Test
	public void testCircle() {
		Circle a = new Circle(Position.at(0, 0), 0);
		assertEquals(0, a.getRadius());
		assertEquals(1, a.getPerimeter().size());

		a = new Circle(Position.at(0, 0), 1);
		assertEquals(1, a.getRadius());
		assertEquals(8, a.getPerimeter().size());
	}

}
