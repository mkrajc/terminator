package org.mech.terminator.geometry;

public class NestedRectangle extends Rectangle {

	private final Rectangle parentRectangle;

	public NestedRectangle(final Position start, final Dimension size, final Rectangle parent) {
		super(start, size);
		this.parentRectangle = parent;

		if (GeometryUtils.intersect(this, parent) == null) {
			throw new IllegalArgumentException("Rectangles not intersecting");
		}

	}

	public NestedRectangle(final Position start, final Dimension size, final Dimension parent) {
		this(start, size, new Rectangle(start, parent));
	}

	public Position outerRelToRel(final Position position) {
		return toRelative(parentRectangle.toAbsolute(position));
	}

	public Position outerAbsToRel(final Position position) {
		return toRelative(position);
	}

	public Position innerRelToRel(final Position position) {
		return innerAbsToRel(toAbsolute(position));
	}

	public Position innerAbsToRel(final Position position) {
		return parentRectangle.toRelative(position);
	}

	@Override
	public String toString() {
		return super.toString() + " of " + parentRectangle;
	}

	public Rectangle intersect() {
		return GeometryUtils.intersect(this, parentRectangle);
	}
}
