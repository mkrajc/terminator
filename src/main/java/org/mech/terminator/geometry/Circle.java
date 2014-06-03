package org.mech.terminator.geometry;

import java.util.ArrayList;
import java.util.List;

public class Circle {
	private Position center;
	private int radius;
	protected List<Position> list;

	public Circle(final Position center, final int radius) {
		this.center = center;
		this.radius = radius;

		list = new ArrayList<Position>();
		recount();
	}

	public List<Position> getPerimeter(final int radius) {
		final List<Position> ret = new ArrayList<Position>();

		if (radius == 0) {
			ret.add(center);
			return ret;
		}

		for (final Position p : list) {
			final float distPyth = GeometryUtils.distPyth(p, center);
			if (!GeometryUtils.isDistanceInCircle(distPyth, radius - 1) && GeometryUtils.isDistanceInCircle(distPyth, radius)) {
				ret.add(Position.clone(p));
			}
		}
		return ret;
	}

	public List<Position> getPerimeter() {
		return getPerimeter(getRadius());
	}

	protected void recount() {
		list.clear();

		final int startX = center.x - radius;
		final int startY = center.y - radius;
		final int endX = center.x + radius;
		final int endY = center.y + radius;

		for (int i = startX; i <= endX; i++) {
			for (int j = startY; j <= endY; j++) {
				if (positionBelongInCircle(i, j)) {
					list.add(Position.at(i, j));
				}
			}
		}
	}

	protected boolean positionBelongInCircle(final int i, final int j) {
		final float distPyth = GeometryUtils.distPyth(i, j, center.x, center.y);
		return GeometryUtils.isDistanceInCircle(distPyth, radius);
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

	public void setCenter(final Position center) {
		this.center = center;
		recount();
	}

	public void setRadius(final int radius) {
		this.radius = radius;
		recount();
	}

	public static void main(final String[] args) {
		final Position at = Position.at(10, 10);
		final Circle a = new Circle(at, 3);
		final Circle b = new Circle(at, 4);

		final List<Position> aperimeter = a.getPerimeter(3);
		final List<Position> bperimeter = b.getPerimeter(3);

		System.out.println(aperimeter.size() + " " + bperimeter.size());

	}
}
