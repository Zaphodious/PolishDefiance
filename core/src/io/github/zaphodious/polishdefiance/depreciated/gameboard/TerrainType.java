package io.github.zaphodious.polishdefiance.depreciated.gameboard;

public enum TerrainType implements Terrain {

	GRASS("*"),
	MOUNTAIN("^"),
	RIVER("'"),
	OCEAN("\""),
	BEACH("`");

	String	symbol;

	TerrainType(String symbol) {
		this.symbol = symbol;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

}
