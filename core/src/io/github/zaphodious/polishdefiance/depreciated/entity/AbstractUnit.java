package io.github.zaphodious.polishdefiance.depreciated.entity;

public abstract class AbstractUnit implements Unit {

	private String	symbol;
	private int		maxLoyalty;
	private int		currentLoyalty;

	protected AbstractUnit(String symbol) {
		this.symbol = symbol;
		maxLoyalty = 10;
		currentLoyalty = maxLoyalty;
	}

	@Override
	public String getSymbol() {
		return symbol;
	}

	@Override
	public int attack(Unit entity) {
		return entity.takeDamage(4);
	}

	@Override
	public int takeDamage(int damage) {
		currentLoyalty -= damage;
		return damage;
	}

	@Override
	public int getMaxLoyalty() {
		return maxLoyalty;
	}

	@Override
	public int getCurrentLoyalty() {
		return currentLoyalty;
	}

	@Override
	public boolean equals(Object obj) {
		// TODO Auto-generated method stub
		return super.equals(obj);
	}

	@Override
	public int hashCode() {
		// TODO Auto-generated method stub
		return super.hashCode();
	}

}
