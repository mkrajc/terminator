package org.mech.terminator.geometry;

import static org.junit.Assert.*;
import org.junit.Test;

public class SquareTest {

	@Test
	public void testSquare() {
		Square square = new Square(Position.at(5, 5), 1);
		assertEquals(Position.at(4, 4), square.getTopLeftPosition());
		assertEquals(Position.at(6, 6), square.getBottomRightPosition());
	}

}
