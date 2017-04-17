package br.game.castleduel;

import br.game.castleduel.exception.PlayerException;
import br.game.castleduel.gui.GuiInterface;
import br.game.castleduel.gui.NormalGui;
import br.game.castleduel.gui.ServerGui;
import br.game.castleduel.player.PlayerFacade;
import br.game.castleduel.player.PlayerInfo;
import br.game.castleduel.time.FixedTimeRunnable;
import br.game.castleduel.time.GameTime;

public class Game implements FixedTimeRunnable {
	protected static final String CONFIG_FPS = "fps";
	protected static final String CONFIG_SERVER = "server";
	protected GameTime time;
	protected Battleground battleground;
	protected PlayerFacade playerEngine;
	protected GuiInterface gui;
	protected int playerWon = -1;
	protected boolean server = false;
	
	public static void main(String[] args) {
		new Game(args).execute();
	}
	
	public Game(String[] args) {
		time = new GameTime();
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
			int fps = Integer.parseInt(config.substring(3));
			time.setFps(fps);
		}
	}
	
	protected void tryConfigServer(String config) {
		if (config.startsWith(CONFIG_SERVER)) {
			server = true;
		}
	}

	protected void execute() {
		loadGame();
		if (playerWon == -1) {
			if (server) {
				runGameLoopServer();
			} else {
				runGameLoopNormal();
			}
		}
		finish();
	}

	private void loadGame() {
		try {
			playerEngine = new PlayerFacade();
		} catch (PlayerException e) {
			playerWon = e.player != 1 ? 1 : 2;
		}
		gui = server ? new ServerGui() : new NormalGui();
		battleground = new Battleground(gui);
	}
	
	@Override
	public void runWithFixedTime() {
		runBattle();
		gui.setFramesLeft(time.getFramesLeft());
		gui.updateGame();
	}

	private void runGameLoopNormal() {
		while (!battleground.isFinished() && time.canContinue()) {
			time.runWithSleep(this);
		}
	}
	
	private void runGameLoopServer() {
		while (!battleground.isFinished() && time.canContinue()) {
			runBattle();
			time.nextFrame();
		}
	}

	private void runBattle() {
		if (time.canPlayersPlay()) {
			runPlayers();
		}
		if (time.canReceiveGold()) {
			battleground.gainGold();
		}
		battleground.executeBattle();
	}
	
	private void runPlayers() {
		for (int i = 0; i < 2; i++) {
			final PlayerInfo info = battleground.getPlayerInfo(i);
			final int unitIndex = playerEngine.callPlay(info);
			battleground.addUnitFromPlayer(unitIndex, i);	
		}
	}

	private void finish() {
		if (playerWon == -1) {
			PlayerInfo player0 = battleground.getPlayerInfo(0);			
			if (player0.castle > player0.castleEnemy) {
				playerWon = 1;
			} else if (player0.castle < player0.castleEnemy) {
				playerWon = 2;
			} else {
				playerWon = 3;
			}
		}
		gui.setPlayerWon(playerWon);
		gui.updateGame();
	}
}
