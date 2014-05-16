package org.mech.terminator.geometry;

import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class Rectangle {
	protected Position corner1, corner2;

	public Rectangle(final Position start, final Dimension size) {
		this(start, new Position(start.x + size.width - 1, start.y + size.height - 1));
	}

	public Rectangle(final Position corner1, final Position corner2) {
		normalize(corner1, corner2);
	}

	public boolean isOut(final Position position) {
		return !isIn(position);
	}

	public boolean isIn(final Position position) {
		return corner1.x <= position.x && corner1.y <= position.y && corner2.x >= position.x && corner2.y >= position.y;
	}

	public Position toRelative(final Position abs) {
		if (isOut(abs)) {
			throw new IllegalArgumentException("Absolute point is out of rectangle [p=" + abs + ", rec=" + this + "]");
		}
		return abs.sub(corner1);
	}

	public Position toAbsolute(final Position rel) {
		if (getSize().isOut(rel)) {
			throw new IllegalArgumentException("Relative point is out of rectangle [p=" + rel + ", recSize=" + getSize() + "]");
		}

		return corner1.add(rel);
	}

	protected void normalize(final Position corner1, final Position corner2) {
		// on same axe
		if (corner1.x == corner2.x || corner1.y == corner2.y) {
			// throw new IllegalArgumentException("Not a rectangle");
		}

		// reverse order;
		final boolean swapCorners = corner1.x > corner2.x && corner1.y > corner2.y;

		if (corner1.x > corner2.x && corner1.y < corner2.y) {
			// transform coordinates
			final int swap = corner1.x;
			corner1.x = corner2.x;
			corner2.x = swap;
		} else if (corner1.x < corner2.x && corner1.y > corner2.y) {
			final int swap = corner1.y;
			corner1.y = corner2.y;
			corner2.y = swap;
		}

		this.corner1 = swapCorners ? corner2 : corner1;
		this.corner2 = swapCorners ? corner1 : corner2;
	}

	public int getHeight() {
		return corner2.y - corner1.y + 1;
	}

	public int getWidth() {
		return corner2.x - corner1.x + 1;
	}

	public Position getTopLeftPosition() {
		return corner1;
	}

	public Position getBottomRightPosition() {
		return corner2;
	}

	public Position getTopRightPosition() {
		return Position.at(corner2.x, corner1.y);
	}

	public Position getBottomLeftPosition() {
		return Position.at(corner1.x, corner2.y);
	}

	@Override
	public String toString() {
		return "rectangle [tl=" + corner1 + ", br=" + corner2 + "]";
	}

	public Dimension getSize() {
		return Dimension.of(getWidth(), getHeight());
	}

	public Line getLineUp() {
		return Line.createHorizontal(getTopLeftPosition(), getWidth());
	}

	public Line getLineDown() {
		return Line.createHorizontal(getTopLeftPosition().addY(getHeight() - 1), getWidth());
	}

	public Line getLineLeft() {
		return Line.createVertical(getTopLeftPosition(), getHeight());
	}

	public Line getLineRight() {
		return Line.createVertical(getTopLeftPosition().addX(getWidth() - 1), getHeight());
	}

	public Collection<Position> getPerimeter() {
		final Set<Position> positions = new HashSet<Position>();

		positions.addAll(getLineUp().getPoints());
		positions.addAll(getLineDown().getPoints());
		positions.addAll(getLineRight().getPoints());
		positions.addAll(getLineLeft().getPoints());

		return positions;
	}

	public Collection<Position> getPoints() {
		final Set<Position> positions = new HashSet<Position>();
		final Position start = getTopLeftPosition();
		final Position end = getBottomRightPosition();

		for (int i = start.x; i <= end.x; i++) {
			for (int j = start.y; j <= end.y; j++) {
				positions.add(Position.at(i, j));
			}
		}
		return positions;
	}

}
