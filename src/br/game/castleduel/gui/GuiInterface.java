package br.game.castleduel.gui;

import br.game.castleduel.gui.sprite.Sprite;
import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public interface GuiInterface {

	public void init(
			int width,
			Castle castleP1, 
			Castle castleP2
			);


	public void setPlayerWon(int player);

	public void updateGame(int framesLeft);
	
	public void addSprite(int player, Unit unit);
	
	public void addSprite(Sprite sprite);

	public void setGold(int gold, int gold2);
}
