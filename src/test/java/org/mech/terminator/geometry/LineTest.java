package org.mech.terminator.geometry;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;

public class LineTest {

    @Test
    public void testLineLength() {
        Line line = Line.createHorizontal(Position.at(0, 0), 1);
        assertEquals(1, line.getLength());
        assertEquals(line.getStart(), line.getEnd());
    }

    @Test
    public void testLine() {
        Line line = new Line(Position.at(0, 0), Position.at(5, 3));
        assertEquals(6, line.getLength());
        List<Position> points = line.getPoints();

        for (Position p : points) {
            System.out.println(p);
        }
    }

    @Test
    public void testLineToString() {
        Line line = new Line(Position.at(0, 0), Position.at(5, 5));
        assertEquals("0,0-5,5", line.toString());
    }

}
