package org.mech.terminator.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

public class RectangleTest {

	@Test
	public void testCorrectConstructor() {
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);

		Rectangle test = new Rectangle(one, two);
		assertResult(test);
	}

	@Test
	public void testReverseConstructor() {
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);
		Rectangle test = new Rectangle(two, one);
		assertResult(test);
	}

	@Test
	public void testDimensionConstructor() {
		Position one = new Position(0, 0);
		Rectangle test = new Rectangle(one, Dimension.of(8, 4));
		assertResult(test);
	}

	@Test
	public void testTransformedConstructor() {
		Position one = new Position(7, 0);
		Position two = new Position(0, 3);

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
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);

		Rectangle test = new Rectangle(one, two);

		Line line = test.getLineUp();
		assertEquals(one, line.getStart());
		assertEquals(Position.at(7, 0), line.getEnd());
	}

	@Test
	public void testLineDown() {
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);

		Rectangle test = new Rectangle(one, two);

		Line line = test.getLineDown();
		assertEquals(Position.at(0, 3), line.getStart());
		assertEquals(Position.at(7, 3), line.getEnd());
	}

	@Test
	public void testLineLeft() {
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);

		Rectangle test = new Rectangle(one, two);

		Line line = test.getLineLeft();
		assertEquals(Position.at(0, 0), line.getStart());
		assertEquals(Position.at(0, 3), line.getEnd());
	}

	@Test
	public void testLineRight() {
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);

		Rectangle test = new Rectangle(one, two);

		Line line = test.getLineRight();
		assertEquals(Position.at(7, 0), line.getStart());
		assertEquals(Position.at(7, 3), line.getEnd());
	}

	@Test
	public void testLineLength() {
		Position one = new Position(0, 0);
		Position two = new Position(7, 3);

		Rectangle test = new Rectangle(one, two);

		Line line = test.getLineUp();
		assertEquals(7, line.getLength());
	}

	@Test
	public void testRectangleSize() {
		Position one = new Position(0, 0);
		Rectangle test = new Rectangle(one, Dimension.of(1, 1));

		assertEquals(1, test.getWidth());
		assertEquals(1, test.getHeight());
	}

	@Test
	public void testTopLeft() {
		Position one = new Position(0, 0);
		Rectangle test = new Rectangle(one, Dimension.of(10, 10));

		assertEquals(one, test.getTopLeftPosition());
	}

	@Test
	public void testBottomRight() {
		Position one = new Position(0, 0);
		Rectangle test = new Rectangle(one, Dimension.of(10, 10));

		System.out.println(test.getWidth());
		assertEquals(Position.at(9, 9), test.getBottomRightPosition());
	}

	private void assertResult(Rectangle test) {
		assertEquals(8, test.getWidth());
		assertEquals(4, test.getHeight());
		assertEquals(new Position(0, 0), test.getTopLeftPosition());
		assertEquals(new Position(7, 3), test.getBottomRightPosition());
	}

}
