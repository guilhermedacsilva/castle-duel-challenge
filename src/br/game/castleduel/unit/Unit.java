package br.game.castleduel.unit;

public class Unit extends LivingBeing {
	private static final int RANGE_CLOSE = 100;
	private static final int RANGE_FAR = 220;
	private static int ID_GENERATOR = 0;
	private static long ATTACK_DELAY_TIME = 1500; // milliseconds 

	public final int id;
	protected int type;
	protected int attack;
	protected int range;
	protected int gold;
	protected int position = 0;
	protected long timeNextAttack = 0;

	protected Unit(int type, int gold, int health, int attack) {
		this(type, gold, health, attack, false);
	}
	
	protected Unit(int type, int gold, int health, int attack, boolean ranged) {
		this.type = type;
		this.gold = gold;
		this.health = health;
		this.attack = attack;
		this.range = ranged ? RANGE_FAR : RANGE_CLOSE;
		id = ID_GENERATOR++;
	}

	protected Unit(Unit unit) {
		this.type = unit.type;
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
	
	public int getRange() {
		return range;
	}
	
	public int getPosition() {
		return position;
	}
	
	public int getType() {
		return type;
	}
	
	public void walk() {
		position++;
	}

	public void attackWithCooldown(LivingBeing enemy) {
		if (System.currentTimeMillis() >= timeNextAttack) {
			enemy.looseHealth(attack);
			timeNextAttack = System.currentTimeMillis() + ATTACK_DELAY_TIME;
		}
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
