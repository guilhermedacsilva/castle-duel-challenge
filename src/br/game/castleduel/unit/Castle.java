package br.game.castleduel.unit;

public class Castle extends LivingBeing {
	private int player;
	
	public Castle(int player) {
		health = 120;
		healthMax = health;
		this.player = player;
	}
	
	public int getPlayer() {
		return player;
	}
}
