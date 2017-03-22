package br.game.castleduel.js;

import br.game.castleduel.unit.Unit;
import br.game.castleduel.unit.UnitManager;

public class PlayerEngine {
	private boolean test = true;
	
	public Unit runPlayer(int player) {
		if (test && player == 1) {
			test = false;
			return UnitManager.createUnit(0);
		}
		return null;
	}

}
