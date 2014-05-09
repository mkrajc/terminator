package org.mech.terminator.geometry;

import org.junit.Test;
import static org.junit.Assert.*;

public class GeometryUtilsTest {

	@Test
	public void testDist() {
		int dist = GeometryUtils.dist(0, 0, 1, 5);
		int dist2 = GeometryUtils.dist(Position.at(0, 0), Position.at(1, 5));
		assertEquals(5, dist);
		assertEquals(dist, dist2);
	}

	@Test
	public void testDistPyth() {
		float dist = GeometryUtils.distPyth(0, 0, 3, 4);
		float dist2 = GeometryUtils.distPyth(Position.at(0, 0), Position.at(3, 4));
		assertEquals(5, dist, 0.00001);
		assertEquals(dist, dist2, 0.00001);
	}
}
