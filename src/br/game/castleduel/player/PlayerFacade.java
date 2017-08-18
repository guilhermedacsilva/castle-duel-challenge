package br.game.castleduel.player;

import java.lang.reflect.Method;

import br.game.castleduel.exception.PlayerException;

public class PlayerFacade {
	private static final String PLAY_ERROR_MSG = 
			"Error invoking play method from player %d";
	private Object[] playerObjects = new Object[2];
	private Method[] playMethods = new Method[2];
	private Method[] getNameMethods = new Method[2];
	private String[] filenames;
	
	public PlayerFacade() throws PlayerException {
		filenames = PlayerCompiler.compileFiles();
		for (int index = 0; index < 2; index++) {
			playerObjects[index] = PlayerLoader.load(index, filenames[index]);
			playMethods[index] = getPlayMethod(index+1);	
			getNameMethods[index] = getNameMethod(index+1);	
		}
	}
	
	public String getFilename(int player) {
		return filenames[player-1];
	}

	public int callPlay(PlayerInfo info) {
		try {
			return (int) playMethods[info.playerIndex].invoke(
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
	
	public String callGetName(int playerIndex) throws PlayerException {
		try {
			return (String) getNameMethods[playerIndex].invoke(playerObjects[playerIndex]);
		} catch (Exception e) {
			throw new PlayerException(playerIndex+1);
		}
	}
	
	protected Method getPlayMethod(int playerNumber) throws PlayerException {
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
	
	protected Method getNameMethod(int playerNumber) throws PlayerException {
		try {
			return playerObjects[playerNumber-1].getClass().getMethod("getName");
		} catch (Exception e) {
			throw new PlayerException(playerNumber);
		}
	}
}
