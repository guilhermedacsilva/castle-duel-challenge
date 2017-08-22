package br.game.castleduel.unit;

import br.game.castleduel.gui.sprite.SpriteUnit;

public class Unit extends Killable {
	private static long ATTACK_DELAY_FRAMES = 60;

	protected int type;
	protected int attack;
	protected int attackType;
	protected int range;
	protected int gold;
	protected int position = 0;
	protected long cooldown = 0;
	protected int playerIndex;
	protected SpriteUnit sprite;
	
	protected Unit(int type, int gold, int health, 
			int attack, int range, int attackType) {
		this.type = type;
		this.gold = gold;
		this.health = health;
		this.healthMax = health;
		this.attack = attack;
		this.attackType = attackType;
		this.range = range;
	}

	protected Unit(Unit unit) {
		this.type = unit.type;
		this.health = unit.health;
		this.healthMax = unit.health;
		this.attack = unit.attack;
		this.attackType = unit.attackType;
		this.gold = unit.gold;
		this.range = unit.range;
	}
	
	public int getAttack() {
		return attack;
	}
	
	public int getAttackType() {
		return attackType;
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
	
	protected void setPlayerIndex(int playerIndex) {
		this.playerIndex = playerIndex;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}

	public void attackWithCooldown(Killable enemy) {
		if (isReady()) {
			enemy.looseHealth(attack);
			setCooldown();
		}
	}
	
	public void attackWithNoCooldown(Killable enemy) {
		if (isReady()) {
			enemy.looseHealth(attack);
		}
	}
	
	public boolean isReady() {
		return cooldown == 0;
	}
	
	public boolean isOnCooldown() {
		return cooldown > 0;
	}
	
	public void setCooldown() {
		cooldown = ATTACK_DELAY_FRAMES;
	}
	
	public void decreaseCooldown() {
		if (cooldown > 0) {
			cooldown--;
		}
	}
	
	public SpriteUnit getSprite() {
		if (sprite == null) {
			sprite = new SpriteUnit(playerIndex, this);
		}
		return sprite;
	}

	public boolean isRanged() {
		return getRange() > UnitConsts.RANGE_CLOSE;
	}
}
