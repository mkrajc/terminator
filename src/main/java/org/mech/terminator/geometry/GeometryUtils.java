package org.mech.terminator.geometry;

import java.util.ArrayList;
import java.util.List;

public class GeometryUtils {

    public static Rectangle intersect(final Rectangle r1, final Rectangle r2) {
        final Rectangle i = tryIntersection(r1, r2);
        return i == null ? tryIntersection(r2, r1) : i;
    }

    private static Rectangle tryIntersection(final Rectangle r1, final Rectangle r2) {

        final boolean bl = r2.isIn(r1.getBottomLeftPosition());
        final boolean br = r2.isIn(r1.getBottomRightPosition());
        final boolean tl = r2.isIn(r1.getTopLeftPosition());
        final boolean tr = r2.isIn(r1.getTopRightPosition());

        final boolean hasIntersection = bl || br || tl || tr;

        if (hasIntersection) {
            // all corners inside
            if (bl && br && tl && tr) {
                return new Rectangle(r1.corner1, r1.corner2);
            }

            if (bl && br) {
                return new Rectangle(Position.at(r1.getBottomLeftPosition().x, r2.getTopLeftPosition().y), r1.getBottomRightPosition());
            }

            if (tl && tr) {
                return new Rectangle(r1.getTopLeftPosition(), Position.at(r1.getTopRightPosition().x, r2.getBottomRightPosition().y));
            }

            if (tl && bl) {
                return new Rectangle(r1.getTopLeftPosition(), Position.at(r2.getBottomRightPosition().x, r1.getBottomRightPosition().y));
            }

            if (tr && br) {
                return new Rectangle(Position.at(r2.getTopLeftPosition().x, r1.getTopLeftPosition().y), r1.getBottomRightPosition());
            }

            if (br) {
                return new Rectangle(r2.getTopLeftPosition(), r1.getBottomRightPosition());
            }

            if (tl) {
                return new Rectangle(r1.getTopLeftPosition(), r2.getBottomRightPosition());
            }

            if (bl) {
                return new Rectangle(Position.at(r1.getTopLeftPosition().x, r2.getTopLeftPosition().y), Position.at(r2.getBottomRightPosition().x,
                        r1.getBottomRightPosition().y));
            }

            if (tr) {
                return new Rectangle(Position.at(r2.getTopLeftPosition().x, r1.getTopLeftPosition().y), Position.at(r1.getBottomRightPosition().x,
                        r2.getBottomRightPosition().y));
            }
        } else {
            if (r1.getTopLeftPosition().x >= r2.getTopLeftPosition().x && r1.getTopLeftPosition().x <= r2.getBottomRightPosition().x
                    && r2.getTopLeftPosition().y >= r1.getTopLeftPosition().y && r2.getTopLeftPosition().y <= r1.getBottomRightPosition().y) {
                return new Rectangle(Position.at(r1.getTopLeftPosition().x, r2.getTopLeftPosition().y), Position.at(r1.getBottomRightPosition().x,
                        r2.getBottomRightPosition().y));
            }
        }

        return null;

    }

    public static int dist(final Position a, final Position b) {
        return dist(a.x, a.y, b.x, b.y);
    }

    public static int dist(final int ax, final int ay, final int bx, final int by) {
        return Math.max(Math.abs(ax - bx), Math.abs(ay - by));
    }

    public static float distPyth(final Position a, final Position b) {
        return distPyth(a.x, a.y, b.x, b.y);
    }

    public static float distPyth(final int ax, final int ay, final int bx, final int by) {
        return (float) Math.sqrt(Math.pow(Math.abs(ax - bx), 2) + Math.pow(Math.abs(ay - by), 2));
    }

    public static float distPyth(final int dx, final int dy) {
        return (float) Math.sqrt(Math.pow(Math.abs(dx), 2) + Math.pow(Math.abs(dy), 2));
    }

    public static boolean isDistanceInCircle(final float distance, final int radius) {
        return distance < radius + 0.5;
    }

    public static void main(final String[] args) {
        System.out.println(distPyth(0, 0, 4, 4));
    }

    public static List<Position> bresenhamLine(final int x0, final int y0, final int x1, final int y1) {
        final List<Position> line = new ArrayList<Position>();

        final int dx = Math.abs(x1 - x0);
        final int dy = Math.abs(y1 - y0);

        final int sx = x0 < x1 ? 1 : -1;
        final int sy = y0 < y1 ? 1 : -1;

        int err = dx - dy;
        int e2;
        int currentX = x0;
        int currentY = y0;

        while (true) {
            line.add(Position.at(currentX, currentY));

            if (currentX == x1 && currentY == y1) {
                break;
            }

            e2 = 2 * err;
            if (e2 > -1 * dy) {
                err = err - dy;
                currentX = currentX + sx;
            }

            if (e2 < dx) {
                err = err + dx;
                currentY = currentY + sy;
            }
        }

        return line;
    }

    public static List<Position> bresenhamLine(final Position a, final Position b) {
        return bresenhamLine(a.x, a.y, b.x, b.y);
    }
}
