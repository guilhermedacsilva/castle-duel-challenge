package br.game.castleduel;

import br.game.castleduel.exception.PlayerException;
import br.game.castleduel.player.PlayerFilesPreparator;

public class Main {
	protected static final String CONFIG_FPS = "fps";
	protected static final String CONFIG_SERVER = "server";
	protected int fps;
	protected boolean isServer = false;
	
	public static void main(String[] args) {
		new Main(args).execute();
	}
	
	public Main(String[] args) {
		parseConfigs(args);
	}
	
	protected void parseConfigs(String[] configs) {
		for (String config : configs) {
			tryConfigFps(config);
			tryConfigServer(config);
		}
	}
	
	protected void tryConfigFps(String config) {
		if (config.startsWith(CONFIG_FPS)) {
			fps = Integer.parseInt(config.substring(3));
		}
	}
	
	protected void tryConfigServer(String config) {
		if (config.startsWith(CONFIG_SERVER)) {
			isServer = true;
		}
	}

	protected void execute() {
		if (isServer) {
			Score score = new Score();
			
			String playerWonTmpName, playerWonRealName;
			
			while (PlayerFilesPreparator.prepare()) {
				score.init(PlayerFilesPreparator.getRealNames());
				Game game = new Game();
				try {
					playerWonTmpName = game.play(isServer, fps);
					playerWonRealName = PlayerFilesPreparator.getPlayerRealName(playerWonTmpName);
				} catch (PlayerException e) {
					playerWonRealName = PlayerFilesPreparator.getOtherPlayerRealName(e.filename);
				}
				score.increment(playerWonRealName);
			}
			score.print();
			score.saveToFile();
			
		} else {
			PlayerFilesPreparator.prepare();
			Game game = new Game();
			try {
				game.play(isServer, fps);
			} catch (PlayerException e) {
				e.printStackTrace();
			}
		}
	}
}
