package br.game.castleduel.exception;

public class PlayerException extends Exception {
	public final int player;
	
	public PlayerException(int player) {
		this.player = player;
	}
}
