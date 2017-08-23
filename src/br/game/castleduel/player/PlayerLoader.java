package br.game.castleduel.player;

import java.net.URL;
import java.net.URLClassLoader;

import br.game.castleduel.exception.PlayerException;

public class PlayerLoader {	
	public static Object load(int playerIndex, String filename) throws PlayerException {
		try {
			URL[] urls = new URL[] {PlayerCompiler.TEMP_PLAYERS.toURI().toURL()};
			URLClassLoader classLoader = URLClassLoader.newInstance(urls);
			String className = filename.substring(0, filename.length()-5);
			Class<?> clazz = Class.forName(className, true, classLoader);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new PlayerException(playerIndex+1);
		}
	}
}
