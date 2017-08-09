package br.game.castleduel.unit;

import static br.game.castleduel.unit.UnitConsts.*;

public class UnitManager {
	public static Unit createUnit(int unitIndex, int playerIndex) {
		Unit unit = new Unit(UNIT_ARRAY[unitIndex]);
		unit.setPlayerIndex(playerIndex);
		return unit;
	}
	
	public static boolean canBuyUnit(int index, int gold) {
		return isValidIndex(index) && gold >= getCost(index);
	}
	
	public static int getCost(int index) {
		return UNIT_ARRAY[index].gold;
	}
	
	protected static boolean isValidIndex(int index) {
		return index >= 0 && index < UNIT_ARRAY.length;
	}
}
