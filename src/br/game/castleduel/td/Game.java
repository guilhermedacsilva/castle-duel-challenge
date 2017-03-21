package br.game.castleduel.td;

import javax.swing.plaf.basic.BasicTextAreaUI;

import br.game.castleduel.td.gui.Gui;
import br.game.castleduel.td.js.JsEngine;
import br.game.castleduel.td.unit.Unit;

public class Game {
	private Battleground battleground;
	private JsEngine jsEngine;
	private Gui gui;
	
	private void start() {
		loadAll();
		runLoop();
		finish();
	}

	private void loadAll() {
		gui = new Gui();
		battleground = new Battleground(gui);
		jsEngine = new JsEngine();
	}

	private void runLoop() {
		while (!battleground.isFinished()) {
			runBattle();
		}
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
