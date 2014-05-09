package org.mech.terminator.geometry;

public class Position {
	public int x, y;

	public Position(int x, int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public boolean equals(Object obj) {
		Position p = (Position) obj;
		return x == p.x && y == p.y;
	}

	@Override
	public int hashCode() {
		int hash = 23;
		hash = hash * 31 + x;
		hash = hash * 31 + y;
		return hash;
	}

	@Override
	public String toString() {
		return x + "," + y;
	}

	public static Position at(int x, int y) {
		return new Position(x, y);
	}

	public void add_(Position position) {
		this.x += position.x;
		this.y += position.y;
	}

	public void add_x(int d) {
		this.x += d;
	}

	public void add_y(int d) {
		this.y += d;
	}

	public Position add(Position position) {
		return new Position(x + position.x, y + position.y);
	}

	public static Position clone(Position r) {
		return new Position(r.x, r.y);
	}

	public static Position valueOf(String string) {
		String[] split = string.split(",");
		return valueOf(split[0], split[1]);
	}

	public static Position valueOf(String x, String y) {
		return new Position(Integer.valueOf(x),Integer.valueOf(y));
	}
	
	public Position addY(int y) {
		return add(Position.at(0, y));
	}

	public Position addX(int x) {
		return add(Position.at(x, 0));
	}

	public Position addXY(int x, int y) {
		return add(Position.at(x, y));
	}

	public Position sub(Position r) {
		return addXY(-r.x, -r.y);
	}

}
