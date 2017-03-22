package br.game.castleduel.unit;

public class Unit {
	private static final int RANGE_CLOSE = 50;
	private static final int RANGE_FAR = RANGE_CLOSE * 3;
	private static int ID_GENERATOR = 0;

	public final int id;
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
		id = ID_GENERATOR++;
	}

	protected Unit(Unit unit) {
		this.health = unit.health;
		this.attack = unit.attack;
		this.gold = unit.gold;
		this.range = unit.range;
		id = ID_GENERATOR++;
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
	
	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Unit other = (Unit) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
