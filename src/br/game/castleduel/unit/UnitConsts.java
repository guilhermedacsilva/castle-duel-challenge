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
	public static final int ARCHER_GOLD = 0;
	public static final int ARCHER_HEALTH = 0;
	public static final int ARCHER_ATTACK = 0;
	public static final int ARCHER_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int ARCHER_RANGE = 220;

	public static final int OGRE_TYPE = 2;
	public static final int OGRE_GOLD= 0;
	public static final int OGRE_HEALTH = 0;
	public static final int OGRE_ATTACK = 0;
	public static final int OGRE_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int OGRE_RANGE = RANGE_CLOSE;

	public static final int ZOMBIE_TYPE = 3;
	public static final int ZOMBIE_GOLD= 0;
	public static final int ZOMBIE_HEALTH = 0;
	public static final int ZOMBIE_ATTACK = 0;
	public static final int ZOMBIE_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int ZOMBIE_RANGE = RANGE_CLOSE;

	public static final int MAGE_TYPE = 4;
	public static final int MAGE_GOLD= 0;
	public static final int MAGE_HEALTH = 0;
	public static final int MAGE_ATTACK = 0;
	public static final int MAGE_ATTACK_TYPE = ATTACK_TYPE_HIT_ALL;
	public static final int MAGE_RANGE = 350;

	public static final int CYCLOPS_TYPE = 5;
	public static final int CYCLOPS_GOLD= 0;
	public static final int CYCLOPS_HEALTH = 0;
	public static final int CYCLOPS_ATTACK = 0;
	public static final int CYCLOPS_ATTACK_TYPE = ATTACK_TYPE_NORMAL;
	public static final int CYCLOPS_RANGE = RANGE_CLOSE;
	
	private UnitConsts() {}
}
