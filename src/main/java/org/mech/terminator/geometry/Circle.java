package org.mech.terminator.geometry;

import java.util.ArrayList;
import java.util.List;

public class Circle {
	private Position center;
	private int radius;
	private List<Position> list;

	public Circle(Position center, int radius) {
		this.center = center;
		this.radius = radius;

		list = new ArrayList<Position>();
		recount();
	}

	public List<Position> getPerimeter(int radius) {
		List<Position> ret = new ArrayList<Position>();

		if (radius == 0) {
			ret.add(center);
			return ret;
		}

		for (Position p : list) {
			float distPyth = GeometryUtils.distPyth(p, center);
			if (!GeometryUtils.isDistanceInCircle(distPyth, radius - 1) && GeometryUtils.isDistanceInCircle(distPyth, radius)) {
				ret.add(Position.clone(p));
			}
		}
		return ret;
	}

	public List<Position> getPerimeter() {
		return getPerimeter(getRadius());
	}

	private void recount() {
		list.clear();

		int startX = center.x - radius;
		int startY = center.y - radius;
		int endX = center.x + radius;
		int endY = center.y + radius;

		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				float distPyth = GeometryUtils.distPyth(i, j, center.x, center.y);
				if (GeometryUtils.isDistanceInCircle(distPyth, radius)) {
					list.add(Position.at(i, j));
				}
			}
		}
	}

	@Override
	public String toString() {
		return "circle center=" + center + ", r=" + radius;
	}

	public Position getCenter() {
		return center;
	}

	public int getRadius() {
		return radius;
	}

	public List<Position> getPoints() {
		return list;
	}

	public void setCenter(Position center) {
		this.center = center;
		recount();
	}

	public void setRadius(int radius) {
		this.radius = radius;
		recount();
	}

	public static void main(String[] args) {
		Position at = Position.at(10, 10);
		Circle a = new Circle(at, 3);
		Circle b = new Circle(at, 4);

		List<Position> aperimeter = a.getPerimeter(3);
		List<Position> bperimeter = b.getPerimeter(3);

		System.out.println(aperimeter.size() + " " + bperimeter.size());

	}
}
