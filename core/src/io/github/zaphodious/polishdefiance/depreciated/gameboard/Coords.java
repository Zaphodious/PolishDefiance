package io.github.zaphodious.polishdefiance.depreciated.gameboard;

public class Coords {

	private int	x;
	private int	y;

	private Coords(int x, int y) {
		this.x = x;
		this.y = y;
	}

	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public Coords north() {
		return Coords.at(getX() + 1, getY());
	}

	public Coords south() {
		return Coords.at(getX() - 1, getY());
	}

	public Coords east() {
		return Coords.at(getX(), getY() + 1);
	}

	public Coords west() {
		return Coords.at(getX(), getY() + 1);
	}

	public Coords offSet(Direction direction) {
		return direction.getOffset(this);
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof Coords)) {
			return false;
		}
		return hashCode() == obj.hashCode();
	}

	@Override
	public int hashCode() {
		return toString().hashCode();
	}

	@Override
	public String toString() {
		return "X = " + getX() + " Y = " + getY();
	}

	public static Coords at(int x, int y) {
		return new Coords(x, y);
	}

	public enum Direction {
		NORTH(1, 0),
		SOUTH(-1, 0),
		EAST(0, 1),
		WEST(0, -1);

		private int	offsetX;
		private int	offsetY;

		Direction(int offsetX, int offsetY) {
			this.offsetX = offsetX;
			this.offsetY = offsetY;
		}

		private Coords getOffset(Coords coords) {
			return Coords.at(coords.getX() + offsetX, coords.getY() + offsetY);
		}

	}

}
