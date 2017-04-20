package br.game.castleduel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.game.castleduel.gui.GuiInterface;
import br.game.castleduel.gui.sprite.Sprite;
import br.game.castleduel.gui.sprite.SpriteCastle;
import br.game.castleduel.gui.sprite.SpriteExplosion;
import br.game.castleduel.player.PlayerInfo;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;
import br.game.castleduel.util.Matrix;

public class Battleground {
	public static final int BATTLEGROUND_WIDTH = 800;
	public static final int CASTLE_POSITION = 750;
	private static final int QNT_PLAYERS = 2;

	protected GuiInterface gui;
	protected List<List<Unit>> units = new ArrayList<List<Unit>>(QNT_PLAYERS);
	protected Castle[] castles = new Castle[QNT_PLAYERS];
	protected int[][] unitFrequency = new int[QNT_PLAYERS][6];
	
	public Battleground(GuiInterface gui) {
		this.gui = gui;
		for (int index = 0; index < QNT_PLAYERS; index++) {
			castles[index] = new Castle(index);
			units.add(new ArrayList<Unit>(20));	
		}
		gui.init(BATTLEGROUND_WIDTH, castles[0], castles[1]);
		calculateUnitFrequency();
	}
	
	private void calculateUnitFrequency() {
		Matrix.reset(unitFrequency);
		for (int playerIndex = 0; playerIndex < QNT_PLAYERS; playerIndex++) {
			for (Unit unit : units.get(playerIndex)) {
				unitFrequency[playerIndex][unit.getType()]++;
			}
		}
	}
	
	public PlayerInfo getPlayerInfo(int playerIndex) {
		final int enemyIndex = getEnemyIndex(playerIndex);
		return new PlayerInfo(
				playerIndex,
				unitFrequency[playerIndex],
				unitFrequency[enemyIndex],
				castles[playerIndex].getHealth(),
				castles[enemyIndex].getHealth()
		);
	}
	
	protected int getEnemyIndex(int playerIndex) {
		return 1 - playerIndex;
	}

	public boolean isFinished() {
		return castles[0].isDead() || castles[1].isDead();
	}
	
	public void addUnit(Unit unit) {
		units.get(unit.getPlayerIndex()).add(unit);
	}

	public void executeBattle() {
		attackAndWalkAllUnits();
		removeDeads();
		decreaseCooldowns();
		calculateUnitFrequency();
	}
	
	protected void attackAndWalkAllUnits() {
		for (int playerIndex = 0; playerIndex < QNT_PLAYERS; playerIndex++) {
			walkUnitsFrom(playerIndex);
		}
		for (int playerIndex = 0; playerIndex < QNT_PLAYERS; playerIndex++) {
			attackUnitsFrom(playerIndex);
		}
	}
	
	protected void walkUnitsFrom(int playerIndex) {
		for (Unit unit : units.get(playerIndex)) {
			if (!isEnemyInAttackRange(unit)) {
				unit.walk();
			}
		}
	}
	
	protected boolean isEnemyInAttackRange(Unit attackingUnit) {
		int enemyIndex = getEnemyIndex(attackingUnit.getPlayerIndex());
		for (Unit enemy : units.get(enemyIndex)) {
			if (isInAttackRange(attackingUnit, enemy)) {
				return true;
			}
		}
		return isInCastleRange(attackingUnit);
	}
	
	protected void attackUnitsFrom(int playerIndex) {
		final int enemyIndex = getEnemyIndex(playerIndex);
		for (Unit attackingUnit : units.get(playerIndex)) {
			tryAttackEnemy(attackingUnit, units.get(enemyIndex));
			tryAttackCastle(attackingUnit, castles[enemyIndex]);
		}
	}

	protected void tryAttackEnemy(
			Unit unit, 
			List<Unit> enemies
			) {
		
		if (unit.isReady()) {
			if (Unit.ATTACK_TYPE_NORMAL == unit.getAttackType()) {
				tryAttackNormalHit(unit, enemies);
			} else {
				tryAttackHitAll(unit, enemies);
			}
		}
	}
	
	protected boolean tryAttackNormalHit(
			Unit unit, 
			List<Unit> enemies
			) {
		boolean hit = false;
		for (Unit enemy : enemies) {
			if (isInAttackRange(unit, enemy) && !enemy.isDead()) {
				hit = unit.attackWithCooldown(enemy);
				if (hit) {
					gui.addSprite(new SpriteExplosion(enemy.getSprite()));
				}
				return true;
			}
		}
		return false;
	}
	
	protected boolean tryAttackHitAll(
			Unit unit, 
			List<Unit> enemies
			) {
		boolean enemyInRange = false;
		boolean hit = false;
		for (Unit enemy : enemies) {
			if (isInAttackRange(unit, enemy) && !enemy.isDead()) {
				enemyInRange = true;
				if (unit.attackWithNoCooldown(enemy)) {
					hit = true;
					gui.addSprite(new SpriteExplosion(enemy.getSprite()));
				}
			}
		}
		if (hit) {
			unit.setCooldown();
		}
		return enemyInRange;
	}
	
	protected static boolean isInAttackRange(Unit unit, Unit enemy) {
		int distance = BATTLEGROUND_WIDTH 
				- unit.getPosition() 
				- enemy.getPosition();
		
		return distance <= unit.getRange();
	}
	
	protected boolean tryAttackCastle(
			Unit unit, 
			Castle castle
			) {
		if (isInCastleRange(unit)) {
			if (unit.attackWithCooldown(castle)) {
				gui.addSprite(createCastleExplosionSprite(castle));
			}
			return true;
		}
		return false;
	}
	
	protected boolean isInCastleRange(Unit unit) {
		int distance = CASTLE_POSITION - unit.getPosition();
		return distance <= unit.getRange();
	}
	
	protected SpriteExplosion createCastleExplosionSprite(Castle castle) {
		Sprite target = SpriteCastle.getSprite(castle.getPlayerIndex());
		return new SpriteExplosion(target);
	}
	
	protected void removeDeads() {
		Iterator<Unit> iterator;
		Unit unit;
		
		for (List<Unit> playerUnits : units) {
			iterator = playerUnits.iterator();
			while (iterator.hasNext()) {
				unit = iterator.next();
				if (unit.isDead()) {
					iterator.remove();
				}
			}
		}
	}
	
	protected void decreaseCooldowns() {
		for (List<Unit> playerUnits : units) {
			for (Unit unit : playerUnits) {
				unit.decreaseCooldown();
			}
		}
	}
	
}
