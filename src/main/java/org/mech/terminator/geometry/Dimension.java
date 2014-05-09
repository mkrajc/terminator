package org.mech.terminator.geometry;

import java.util.Random;

public class Dimension {
	public int width, height;

	public Dimension(int width, int height) {
		this.width = width;
		this.height = height;
	}

	@Override
	public String toString() {
		return width + "x" + height;
	}

	public static Dimension of(int width, int height) {
		return new Dimension(width, height);
	}

	public boolean isOut(Position position) {
		return !isIn(position);
	}

	public boolean isIn(Position position) {
		return position.x >= 0 && position.x < width && position.y >= 0 && position.y < height;
	}

	public Position getRandom() {
		final Random r = new Random(System.nanoTime());
		return Position.at(r.nextInt(width), r.nextInt(height));
	}

	public static Dimension clone(Dimension dimension) {
		return new Dimension(dimension.width, dimension.height);
	}

	@Override
	public boolean equals(Object obj) {
		Dimension dim = (Dimension) obj;
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

	public Dimension addXY(int x, int y) {
		return Dimension.of(width + x, height + y);
	}

}
