package br.game.castleduel.unit;

import br.game.castleduel.Game;
import br.game.castleduel.gui.SpriteUnit;

public class Unit extends LivingBeing {
	public static final int RANGE_CLOSE = 100;
	private static int ID_GENERATOR = 0;
	private static long ATTACK_DELAY_FRAMES = 60; 
	public static final int ATTACK_TYPE_NORMAL = 0;
	public static final int ATTACK_TYPE_HIT_ALL = 1;

	public final int id;
	protected int type;
	protected int attack;
	protected int attackType;
	protected int range;
	protected int gold;
	protected int position = 0;
	protected long frameNextAttack = 0;
	private SpriteUnit sprite;

	protected Unit(int type, int gold, int health, int attack) {
		this(type, gold, health, attack, RANGE_CLOSE);
	}
	
	protected Unit(int type, int gold, int health, int attack, int range) {
		this(type, gold, health, attack, range, ATTACK_TYPE_NORMAL);
	}
	
	protected Unit(int type, int gold, int health, 
			int attack, int range, int attackType) {
		this.type = type;
		this.gold = gold;
		this.health = health;
		this.healthMax = health;
		this.attack = attack;
		this.attackType = attackType;
		this.range = range;
		id = ID_GENERATOR++;
	}

	protected Unit(Unit unit) {
		this.type = unit.type;
		this.health = unit.health;
		this.healthMax = unit.health;
		this.attack = unit.attack;
		this.attackType = unit.attackType;
		this.gold = unit.gold;
		this.range = unit.range;
		id = ID_GENERATOR++;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getAttackType() {
		return attackType;
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

	public boolean attackWithCooldown(LivingBeing enemy) {
		if (Game.getCURRENT_FRAME() >= frameNextAttack) {
			enemy.looseHealth(attack);
			frameNextAttack = Game.getCURRENT_FRAME() + ATTACK_DELAY_FRAMES;
			return true;
		}
		return false;
	}
	
	public boolean attackWithNoCooldown(LivingBeing enemy) {
		if (Game.getCURRENT_FRAME() >= frameNextAttack) {
			enemy.looseHealth(attack);
			return true;
		}
		return false;
	}
	
	public void updateCooldown() {
		frameNextAttack = Game.getCURRENT_FRAME() + ATTACK_DELAY_FRAMES;
	}

	public void setSprite(SpriteUnit sprite) {
		this.sprite = sprite;
	}
	
	public SpriteUnit getSprite() {
		return sprite;
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
