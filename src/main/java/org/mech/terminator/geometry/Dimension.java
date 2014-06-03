package org.mech.terminator.geometry;

import java.io.Serializable;
import java.util.Random;

public class Dimension implements Serializable {
	private static final long serialVersionUID = 3076178816949442612L;
	public int width, height;

	public Dimension(final int width, final int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return width + "x" + height;
	}

	public static Dimension of(final int width, final int height) {
		return new Dimension(width, height);
	}
	
	public static Dimension of(final int size) {
		return new Dimension(size, size);
	}

	public boolean isOut(final Position position) {
		return !isIn(position);
	}

	public boolean isIn(final Position position) {
		return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
	}

	// return dimension with size same or less then this
	public Dimension intersectWith(final Dimension dim) {
		return Dimension.of((dim.width <= 0) ? width : Math.min(width, dim.width), (dim.height <= 0) ? height : Math.min(height, dim.height));
	}

	public Position getRandom() {
		final Random r = new Random(System.nanoTime());
		return Position.at(r.nextInt(width), r.nextInt(height));
	}

	public static Dimension clone(final Dimension dimension) {
		return new Dimension(dimension.width, dimension.height);
	}

	@Override
	public boolean equals(final Object obj) {
		final Dimension dim = (Dimension) obj;
		return dim.width == width && dim.height == height;
	}

	public int getArea() {
		return width * height;
	}

	public Integer getWidth() {
		return width <= 0 ? null : width;
	}

	public Integer getHeight() {
		return height <= 0 ? null : height;
	}

	public Position toPosition() {
		return Position.at(width, height);
	}

	public Rectangle toRectangle() {
		return new Rectangle(Position.at(0, 0), clone(this));
	}

	public Dimension addXY(final int x, final int y) {
		return Dimension.of(width + x, height + y);
	}

}
