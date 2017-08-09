package br.game.castleduel.unit;

public final class UnitConsts {
	public static final int RANGE_CLOSE = 100;
	public static final int ATTACK_TYPE_NORMAL = 0;
	public static final int ATTACK_TYPE_HIT_ALL = 1;
	
	public static final int SKELETON_TYPE = 0;
	public static final int SKELETON_GOLD= 3;
	public static final int SKELETON_HEALTH = 10;
	public static final int SKELETON_ATTACK = 3;
	public static final int SKELETON_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int SKELETON_RANGE = RANGE_CLOSE;

	public static final int ARCHER_TYPE = 1;
	public static final int ARCHER_GOLD = 4;
	public static final int ARCHER_HEALTH = 7;
	public static final int ARCHER_ATTACK = 2;
	public static final int ARCHER_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int ARCHER_RANGE = 220;

	public static final int OGRE_TYPE = 2;
	public static final int OGRE_GOLD= 5;
	public static final int OGRE_HEALTH = 30;
	public static final int OGRE_ATTACK = 3;
	public static final int OGRE_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int OGRE_RANGE = RANGE_CLOSE;

	public static final int ZOMBIE_TYPE = 3;
	public static final int ZOMBIE_GOLD= 10;
	public static final int ZOMBIE_HEALTH = 21;
	public static final int ZOMBIE_ATTACK = 10;
	public static final int ZOMBIE_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int ZOMBIE_RANGE = RANGE_CLOSE;

	public static final int MAGE_TYPE = 4;
	public static final int MAGE_GOLD= 17;
	public static final int MAGE_HEALTH = 14;
	public static final int MAGE_ATTACK = 3;
	public static final int MAGE_ATTACK_TYPE = ATTACK_TYPE_HIT_ALL;
	public static final int MAGE_RANGE = 350;

	public static final int CYCLOPS_TYPE = 5;
	public static final int CYCLOPS_GOLD= 17;
	public static final int CYCLOPS_HEALTH = 100;
	public static final int CYCLOPS_ATTACK = 4;
	public static final int CYCLOPS_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int CYCLOPS_RANGE = RANGE_CLOSE;
	
	private UnitConsts() {}

	protected static Unit[] UNIT_ARRAY = new Unit[] {
		// TYPE, GOLD, HEALTH, ATK, RNG, ATK_TYPE
		// 0 melee
		// 000x11 0000x111 00x2 000x4
		new Unit(SKELETON_TYPE, 
				SKELETON_GOLD, 
				SKELETON_HEALTH, 
				SKELETON_ATTACK, 
				SKELETON_RANGE, 
				SKELETON_ATTACK_TYPE), 
		// 1 ranged
		// 11x00 111x000 111x4
		new Unit(ARCHER_TYPE, 
				ARCHER_GOLD, 
				ARCHER_HEALTH, 
				ARCHER_ATTACK, 
				ARCHER_RANGE, 
				ARCHER_ATTACK_TYPE),
		// 2 tank
		// x0 x1 x4 x11 22x3 222x44
		new Unit(OGRE_TYPE, 
				OGRE_GOLD, 
				OGRE_HEALTH, 
				OGRE_ATTACK, 
				OGRE_RANGE, 
				OGRE_ATTACK_TYPE),

		// 3 melee
		// x0 x1 x2 x00 x11 x4 33x44
		new Unit(ZOMBIE_TYPE, 
				ZOMBIE_GOLD, 
				ZOMBIE_HEALTH, 
				ZOMBIE_ATTACK, 
				ZOMBIE_RANGE, 
				ZOMBIE_ATTACK_TYPE),
		// 4 ranged
		// x000000 x111111 44x222222 44x333333 4444x55
		new Unit(MAGE_TYPE, 
				MAGE_GOLD, 
				MAGE_HEALTH, 
				MAGE_ATTACK, 
				MAGE_RANGE, 
				MAGE_ATTACK_TYPE),
		// 5 tank
		// x3 x4 x44 55x44
		new Unit(CYCLOPS_TYPE, 
				CYCLOPS_GOLD, 
				CYCLOPS_HEALTH, 
				CYCLOPS_ATTACK, 
				CYCLOPS_RANGE, 
				CYCLOPS_ATTACK_TYPE)
	};
}
