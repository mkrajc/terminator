package org.mech.terminator.geometry;

import java.util.List;

public class Line {

    private List<Position> linePoints;

    public Line(Position start, Position end) {
        linePoints = GeometryUtils.bresenhamLine(start.x, start.y, end.x, end.y);
    }

    public List<Position> getPoints() {
        return linePoints;
    }

    public int getLength() {
        return linePoints.size();
    }

    public Position getStart() {
        return linePoints.get(0);
    }

    public Position getEnd() {
        return linePoints.get(getLength() - 1);
    }

    public static Line createHorizontal(Position start, int size) {
        return new Line(start, Position.at(start.x + size - 1, start.y));
    }

    public static Line createVertical(Position start, int size) {
        return new Line(start, Position.at(start.x, start.y + size - 1));
    }

    @Override
    public String toString() {
        return getStart() + "-" + getEnd();
    }

}
