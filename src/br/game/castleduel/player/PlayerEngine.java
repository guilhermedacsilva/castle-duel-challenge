package br.game.castleduel.player;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;

import br.game.castleduel.unit.Unit;
import br.game.castleduel.unit.UnitManager;

public class PlayerEngine {
	private Object player1;
	private Object player2;
	
	
	private int[] u1 = new int[] {0};
	private int[] u2 = new int[] {1};
	private int i1 = 0;
	private int i2 = 0;
	private Unit unit;
	
	public PlayerEngine() {
		JavaCompiler compiler = ToolProvider.getSystemJavaCompiler();
		compiler.run(null, null, null, "players/RA123456.java");
		System.exit(0);
	}
	
	public Unit runPlayer(int player) {
		unit = null;
		if (player == 1 && i1 < u1.length) {
			unit = UnitManager.createUnit(u1[i1]);
			i1++;
		}
		if (player == 2 && i2 < u2.length) {
			unit = UnitManager.createUnit(u2[i2]);
			i2++;
		}
		return unit;
	}

}
