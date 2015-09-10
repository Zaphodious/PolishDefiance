package io.github.zaphodious.polishdefiance.depreciated.gameboard;

import io.github.zaphodious.polishdefiance.depreciated.entity.Unit;
import io.github.zaphodious.polishdefiance.depreciated.gameboard.Coords.Direction;
import io.github.zaphodious.polishdefiance.ZaphUtil;

import java.util.List;
import java.util.Map;

public class GameMap {
	private Map<Coords, Terrain>	map;
	private Map<Unit, Coords>		unitPositions;
	private final int				height;
	private final int				width;

	private GameMap(int height, int width) {
		map = ZaphUtil.newMap();
		unitPositions = ZaphUtil.newMap();
		this.height = height;
		this.width = width;
	}

	private void makeDefaultGameMap() {
		for (int x = 0; x < width; x++) {
			for (int y = 0; y < height; y++) {
				map.put(Coords.at(x, y), TerrainType.GRASS);
			}
		}
	}

	public boolean placeUnitOnMap(Coords coords, Unit unit) {
		if (unitPositions.containsKey(coords)) {
			return false;
		}
		unitPositions.put(unit, coords);
		return true;
	}

	public boolean moveUnit(Unit unit, Direction direction) {
		if (!unitPositions.containsKey(unit)) {
			return false;
		}
		Coords destination = getUnitPosition(unit).offSet(direction);
		if (hasUnitAt(destination)) {
			unit.attack(getUnitAt(destination));
			return false;
		}
		if (isOutOfBounds(destination)) {
			return false;
		}

		unitPositions.put(unit, destination);
		return true;
	}

	private boolean isOutOfBounds(Coords destination) {
		boolean outOfX = (destination.getX() < 0) || (destination.getX() >= getWidth());
		boolean outOfY = (destination.getY() < 0) || (destination.getY() >= getHeight());
		return outOfX || outOfY;
	}

	public int getHeight() {
		return height;
	}

	public int getWidth() {
		return width;
	}

	public Terrain getTerrainAt(Coords coords) {
		return map.get(coords);
	}

	public void setTerrainAt(Coords coords, Terrain terrain) {
		map.put(coords, terrain);
	}

	public Unit getUnitAt(Coords position) {
		Unit toReturn = null;
		for (Unit unit : unitPositions.keySet()) {
			if (unitPositions.get(unit).equals(position)) {
				toReturn = unit;
			}
		}
		return toReturn;
	}

	public boolean hasUnitAt(Coords position) {
		return unitPositions.containsValue(position);
	}

	public Coords getUnitPosition(Unit unit) {
		return unitPositions.get(unit);
	}

	public void cullDamagedUnits() {
		List<Unit> toRemove = ZaphUtil.newList();
		for (Unit unit : unitPositions.keySet()) {
			if (unit.getCurrentLoyalty() <= 0) {
				toRemove.add(unit);
			}
		}
		for (Unit unit : toRemove) {
			unitPositions.remove(unit);
		}
	}

	public String getMapAsString() {
		StringBuilder toReturn = new StringBuilder();
		for (int x = 0; x < width; x++) {
			toReturn.append("|");
			for (int y = 0; y < height; y++) {
				Coords position = Coords.at(x, y);
				String terrainSymbol = map.get(position).getSymbol();
				String unitSymbol = (hasUnitAt(position)) ? getUnitAt(position).getSymbol() : " ";
				toReturn.append(terrainSymbol + unitSymbol + "|");
			}
			toReturn.append("\n");
		}
		return toReturn.toString();
	}

	public Map<Unit, Coords> getUnitsOnMap() {
		Map<Unit, Coords> toReturn = ZaphUtil.newMap();
		for (Unit unit : unitPositions.keySet()) {
			toReturn.put(unit, unitPositions.get(unit));
		}
		return toReturn;
	}

	public Map<Coords, Terrain> getMapTerrain() {
		Map<Coords, Terrain> toReturn = ZaphUtil.newMap();
		for (Coords position : map.keySet()) {
			toReturn.put(position, map.get(position));
		}
		return toReturn;
	}

	public static GameMap newInstance(int sizeHeight, int sizeWidth) {
		GameMap toReturn = new GameMap(sizeHeight, sizeWidth);
		toReturn.makeDefaultGameMap();
		return toReturn;
	}
}
