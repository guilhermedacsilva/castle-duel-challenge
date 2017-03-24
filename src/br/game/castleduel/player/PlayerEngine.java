package br.game.castleduel.player;

import java.io.File;
import java.net.URL;
import java.net.URLClassLoader;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import br.game.castleduel.util.ClassFileFilter;

public class PlayerEngine {
	private Object player1;
	private Object player2;
	
	public PlayerEngine() {
//		deleteAllClassFiles();
		compileJavaFiles();
		loadClassFiles();
	}

	public int runPlayer(int player, int gold, int[] units, int[] enemies) {
		if (player == 1) {
			return executeMethodPlayer(player1, gold, units, enemies);
		}
		return executeMethodPlayer(player2, gold, units, enemies);
	}
	
	private int executeMethodPlayer(
			Object player, 
			int gold, 
			int[] units, 
			int[] enemies) {
		
		try {
			return (int) player
							.getClass()
							.getMethod("play", int.class, int[].class, int[].class)
							.invoke(player, gold, units, enemies);
		} catch (Exception e) {
			System.out.println("Error invoking method play from " + player.getClass().getSimpleName());
			e.printStackTrace();
		}
		return -1;
	}

	private void deleteAllClassFiles() {
		File playersFolder = new File("players");
		if (!playersFolder.exists()) {
			playersFolder.mkdirs();
		}
		for (String fileName : playersFolder.list(new ClassFileFilter())) {
			new File(playersFolder, fileName).delete();
		}
		try { Thread.sleep(500);}
		catch (InterruptedException e) {}
	}

	private void compileJavaFiles() {
		JavaCompiler compiler = getCompiler();
		compileFile(compiler, "players/Player1.java", 2);
		compileFile(compiler, "players/Player2.java", 1);
	}
	
	protected static JavaCompiler getCompiler() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		if (compiler == null) {
			System.out.println("Could not find the compiler!\nPlease, configure your machine.");
			System.exit(1);
		}
		return compiler;
	}
	
	protected void compileFile(JavaCompiler compiler, String path, int playerWon) {
		int result = compiler.run(null, null, null, path);
		if (result != 0) {
			System.out.println("Could not compile the file: "+path+".");
			throw new RuntimeException(String.valueOf(playerWon));
		}
	}
	
	protected void loadClassFiles() {
		try {
			File folder = new File("players");
			URLClassLoader classLoader = URLClassLoader.newInstance(new URL[] {folder.toURI().toURL()});
			Class<?> clazz = Class.forName("Player1", true, classLoader);
			player1 = clazz.newInstance();
			clazz = Class.forName("Player2", true, classLoader);
			player2 = clazz.newInstance();
		} catch (Exception e) {
			System.out.println("Could not load the .class file.");
			e.printStackTrace();
			System.exit(1);
		}
	}
}
