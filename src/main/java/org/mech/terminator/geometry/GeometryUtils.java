package org.mech.terminator.geometry;

import java.util.ArrayList;
import java.util.List;

public class GeometryUtils {

	public static boolean isPositionInDimension(Position p, Dimension dim) {
		return !(p.x >= dim.width || p.y >= dim.height) && p.x >= 0 && p.y >= 0;
	}

	public static int dist(Position a, Position b) {
		return dist(a.x, a.y, b.x, b.y);
	}

	public static int dist(int ax, int ay, int bx, int by) {
		return Math.max(Math.abs(ax - bx), Math.abs(ay - by));
	}

	public static float distPyth(Position a, Position b) {
		return distPyth(a.x, a.y, b.x, b.y);
	}

	public static float distPyth(int ax, int ay, int bx, int by) {
		return (float) Math.sqrt(Math.pow(Math.abs(ax - bx), 2) + Math.pow(Math.abs(ay - by), 2));
	}

	public static float distPyth(int dx, int dy) {
		return (float) Math.sqrt(Math.pow(Math.abs(dx), 2) + Math.pow(Math.abs(dy), 2));
	}

	public static boolean isDistanceInCircle(float distance, int radius) {
		return distance < radius + 0.5;
	}

	public static void main(String[] args) {
		System.out.println(distPyth(0, 0, 4, 4));
	}

	public static List<Position> bresenhamLine(int x0, int y0, int x1, int y1) {
		List<Position> line = new ArrayList<Position>();

		int dx = Math.abs(x1 - x0);
		int dy = Math.abs(y1 - y0);

		int sx = x0 < x1 ? 1 : -1;
		int sy = y0 < y1 ? 1 : -1;

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

	public static List<Position> bresenhamLine(Position a, Position b) {
		return bresenhamLine(a.x, a.y, b.x, b.y);
	}
}
