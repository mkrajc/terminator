package org.mech.terminator.geometry;

import static org.junit.Assert.*;
import org.junit.Test;

public class RectangleTest {

	@Test
	public void testCorrectConstructor() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);

		final Rectangle test = new Rectangle(one, two);
		assertResult(test);
	}

	@Test
	public void testReverseConstructor() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);
		final Rectangle test = new Rectangle(two, one);
		assertResult(test);
	}

	@Test
	public void testDimensionConstructor() {
		final Position one = new Position(0, 0);
		final Rectangle test = new Rectangle(one, Dimension.of(8, 4));
		assertResult(test);
	}

	@Test
	public void testTransformedConstructor() {
		final Position one = new Position(7, 0);
		final Position two = new Position(0, 3);

		Rectangle test = new Rectangle(one, two);
		assertResult(test);

		test = new Rectangle(two, one);
		assertResult(test);

	}

	//    @Test(expected = IllegalArgumentException.class)
	//    public void testInvalidConstructor() {
	//	Position one = new Position(0, 0);
	//	Position two = new Position(7, 0);
	//	new Rectangle(two, one);
	//
	//    }

	@Test
	public void testLineUp() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);

		final Rectangle test = new Rectangle(one, two);

		final Line line = test.getLineUp();
		assertEquals(one, line.getStart());
		assertEquals(Position.at(7, 0), line.getEnd());
	}

	@Test
	public void testLineDown() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);

		final Rectangle test = new Rectangle(one, two);

		final Line line = test.getLineDown();
		assertEquals(Position.at(0, 3), line.getStart());
		assertEquals(Position.at(7, 3), line.getEnd());
	}

	@Test
	public void testLineLeft() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);

		final Rectangle test = new Rectangle(one, two);

		final Line line = test.getLineLeft();
		assertEquals(Position.at(0, 0), line.getStart());
		assertEquals(Position.at(0, 3), line.getEnd());
	}

	@Test
	public void testLineRight() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);

		final Rectangle test = new Rectangle(one, two);

		final Line line = test.getLineRight();
		assertEquals(Position.at(7, 0), line.getStart());
		assertEquals(Position.at(7, 3), line.getEnd());
	}

	@Test
	public void testLineLength() {
		final Position one = new Position(0, 0);
		final Position two = new Position(7, 3);

		final Rectangle test = new Rectangle(one, two);

		final Line line = test.getLineUp();
		assertEquals(8, line.getLength());
	}

	@Test
	public void testRectangleSize() {
		final Position one = new Position(0, 0);
		final Rectangle test = new Rectangle(one, Dimension.of(1, 1));

		assertEquals(1, test.getWidth());
		assertEquals(1, test.getHeight());
	}

	@Test
	public void testTopLeft() {
		final Position one = new Position(0, 0);
		final Rectangle test = new Rectangle(one, Dimension.of(10, 10));

		assertEquals(one, test.getTopLeftPosition());
	}

	@Test
	public void testBottomRight() {
		final Position one = new Position(0, 0);
		final Rectangle test = new Rectangle(one, Dimension.of(10, 10));

		System.out.println(test.getWidth());
		assertEquals(Position.at(9, 9), test.getBottomRightPosition());
	}

	private void assertResult(final Rectangle test) {
		assertEquals(8, test.getWidth());
		assertEquals(4, test.getHeight());
		assertEquals(new Position(0, 0), test.getTopLeftPosition());
		assertEquals(new Position(7, 3), test.getBottomRightPosition());
	}
	
	@Test
	public void testRectangleIn() {
		final Position one = new Position(3, 3);
		final Rectangle test = new Rectangle(one, Dimension.of(1, 1));
		
		assertTrue(test.isIn(test.getBottomLeftPosition()));
		assertTrue(test.isIn(test.getTopLeftPosition()));
		assertTrue(test.isIn(test.getBottomRightPosition()));
		assertTrue(test.isIn(test.getTopRightPosition()));

	}
	
	@Test
	public void testToAbs() {
		final Position one = new Position(100, 100);
		final Rectangle test = new Rectangle(one, Dimension.of(10, 10));

		assertEquals(Position.at(100, 100), test.toAbsolute(Position.at(0, 0)));
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testToAbsFail() {
		final Position one = new Position(100, 100);
		final Rectangle test = new Rectangle(one, Dimension.of(10, 10));
		
		test.toAbsolute(Position.at(20, 20));
	}
	
	
	@Test
	public void testToRel() {
		final Position one = new Position(100, 100);
		final Rectangle test = new Rectangle(one, Dimension.of(10, 10));

		assertEquals(Position.at(9, 9), test.toRelative(Position.at(109, 109)));
		assertEquals(Position.at(0, 0), test.toRelative(Position.at(100, 100)));
	}
	

	@Test(expected=IllegalArgumentException.class)
	public void testToRelFail() {
		final Position one = new Position(100, 100);
		final Rectangle test = new Rectangle(one, Dimension.of(10, 10));
		
		test.toRelative(Position.at(2,2));
	}

	
	

}
