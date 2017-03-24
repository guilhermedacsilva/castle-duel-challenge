package br.game.castleduel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.game.castleduel.gui.GuiInterface;
import br.game.castleduel.gui.SpriteExplosion;
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
	protected List<Castle> castles = new ArrayList<Castle>(2);
	protected int[] goldArray = new int[] {0, 0};
	protected int[][] unitCountMatrix = new int[2][6];
	
	public Battleground(GuiInterface gui) {
		castles.add(new Castle(1));
		castles.add(new Castle(2));
		units.add(new ArrayList<Unit>(100));
		units.add(new ArrayList<Unit>(100));
		gui.init(BATTLEGROUND_WIDTH, castles.get(P1_INDEX), castles.get(P2_INDEX));
		this.gui = gui;
	}
	
	public void gainGold() {
		goldArray[P1_INDEX]++;
		goldArray[P2_INDEX]++;
	}

	public int getGold(int player) {
		return goldArray[player-1];
	}

	public int[] getUnits(int player) {
		updateUnitCountMatrix();
		return unitCountMatrix[player-1];
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

	public boolean isFinished() {
		return castles.get(P1_INDEX).isDead() || castles.get(P2_INDEX).isDead();
	}
	
	public int getCastle1Health() {
		return castles.get(P1_INDEX).getHealth();
	}
	
	public int getCastle2Health() {
		return castles.get(P2_INDEX).getHealth();
	}
	
	public void addUnitFromPlayer(int unitIndex, int player) {
		final int playerIndex = player - 1;
		if (UnitManager.isValidIndex(unitIndex)
				&& UnitManager.getCost(unitIndex) <= goldArray[playerIndex]) {
			
			goldArray[playerIndex] -= UnitManager.getCost(unitIndex);
			Unit unit = UnitManager.createUnit(unitIndex);
			getUnitsFromPlayer(player).add(unit);
			gui.addSprite(player, unit);
		}
	}
	
	protected List<Unit> getUnitsFromPlayer(int player) {
		return units.get(player-1);
	}

	public void executeBattle() {
		attackOtherTeam(units.get(P1_INDEX), units.get(P2_INDEX), castles.get(P2_INDEX));
		attackOtherTeam(units.get(P2_INDEX), units.get(P1_INDEX), castles.get(P1_INDEX));
		removeDeads();
	}
	
	protected void attackOtherTeam(
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
					gui.addSpriteAbstract(new SpriteExplosion(enemy));
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
					gui.addSpriteAbstract(new SpriteExplosion(enemy));
				}
			}
		}
		if (hit) {
			unit.updateCooldown();
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
				gui.addSpriteAbstract(new SpriteExplosion(castle));
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
	
}
