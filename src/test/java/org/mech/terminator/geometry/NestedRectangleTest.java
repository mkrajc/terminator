package org.mech.terminator.geometry;

import static org.junit.Assert.*;
import org.junit.Test;

public class NestedRectangleTest {

	@Test
	public void testCreation() {
		new NestedRectangle(Position.at(1000, 1000), Dimension.of(3, 3), Dimension.of(5, 5));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testBadCreation() {
		new NestedRectangle(Position.at(100, 100), Dimension.of(5, 5), new Rectangle(Position.at(0, 0), Dimension.of(7, 7)));
	}

	@Test
	public void testInnerRelToRel() {
		final NestedRectangle nestedRectangle = new NestedRectangle(Position.at(100, 100), Dimension.of(5, 5), new Rectangle(Position.at(95, 95),
				Dimension.of(10, 10)));

		assertEquals(Position.at(5, 5), nestedRectangle.innerRelToRel(Position.at(0, 0)));
	}
	
	@Test
	public void testInnerAbsToRel() {
		final NestedRectangle nestedRectangle = new NestedRectangle(Position.at(100, 100), Dimension.of(5, 5), new Rectangle(Position.at(95, 95),
				Dimension.of(10, 10)));
		

		assertEquals(Position.at(2, 2), nestedRectangle.toRelative(Position.at(102, 102)));
		assertEquals(Position.at(7, 7), nestedRectangle.innerAbsToRel(Position.at(102, 102)));
	}
	
	@Test
	public void testOuterRelToRel() {
		final NestedRectangle nestedRectangle = new NestedRectangle(Position.at(100, 100), Dimension.of(5, 5), new Rectangle(Position.at(95, 95),
				Dimension.of(10, 10)));

		assertEquals(Position.at(0, 0), nestedRectangle.outerRelToRel(Position.at(5, 5)));
	}
	
	@Test
	public void testOuterAbsToRel() {
		final NestedRectangle nestedRectangle = new NestedRectangle(Position.at(100, 100), Dimension.of(5, 5), new Rectangle(Position.at(95, 95),
				Dimension.of(10, 10)));

		assertEquals(Position.at(2, 2), nestedRectangle.outerAbsToRel(Position.at(102, 102)));
	}
	
}
