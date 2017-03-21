package br.game.castleduel;

import br.game.castleduel.gui.Gui;
import br.game.castleduel.js.JsEngine;
import br.game.castleduel.unit.Unit;

public class Game {
	private static final int FPS = 40;
	private static final int FRAME_TIME = 1000 / FPS;
	
	private Battleground battleground;
	private JsEngine jsEngine;
	private Gui gui;
	
	private void start() {
		loadAll();
		runTimeLoop();
		finish();
	}

	private void loadAll() {
		gui = new Gui();
		battleground = new Battleground(gui);
		jsEngine = new JsEngine();
	}

	private void runTimeLoop() {
		long timeAfterFrame;
		long sleepTime;
		
		while (!battleground.isFinished()) {
			timeAfterFrame = now() + FRAME_TIME;
			
			runBattle();
			gui.repaint();
			
			sleepTime = timeAfterFrame - now();
			if (sleepTime > 0) {
				try { Thread.sleep(sleepTime); }
				catch (InterruptedException e) {}
			}
		}
	}
	
	private static long now() {
		return System.currentTimeMillis();
	}
	
	private void runBattle() {
		Unit unit;
		unit = jsEngine.runPlayer(1);
		battleground.addUnitFromPlayer(unit, 1);
		unit = jsEngine.runPlayer(2);
		battleground.addUnitFromPlayer(unit, 2);
		battleground.executeBattle();
	}
	
	private void finish() {
		if (battleground.isCastle1Dead()) {
			System.out.println("Player 2 WON!\n");
		} else {
			System.out.println("Player 1 WON!\n");
		}
	}

	public static void main(String[] args) {
		new Game().start();
	}
	
}
