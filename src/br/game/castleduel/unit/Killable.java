package br.game.castleduel.unit;

import br.game.castleduel.util.UniqueId;

public abstract class Killable extends UniqueId {
	protected int healthMax;
	protected int health;
	
	public void looseHealth(int attack) {
		health -= attack;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public double getHealthPercentage() {
		return health * 1.0 / healthMax;
	}
}
