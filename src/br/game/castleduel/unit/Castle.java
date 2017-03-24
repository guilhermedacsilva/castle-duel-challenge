package br.game.castleduel.unit;

public class Castle extends LivingBeing {
	private int player;
	
	public Castle(int player) {
		this.player = player;
		health = 120;
	}
	
	public int getPlayer() {
		return player;
	}
}
