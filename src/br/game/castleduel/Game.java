package br.game.castleduel;

import java.awt.Toolkit;

import br.game.castleduel.gui.Gui;
import br.game.castleduel.player.PlayerEngine;
import br.game.castleduel.unit.Unit;

public class Game {
	public static final int FPS = 120;
	public static final int FRAME_TIME = 1000 / FPS;
	private static int CURRENT_FRAME = 0;
	private static final int FRAME_PLAYER = 13;

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

		while (!battleground.isFinished()) {
			timeAfterFrame = now() + FRAME_TIME;

			runBattle();
			gui.repaint();
			Toolkit.getDefaultToolkit().sync();

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
			Unit unit;
			unit = jsEngine.runPlayer(1);
			battleground.addUnitFromPlayer(unit, 1);
			unit = jsEngine.runPlayer(2);
			battleground.addUnitFromPlayer(unit, 2);
		}
		battleground.executeBattle();
	}

	private void finish() {
		if (battleground.isCastle1Dead()) {
			System.out.println("Player 2 WON!\n");
		} else {
			System.out.println("Player 1 WON!\n");
		}
	}

	public static int getCURRENT_FRAME() {
		return CURRENT_FRAME;
	}

	public static void main(String[] args) {
		new Game().start();
	}

}
