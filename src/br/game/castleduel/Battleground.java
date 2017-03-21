package br.game.castleduel;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import br.game.castleduel.gui.Gui;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public class Battleground {
	protected static final int BATTLEGROUND_WIDTH = 800;
	protected static final int CASTLE_POSITION = 750;

	protected Gui gui;
	protected List<Unit> unitsP1 = new ArrayList<Unit>(50);
	protected List<Unit> unitsP2 = new ArrayList<Unit>(50);
	protected Castle castleP1;
	protected Castle castleP2;
	
	public Battleground(Gui gui) {
		initCastles();
		gui.init(BATTLEGROUND_WIDTH);
		this.gui = gui;
	}
	
	protected void initCastles() {
		castleP1 = new Castle();
		castleP2 = new Castle();
	}
	
	public boolean isFinished() {
		return castleP1.isDead() || castleP2.isDead();
	}
	
	public boolean isCastle1Dead() {
		return castleP1.isDead();
	}
	
	public void addUnitFromPlayer(Unit unit, int player) {
		if (unit != null) {
			getUnitsFromPlayer(player).add(unit);
		}
	}
	
	protected List<Unit> getUnitsFromPlayer(int player) {
		return player == 1 ? unitsP1 : unitsP2;
	}

	public void executeBattle() {
		attackOtherTeam(unitsP1, unitsP2, castleP2);
		attackOtherTeam(unitsP2, unitsP1, castleP1);
		removeDeads();
	}
	
	protected static void attackOtherTeam(
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

	protected static boolean tryAttackEnemy(
			Unit unit, 
			List<Unit> enemies
			) {
		for (Unit enemy : enemies) {
			if (isInAttackRange(unit, enemy)) {
				unit.attack(enemy);
				return true;
			}
		}
		return false;
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
	
	protected static boolean tryAttackCastle(
			Unit unit, 
			Castle castle
			) {
		int distance = CASTLE_POSITION - unit.getPosition();
		if (distance <= unit.getRange()) {
			castle.looseHealth(unit.getAttack());
			return true;
		}
		return false;
	}
	
	protected void removeDeads() {
		removeDeadUnits(unitsP1);
		removeDeadUnits(unitsP2);
	}
	
	protected static void removeDeadUnits(List<Unit> units) {
		Iterator<Unit> iterator;
		Unit unit;
		
		iterator = units.iterator();
		while (iterator.hasNext()) {
			unit = iterator.next();
			if (unit.isDead()) {
				iterator.remove();
			}
		}
	}
	
}
