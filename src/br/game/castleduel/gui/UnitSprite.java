package br.game.castleduel.gui;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

import br.game.castleduel.Battleground;
import br.game.castleduel.unit.Unit;

public class UnitSprite extends SpriteConsts {
	private static int ID_GENERATOR = 0;
	private final int id;
	private final int player;
	private final Unit unit;
	private int width;
	private BufferedImage image;
	
	public UnitSprite(int player, Unit unit) {
		this.player = player;
		this.unit = unit;
		width = 50;
		id = ID_GENERATOR++;
		image = ImageLoader.load("unit" + unit.getType() + ".png");
	}
	
	public boolean isUnitDead() {
		return unit.isDead();
	}
	
	public void paint(Graphics g) {
		g.setColor(Color.black);
//		g.fillRect(getPositionX(), 100, width, 100);
		g.drawImage(image, 
				getPositionX(), 
				144,
				getPositionX() + 45,
				144 + 45,
				TYPE_DATA[unit.getType()][X1],
				TYPE_DATA[unit.getType()][Y1],
				TYPE_DATA[unit.getType()][X2],
				TYPE_DATA[unit.getType()][Y2],
				null);
	}
	
	private int getPositionX() {
		if (player == 1) {
			return unit.getPosition();
		}
		return Battleground.BATTLEGROUND_WIDTH - unit.getPosition() - width;
	}

	@Override
	public int hashCode() {
		return id;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		UnitSprite other = (UnitSprite) obj;
		if (id != other.id)
			return false;
		return true;
	}
	
	
}
