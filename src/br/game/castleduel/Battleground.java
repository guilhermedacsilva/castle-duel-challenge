package br.game.castleduel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.game.castleduel.gui.GuiInterface;
import br.game.castleduel.gui.sprite.SpriteCastle;
import br.game.castleduel.gui.sprite.SpriteExplosion;
import br.game.castleduel.player.PlayerInfo;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;
import br.game.castleduel.unit.UnitManager;

public class Battleground {
	public static final int BATTLEGROUND_WIDTH = 800;
	public static final int CASTLE_POSITION = 750;
	private static final int P1_INDEX = 0;
	private static final int P2_INDEX = 1;

	protected GuiInterface gui;
	protected List<List<Unit>> units = new ArrayList<List<Unit>>(2);
	protected Castle[] castles = new Castle[2];
	protected int[] goldArray = new int[] {0, 0};
	protected int[][] unitCountMatrix = new int[2][6];
	
	public Battleground(GuiInterface gui) {
		castles[0] = new Castle(1);
		castles[1] = new Castle(2);
		units.add(new ArrayList<Unit>(100));
		units.add(new ArrayList<Unit>(100));
		gui.init(BATTLEGROUND_WIDTH, castles[0], castles[1]);
		this.gui = gui;
		updateUnitCountMatrix();
	}
	
	public PlayerInfo getPlayerInfo(int playerIndex) {
		final int enemyIndex = 1 - playerIndex;
		return new PlayerInfo(
				playerIndex,
				goldArray[playerIndex],
				unitCountMatrix[playerIndex],
				unitCountMatrix[enemyIndex],
				castles[playerIndex].getHealth(),
				castles[enemyIndex].getHealth()
		);
	}
	
	private void updateUnitCountMatrix() {
		for (int i = 0; i < unitCountMatrix.length; i++) {
			for (int j = 0; j < unitCountMatrix[i].length; j++) {
				unitCountMatrix[i][j] = 0;
			}
		}
		List<Unit> playerUnits;
		for (int playerIndex = 0; playerIndex < units.size(); playerIndex++) {
			playerUnits = units.get(playerIndex);
			for (Unit unit : playerUnits) {
				unitCountMatrix[playerIndex][unit.getType()]++;
			}
		}
	}
	
	public void gainGold() {
		goldArray[P1_INDEX]++;
		goldArray[P2_INDEX]++;
	}

	public boolean isFinished() {
		return castles[0].isDead() || castles[1].isDead();
	}
	
	public void addUnitFromPlayer(int unitIndex, int playerIndex) {
		if (UnitManager.isValidIndex(unitIndex)
				&& UnitManager.getCost(unitIndex) <= goldArray[playerIndex]) {
			
			goldArray[playerIndex] -= UnitManager.getCost(unitIndex);
			Unit unit = UnitManager.createUnit(unitIndex);
			getUnitsFromPlayer(playerIndex+1).add(unit);
			gui.addSprite(playerIndex+1, unit);
		}
	}
	
	protected List<Unit> getUnitsFromPlayer(int player) {
		return units.get(player-1);
	}

	public void executeBattle() {
		attackOrWalk(units.get(P1_INDEX), units.get(P2_INDEX), castles[1]);
		attackOrWalk(units.get(P2_INDEX), units.get(P1_INDEX), castles[0]);
		attack(units.get(P1_INDEX), units.get(P2_INDEX), castles[1]);
		attack(units.get(P2_INDEX), units.get(P1_INDEX), castles[0]);
		
		removeDeads();
		decreaseCooldowns();
		gui.setGold(goldArray[0], goldArray[1]);
		updateUnitCountMatrix();
	}
	
	protected void attackOrWalk(
			List<Unit> attackingUnits, 
			List<Unit> enemies, 
			Castle enemyCastle
	) {
		for (Unit attackingUnit : attackingUnits) {
			if (tryAttackEnemy(attackingUnit, enemies)
					|| tryAttackCastle(attackingUnit, enemyCastle)) {
				continue;
			}
			attackingUnit.walk();
		}
	}
	
	protected void attack(
			List<Unit> attackingUnits, 
			List<Unit> enemies, 
			Castle enemyCastle
	) {
		for (Unit attackingUnit : attackingUnits) {
			if (tryAttackEnemy(attackingUnit, enemies)
					|| tryAttackCastle(attackingUnit, enemyCastle)) {
				
			}
		}
	}

	protected boolean tryAttackEnemy(
			Unit unit, 
			List<Unit> enemies
			) {
		
		if (Unit.ATTACK_TYPE_NORMAL == unit.getAttackType()) {
			return tryAttackNormalHit(unit, enemies);
		} else {
			return tryAttackHitAll(unit, enemies);
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
	
	protected static boolean isInAttackRange(
			Unit unit, 
			Unit enemy
			) {
		int distance = BATTLEGROUND_WIDTH 
				- unit.getPosition() 
				- enemy.getPosition();
		
		return distance <= unit.getRange();
	}
	
	protected boolean tryAttackCastle(
			Unit unit, 
			Castle castle
			) {
		int distance = CASTLE_POSITION - unit.getPosition();
		if (distance <= unit.getRange()) {
			if (unit.attackWithCooldown(castle)) {
				gui.addSprite();
			}
			return true;
		}
		return false;
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
