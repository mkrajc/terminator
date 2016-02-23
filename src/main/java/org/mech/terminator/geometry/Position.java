package org.mech.terminator.geometry;

import java.io.Serializable;

public class Position implements Serializable, Comparable<Position> {
    public int x, y;

    public Position(final int x, final int y) {
        this.x = x;
        this.y = y;
    }

    @Override
    public boolean equals(final Object obj) {
        final Position p = (Position) obj;
        return x == p.x && y == p.y;
    }

    @Override
    public int hashCode() {
        int hash = 23;
        hash = hash * 31 + x;
        hash = hash * 31 + y;
        return hash;
    }

    @Override
    public String toString() {
        return x + "," + y;
    }

    public static Position at(final int x, final int y) {
        return new Position(x, y);
    }

    public void add_(final Position position) {
        this.x += position.x;
        this.y += position.y;
    }

    public void add_x(final int d) {
        this.x += d;
    }

    public void add_y(final int d) {
        this.y += d;
    }

    public Position add(final Position position) {
        return new Position(x + position.x, y + position.y);
    }

    public static Position clone(final Position r) {
        return new Position(r.x, r.y);
    }

    public static Position valueOf(final String string) {
        final String[] split = string.split(",");
        return valueOf(split[0], split[1]);
    }

    public static Position valueOf(final String x, final String y) {
        return new Position(Integer.valueOf(x), Integer.valueOf(y));
    }

    public Position addY(final int y) {
        return add(Position.at(0, y));
    }

    public Position addX(final int x) {
        return add(Position.at(x, 0));
    }

    public Position addXY(final int x, final int y) {
        return add(Position.at(x, y));
    }

    public Position sub(final Position r) {
        return addXY(-r.x, -r.y);
    }

    @Override
    public int compareTo(final Position p) {
        if (x < p.x) {
            return -1;
        } else if (p.x > p.x) {
            return 1;
        } else {
            return 0;
        }
    }

}
