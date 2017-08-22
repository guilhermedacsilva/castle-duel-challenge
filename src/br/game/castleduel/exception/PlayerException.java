package br.game.castleduel.exception;

public class PlayerException extends Exception {
	private static final long serialVersionUID = 1L;
	public final int player;
	public final String filename;
	
	public PlayerException(int player, String filename) {
		this.player = player;
		this.filename = filename;
	}
	
	public PlayerException(int player) {
		this.player = player;
		this.filename = null;
	}
}
