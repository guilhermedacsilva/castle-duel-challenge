package br.game.castleduel;

import br.game.castleduel.gui.GuiInterface;
import br.game.castleduel.gui.NormalGui;
import br.game.castleduel.gui.ServerGui;
import br.game.castleduel.player.PlayerEngine;

public class Game {
	private static final int FRAME_LIMIT = 60 * 60 * 5;
	private static int FPS = 60;
	private static int FRAME_TIME = 1000 / FPS;
	private static int CURRENT_FRAME = 0;
	private static final int FRAME_PLAYER = 13;
	private static final int FRAME_GOLD = FRAME_PLAYER * 6;
	private boolean server = false;

	private Battleground battleground;
	private PlayerEngine playerEngine;
	private GuiInterface gui;
	private int playerWon = -1;
	
	public Game(String[] args) {
		for (String config : args) {
			if (config.startsWith("fps")) {
				FPS = Integer.parseInt(config.substring(3));
				FRAME_TIME = 1000 / FPS;
			
			} else if (config.startsWith("server")) {
				server = true;
				
			}
		}
	}

	private void start() {
		loadAll();
		if (playerWon == -1) {
			if (server) {
				runGameLoopServer();
			} else {
				runGameLoopNormal();
			}
		}
		finish();
	}

	private void loadAll() {
		try {
			playerEngine = new PlayerEngine();
		} catch (RuntimeException e) {
			playerWon = Integer.valueOf(e.getMessage());
		}
		
		gui = server ? new ServerGui() : new NormalGui();
		
		battleground = new Battleground(gui);
	}

	private void runGameLoopNormal() {
		long timeAfterFrame;
		long sleepTime;

		while (!battleground.isFinished()
				&& CURRENT_FRAME < FRAME_LIMIT) {
			
			timeAfterFrame = now() + FRAME_TIME;

			runBattle();
			gui.updateGame();

			sleepTime = timeAfterFrame - now();
			if (sleepTime > 0) {
				try {
					Thread.sleep(sleepTime);
				} catch (InterruptedException e) {
				}
			}

			CURRENT_FRAME++;
		}
	}
	
	private void runGameLoopServer() {
		while (!battleground.isFinished()
				&& CURRENT_FRAME < FRAME_LIMIT) {

			runBattle();

			CURRENT_FRAME++;
		}
	}

	private static long now() {
		return System.currentTimeMillis();
	}

	private void runBattle() {
		if (CURRENT_FRAME % FRAME_PLAYER == 0) {
			int unitIndex;
			unitIndex = playerEngine.runPlayer(
					1,
					battleground.getGold(1),
					battleground.getUnits(1),
					battleground.getUnits(2),
					battleground.getCastleHealth(1),
					battleground.getCastleHealth(2)
					);
			battleground.addUnitFromPlayer(unitIndex, 1);
			unitIndex = playerEngine.runPlayer(
					2,
					battleground.getGold(2),
					battleground.getUnits(2),
					battleground.getUnits(1),
					battleground.getCastleHealth(2),
					battleground.getCastleHealth(1)					
					);
			battleground.addUnitFromPlayer(unitIndex, 2);
		}
		if (CURRENT_FRAME % FRAME_GOLD == 0) {
			battleground.gainGold();
		}
		battleground.executeBattle();
	}

	private void finish() {
		if (playerWon == -1) {
			final int castle1Health = battleground.getCastleHealth(1);
			final int castle2Health = battleground.getCastleHealth(2);
			
			if (castle1Health > castle2Health) {
				playerWon = 1;
			} else if (castle1Health < castle2Health) {
				playerWon = 2;
			} else {
				playerWon = 3;
			}
		}
		
		gui.setPlayerWon(playerWon);
		gui.updateGame();
	}

	public static int getCURRENT_FRAME() {
		return CURRENT_FRAME;
	}

	public static void main(String[] args) {
		new Game(args).start();
	}

}
