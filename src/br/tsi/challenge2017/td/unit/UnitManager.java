package br.tsi.challenge2017.td.unit;

public class UnitManager {
	private static Unit[] UNIT_ARRAY = new Unit[] {
		// 1 melee
		new Unit(3, 10, 3),
		// 1 ranged
		new Unit(3, 8, 3, true),
		// 1 tank
		new Unit(5, 25, 3),

		// 2 melee
		new Unit(9, 32, 10),
		// 2 ranged
		new Unit(9, 26, 10, true),
		// 2 tank
		new Unit(15, 70, 10),
	};
	
	public static int getCost(int index) {
		return UNIT_ARRAY[index].gold;
	}
	
	public static Unit createUnit(int index) {
		return new Unit(UNIT_ARRAY[index]);
	}
	
	public static boolean isValidIndex(int index) {
		return index >= 0 && index < UNIT_ARRAY.length;
	}
}
