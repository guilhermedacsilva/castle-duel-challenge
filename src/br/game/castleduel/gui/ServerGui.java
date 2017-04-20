package br.game.castleduel.gui;

import br.game.castleduel.gui.sprite.Sprite;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public class ServerGui implements GuiInterface {

	@Override
	public void init(int width, Castle castleP1, Castle castleP2) {
	}

	@Override
	public void setPlayerWon(int player) {
		System.out.println("Player " + player + " won.");
	}

	@Override
	public void updateGame(int framesLeft) {
	}

	@Override
	public void addSprite(int player, Unit unit) {
	}

	@Override
	public void addSprite(Sprite sprite) {
	}
	
	@Override
	public void setGold(int gold, int gold2) {
	}
}
