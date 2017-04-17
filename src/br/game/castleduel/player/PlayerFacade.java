package br.game.castleduel.player;

import java.lang.reflect.Method;

import br.game.castleduel.exception.PlayerException;

public class PlayerFacade {
	protected static final String PLAY_ERROR_MSG = 
			"Error invoking play method from player %d";
	protected Object[] playerObjects = new Object[2];
	protected Method[] playerMethods = new Method[2];
	
	public PlayerFacade() throws PlayerException {
		PlayerCompiler.compileFiles();
		for (int index = 0; index < 2; index++) {
			playerObjects[index] = PlayerLoader.load(index+1);
			playerMethods[index] = getPlayMethod(index+1);	
		}
	}

	public int callPlay(PlayerInfo info) {
		try {
			return (int) playerMethods[info.playerIndex].invoke(
				playerObjects[info.playerIndex], 
				info.gold,
				info.units, 
				info.enemies, 
				info.castle,
				info.castleEnemy
			);
		} catch (Exception e) {
			String msg = String.format(PLAY_ERROR_MSG, info.playerIndex+1);
			System.out.println(msg);
			e.printStackTrace();
		}
		return -1;
	}
	
	protected Method getPlayMethod(int playerNumber) 
			throws PlayerException {
		
		try {
			return playerObjects[playerNumber-1].getClass().getMethod(
				"play", 
				int.class, 
				int[].class, 
				int[].class, 
				int.class, 
				int.class
			);
		} catch (Exception e) {
			throw new PlayerException(playerNumber);
		}
	}
}
