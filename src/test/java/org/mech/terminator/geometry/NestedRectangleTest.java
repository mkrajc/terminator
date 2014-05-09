package org.mech.terminator.geometry;

import static org.junit.Assert.*;
import org.junit.Test;

public class NestedRectangleTest {

	@Test
	public void testInnerToOuter() {
		NestedRectangle nestedRectangle = new NestedRectangle(Position.at(1, 2), Dimension.of(5, 3), Dimension.of(7, 7));

		assertEquals(Position.at(2, 3), nestedRectangle.innerToOuter(Position.at(1, 1)));
		assertEquals(Position.at(1, 2), nestedRectangle.innerToOuter(Position.at(0, 0)));
		assertEquals(Position.at(5, 4), nestedRectangle.innerToOuter(Position.at(4, 2)));

	}

	@Test
	public void testOuterToInner() {
		NestedRectangle nestedRectangle = new NestedRectangle(Position.at(1, 2), Dimension.of(5, 3), Dimension.of(7, 7));

		assertEquals(Position.at(1, 1), nestedRectangle.outerToInner(Position.at(2, 3)));
		assertEquals(Position.at(0, 0), nestedRectangle.outerToInner(Position.at(1, 2)));
		assertEquals(Position.at(5, 3), nestedRectangle.outerToInner(Position.at(6, 5)));
		assertNull(nestedRectangle.outerToInner(Position.at(1, 1)));
		assertNull(nestedRectangle.outerToInner(Position.at(1, 0)));
		assertNull(nestedRectangle.outerToInner(Position.at(0, 0)));
		assertNull(nestedRectangle.outerToInner(Position.at(6, 6)));

	}

}
