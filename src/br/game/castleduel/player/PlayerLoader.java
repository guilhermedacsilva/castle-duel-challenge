package br.game.castleduel.player;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import br.game.castleduel.exception.PlayerException;

public class PlayerLoader {
	protected static final File FOLDER = new File("players");
	protected static final String CLASS_FORMAT = "Player%d";
	
	public static Object load(int player) throws PlayerException {
		try {
			URL[] urls = new URL[] {FOLDER.toURI().toURL()};
			URLClassLoader classLoader = URLClassLoader.newInstance(urls);
			String className = String.format(CLASS_FORMAT, player);
			Class<?> clazz = Class.forName(className, true, classLoader);
			return clazz.newInstance();
		} catch (Exception e) {
			throw new PlayerException(player);
		}
	}
}
