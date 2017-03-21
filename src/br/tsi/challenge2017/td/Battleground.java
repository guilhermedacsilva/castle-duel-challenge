package br.tsi.challenge2017.td;

import java.util.ArrayList;
import java.util.List;

import br.tsi.challenge2017.td.gui.Gui;
import br.tsi.challenge2017.td.unit.Castle;
import br.tsi.challenge2017.td.unit.Unit;

public class Battleground {
	private static final int WIDTH = 800;
	private static final int CASTLE_POSITION = 750;

	private Gui gui;
	private List<Unit> unitsP1 = new ArrayList<Unit>(50);
	private List<Unit> unitsP2 = new ArrayList<Unit>(50);
	private Castle castleP1 = new Castle();
	private Castle castleP2 = new Castle();
	
	public Battleground(Gui gui) {
		this.gui = gui;
	}
	
	public void addUnitFromPlayer(Unit unit, int player) {
		if (unit != null) {
			getUnitsFromPlayer(player).add(unit);
		}
	}
	
	private List<Unit> getUnitsFromPlayer(int player) {
		return player == 1 ? unitsP1 : unitsP2;
	}
	
	public boolean isFinished() {
		return castleP1.isDead() || castleP2.isDead();
	}

	public void executeBattle() {
		playUnits(unitsP1, unitsP2, castleP2);
		playUnits(unitsP2, unitsP1, castleP1);
		removeDead();
	}
	
	private void playUnits(
			List<Unit> units, 
			List<Unit> enemies, 
			Castle enemyCastle
	) {
		for (Unit unit : units) {
			if (tryAttackEnemy(unit, enemies)
				|| tryAttackCastle(unit, enemyCastle)) {
				continue;
			}
			unit.walk();
		}
	}

	private boolean tryAttackEnemy(Unit unit, List<Unit> enemies) {
		for (Unit enemy : enemies) {
			if (isInAttackRange(unit, enemy)) {
				unit.attack(enemy);
				return true;
			}
		}
		return false;
	}
	
	private boolean isInAttackRange(Unit unit, Unit enemy) {
		int distance = WIDTH 
				- unit.getPosition() 
				- enemy.getPosition();
		
		return distance <= unit.getRange();
	}
	
	private boolean tryAttackCastle(Unit unit, Castle castle) {
		int distance = CASTLE_POSITION - unit.getPosition();
		if (distance <= unit.getRange()) {
			castle.looseHealth(unit.getAttack());
			return true;
		}
		return false;
	}
	
	private void removeDead() {
		// TODO
	}
	
}
