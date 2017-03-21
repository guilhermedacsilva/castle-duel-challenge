package br.tsi.challenge2017.td;

import br.tsi.challenge2017.td.gui.Gui;
import br.tsi.challenge2017.td.js.JsEngine;
import br.tsi.challenge2017.td.unit.Unit;

public class Game {
	Battleground battleground;
	JsEngine jsEngine;
	Gui gui;
	
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
			runScriptsFromPlayers();
			battleground.executeBattle();
		}
	}
	
	private void runScriptsFromPlayers() {
		Unit unit;
		unit = jsEngine.runPlayer(1);
		battleground.addUnitFromPlayer(unit, 1);
		unit = jsEngine.runPlayer(2);
		battleground.addUnitFromPlayer(unit, 2);
	}
	
	private void finish() {
		
	}

	public static void main(String[] args) {
		new Game().start();
	}
	
}
