package br.game.castleduel.unit;

public class Castle extends Killable {
	private int playerIndex;
	
	public Castle(int playerIndex) {
		health = 120;
		healthMax = health;
		this.playerIndex = playerIndex;
	}
	
	public int getPlayerIndex() {
		return playerIndex;
	}
}
