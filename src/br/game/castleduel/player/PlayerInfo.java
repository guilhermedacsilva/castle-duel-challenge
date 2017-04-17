package br.game.castleduel.player;

public class PlayerInfo {
	public int playerIndex; 
	public int gold; 
	public int[] units; 
	public int[] enemies;
	public int castle;
	public int castleEnemy;
	
	public PlayerInfo(
			int playerIndex, 
			int gold, 
			int[] units, 
			int[] enemies, 
			int castle, 
			int castleEnemy)
	{
		this.playerIndex = playerIndex;
		this.gold = gold;
		this.units = units;
		this.enemies = enemies;
		this.castle = castle;
		this.castleEnemy = castleEnemy;
	}	
}
