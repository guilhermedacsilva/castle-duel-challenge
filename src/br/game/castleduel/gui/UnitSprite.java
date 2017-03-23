package br.game.castleduel.gui;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.awt.image.ColorModel;
import java.awt.image.WritableRaster;

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
		image = loadImage(player, unit);
	}
	
	public boolean isUnitDead() {
		return unit.isDead();
	}
	
	public void paint(Graphics g) {
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
	
	protected static BufferedImage loadImage(int player, Unit unit) {
		BufferedImage image = ImageLoader.load("unit" + unit.getType() + ".png");
		image = copyImage(image);
		if (player == 2) {
			flip(image);
		}
		return image;
	}
	
	protected static void flip(BufferedImage image)
	{
	    for (int i = 0; i < image.getWidth()/2; i++)
	        for (int j = 0; j < image.getHeight(); j++)
	        {
	            int tmp = image.getRGB(i, j);
	            image.setRGB(i, j, image.getRGB(image.getWidth()-1-i, j));
	            image.setRGB(image.getWidth()-1-i, j, tmp);
	        }
	}
	
	protected static BufferedImage copyImage(BufferedImage image) {
		 ColorModel cm = image.getColorModel();
		 boolean isAlphaPremultiplied = cm.isAlphaPremultiplied();
		 WritableRaster raster = image.copyData(null);
		 return new BufferedImage(cm, raster, isAlphaPremultiplied, null);
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
