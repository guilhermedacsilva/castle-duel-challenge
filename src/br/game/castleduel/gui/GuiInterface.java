package br.game.castleduel.gui;

import br.game.castleduel.gui.sprite.Sprite;
import br.game.castleduel.unit.Castle;

public interface GuiInterface {

	public void init(
			int width,
			Castle castleP1, 
			Castle castleP2
			);


	public void setPlayerWon(int player);

	public void updateGame(int framesLeft);
	
	public void addSprite(Sprite sprite);

	public void setGold(int gold1, int gold2);
}
