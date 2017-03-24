package br.game.castleduel;

import br.game.castleduel.gui.Gui;
import br.game.castleduel.player.PlayerEngine;

public class Game {
	private static final int FRAME_LIMIT = 60 * 60 * 5;
	public static final int FPS = 60;
	public static final int FRAME_TIME = 1000 / FPS;
	private static int CURRENT_FRAME = 0;
	private static final int FRAME_PLAYER = 13;
	private static final int FRAME_GOLD = FRAME_PLAYER * 6;

	private Battleground battleground;
	private PlayerEngine jsEngine;
	private Gui gui;

	private void start() {
		loadAll();
		runTimeLoop();
		finish();
	}

	private void loadAll() {
		jsEngine = new PlayerEngine();
		gui = new Gui();
		battleground = new Battleground(gui);
	}

	private void runTimeLoop() {
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

	private static long now() {
		return System.currentTimeMillis();
	}

	private void runBattle() {
		if (CURRENT_FRAME % FRAME_PLAYER == 0) {
			int unitIndex;
			unitIndex = jsEngine.runPlayer(
					1,
					battleground.getGold(1),
					battleground.getUnits(1),
					battleground.getUnits(2)					
					);
			battleground.addUnitFromPlayer(unitIndex, 1);
			unitIndex = jsEngine.runPlayer(
					2,
					battleground.getGold(2),
					battleground.getUnits(2),
					battleground.getUnits(1)					
					);
			battleground.addUnitFromPlayer(unitIndex, 2);
		}
		if (CURRENT_FRAME % FRAME_GOLD == 0) {
			battleground.gainGold();
		}
		battleground.executeBattle();
	}

	private void finish() {
		final int castle1Health = battleground.getCastle1Health();
		final int castle2Health = battleground.getCastle2Health(); 
		if (castle1Health > castle2Health) {
			gui.setPlayerWon(1);
		} else if (castle1Health < castle2Health) {
			gui.setPlayerWon(2);
		} else {
			gui.setPlayerWon(3);
		}
		gui.updateGame();
	}

	public static int getCURRENT_FRAME() {
		return CURRENT_FRAME;
	}

	public static void main(String[] args) {
		new Game().start();
	}

}
