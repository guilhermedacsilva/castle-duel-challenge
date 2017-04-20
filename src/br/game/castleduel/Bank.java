package br.game.castleduel;

import br.game.castleduel.unit.Unit;
import br.game.castleduel.unit.UnitManager;

public class Bank {
	private int[] wallets = new int[2];

	public void increaseGold() {
		for (int index = 0; index < wallets.length; index++) {
			wallets[index]++;
		}
	}
	
	public int get(int playerIndex) {
		return wallets[playerIndex];
	}

	public Unit buyUnit(int playerIndex, int unitIndex) {
		if (UnitManager.canBuyUnit(unitIndex, wallets[playerIndex])) {
			wallets[playerIndex] -= UnitManager.getCost(unitIndex);
			return UnitManager.createUnit(unitIndex, playerIndex);
		}
		return null;
	}
	
}
