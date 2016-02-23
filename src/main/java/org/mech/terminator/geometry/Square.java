package org.mech.terminator.geometry;

public class Square extends Rectangle {

    public Square(Position center, int radius) {
        super(center.add(Position.at(-radius, -radius)), Dimension.of(2 * radius + 1, 2 * radius + 1));
    }

}
