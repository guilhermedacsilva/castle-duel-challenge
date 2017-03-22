package br.game.castleduel.unit;

public class Castle {
	protected int health = 120;

	public void looseHealth(int attack) {
		health -= attack;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
}