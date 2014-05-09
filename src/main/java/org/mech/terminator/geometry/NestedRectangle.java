package org.mech.terminator.geometry;

public class NestedRectangle extends Rectangle {

	private Dimension parentDimension;

	public NestedRectangle(Position start, Dimension size, Dimension parent) {
		super(start, size);
		this.parentDimension = parent;

		validate();
	}

	public Position outerToInner(Position position) {
		if (!GeometryUtils.isPositionInDimension(position, parentDimension)) {
			throw new IllegalArgumentException("Position not from inner rectangle [pos=" + position + ", innerRectangle=" + getSize() + "]");
		}

		int topX = getTopLeftPosition().x;
		int topY = getTopLeftPosition().y;
		if (position.x < topX || position.x > getBottomRightPosition().x || position.y < topY || position.y > getBottomRightPosition().y) {
			return null;
		}

		int x = position.x - topX;
		int y = position.y - topY;

		return Position.at(x, y);
	}

	public Position innerToOuter(Position position) {
		if (!GeometryUtils.isPositionInDimension(position, getSize())) {
			throw new IllegalArgumentException("Position not from inner rectangle [pos=" + position + ", innerRectangle=" + getSize() + "]");
		}

		int topX = getTopLeftPosition().x;
		int topY = getTopLeftPosition().y;

		int x = position.x + topX;
		int y = position.y + topY;

		return Position.at(x, y);
	}

	private void validate() {
		validatePosition(getTopLeftPosition());
		validatePosition(getBottomRightPosition());
	}

	public void setParentDimension(Dimension parent) {
		this.parentDimension = parent;
		validate();
	}

	private void validatePosition(Position p) {
		if (p.x > parentDimension.width || p.y > parentDimension.height) {
			throw new IllegalArgumentException("Point of rectangle is out of parent");
		}
	}

	@Override
	public String toString() {
		return super.toString() + " of " + parentDimension;
	}
}
