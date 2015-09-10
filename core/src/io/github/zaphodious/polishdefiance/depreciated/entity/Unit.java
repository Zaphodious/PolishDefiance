package io.github.zaphodious.polishdefiance.depreciated.entity;


import io.github.zaphodious.polishdefiance.depreciated.gameboard.Tile;

public interface Unit extends Tile {

	public int getMaxLoyalty();

	public int getCurrentLoyalty();

	public int attack(Unit unit);

	public int takeDamage(int damage);

	public String getSymbol();

}
