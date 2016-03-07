package org.mech.terminator.geometry;

import org.junit.Test;

import static org.junit.Assert.*;


public class PositionTest {

    @Test
    public void testCompareTo() throws Exception {
        assertEquals(1, Position.at(1,1).compareTo(Position.at(0,0)));
        assertEquals(1, Position.at(1,1).compareTo(Position.at(0,10)));
        assertEquals(-1, Position.at(0,0).compareTo(Position.at(1,1)));
        assertEquals(-1, Position.at(0,10).compareTo(Position.at(1,1)));
        assertEquals(0, Position.at(0,0).compareTo(Position.at(0,0)));
    }
}