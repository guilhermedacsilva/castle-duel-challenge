package br.game.castleduel.player;

import br.game.castleduel.unit.Unit;
import br.game.castleduel.unit.UnitManager;

public class PlayerEngine {
	private int[] u1 = new int[] {4};
	private int[] u2 = new int[] {};
	private int i1 = 0;
	private int i2 = 0;
	
	private Unit unit;
	
	public Unit runPlayer(int player) {
		unit = null;
		if (player == 1 && i1 < u1.length) {
			unit = UnitManager.createUnit(u1[i1]);
			i1++;
		}
		if (player == 2 && i2 < u2.length) {
			unit = UnitManager.createUnit(u2[i2]);
			i2++;
		}
		return unit;
	}

}
