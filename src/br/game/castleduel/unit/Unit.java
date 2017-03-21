package br.game.castleduel.unit;

public class Unit {
	private static final int RANGE_CLOSE = 50;
	private static final int RANGE_FAR = RANGE_CLOSE * 3;

	protected int health;
	protected int attack;
	protected int range;
	protected int gold;
	protected int position = 0;

	protected Unit(int gold, int health, int attack) {
		this(gold, health, attack, false);
	}
	
	protected Unit(int gold, int health, int attack, boolean ranged) {
		this.gold = gold;
		this.health = health;
		this.attack = attack;
		this.range = ranged ? RANGE_FAR : RANGE_CLOSE;
	}

	protected Unit(Unit unit) {
		this.health = unit.health;
		this.attack = unit.attack;
		this.gold = unit.gold;
		this.range = unit.range;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getHealth() {
		return health;
	}
	
	public boolean isDead() {
		return health <= 0;
	}
	
	public int getRange() {
		return range;
	}
	
	public int getPosition() {
		return position;
	}
	
	public void walk() {
		position++;
	}

	public void attack(Unit enemy) {
		enemy.health -= attack;
	}
}
