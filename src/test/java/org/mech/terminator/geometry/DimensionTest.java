package org.mech.terminator.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

public class DimensionTest {

	@Test
	public void testToString() {
		assertEquals("100x50", new Dimension(100, 50).toString());
	}

	@Test
	public void testOf() {
		Dimension dim = Dimension.of(100, 50);
		assertEquals(100, dim.width);
		assertEquals(50, dim.height);
	}

	@Test
	public void testIsOut() {
		Dimension dim = Dimension.of(100, 50);
		assertTrue(dim.isOut(Position.at(100, 49)));
		assertTrue(dim.isOut(Position.at(99, 50)));
		assertTrue(dim.isOut(Position.at(100, 50)));
		assertFalse(dim.isOut(Position.at(99, 49)));
	}

	@Test
	public void testCloneDimension() {
		Dimension dim = Dimension.of(100, 50);
		Dimension clone = Dimension.clone(dim);

		assertNotSame(dim, clone);
		assertEquals(dim, clone);
	}

	@Test
	public void testEqualsObject() {
		Dimension dim1 = Dimension.of(100, 50);
		Dimension dim2 = Dimension.of(100, 50);
		assertTrue(dim1.equals(dim2));
		assertTrue(dim2.equals(dim1));
	}

	@Test
	public void testGetArea() {
		Dimension dim = Dimension.of(100, 50);
		assertEquals(5000, dim.getArea());
	}

	@Test
	public void testGetWidth() {
		assertEquals(100, Dimension.of(100, 1).getWidth().intValue());
		assertNull(Dimension.of(0, 1).getWidth());
		assertNull(Dimension.of(-1, 1).getWidth());
	}

	@Test
	public void testGetHeight() {
		assertEquals(50, Dimension.of(100, 50).getHeight().intValue());
		assertNull(Dimension.of(1, 0).getHeight());
		assertNull(Dimension.of(1, -1).getHeight());
	}

}
