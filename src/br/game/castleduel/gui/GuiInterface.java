package br.game.castleduel.gui;

import br.game.castleduel.unit.Castle;
import br.game.castleduel.unit.Unit;

public interface GuiInterface {

	public void init(
			int width,
			Castle castleP1, 
			Castle castleP2
			);


	public void setPlayerWon(int player);

	public void updateGame();
	
	public void addSprite(int player, Unit unit);
	
	public void addSpriteAbstract(SpriteAbstract sprite);

	public void setGold(int gold, int gold2);
	
	public void setFramesLeft(int framesLeft);
}
