package br.game.castleduel.gui.sprite;

import java.awt.Graphics;

import br.game.castleduel.util.UniqueId;

/**
 * Constants about the images.
 */
abstract public class Sprite extends UniqueId {
	protected static final int X1 = 0;
	protected static final int Y1 = 1;
	protected static final int X2 = 2;
	protected static final int Y2 = 3;
	protected static final int WIDTH = 4;
	protected static final int HEIGHT = 5;
	protected static final int POS_Y = 6;
	
	protected static final int[][] TYPE_DATA = new int[][]{
		// image x1, y1, x2, y2, width, height, posY
		// 0
//		{0, 0, 19, 32, (int)(19*1.5), (int)(32*1.5), 153},
		{0, 0, 52, 60, (int)(52*0.9), (int)(60*0.9), 179},
		// 1
//		{0, 0, 49, 45, (int)(49), (int)(45), 153},
		{0, 0, 88, 82, (int)(88*0.9), (int)(82*0.9), 159},
		// 2
//		{0, 0, 56, 80, (int)(56*1.3), (int)(80*1.3), 103},
		{0, 0, 90, 107, (int)(90*1), (int)(107*1), 125},
		// 3
//		{0, 0, 40, 54, (int)(40*1.1), (int)(54*1.1), 143},
		{0, 0, 67, 73, (int)(67*1.0), (int)(73*1.0), 163},
		// 4
//		{0, 0, 47, 59, (int)(47*1.1), (int)(59*1.1), 143},
		{0, 0, 116, 80, (int)(116*1), (int)(80*1), 153},
		// 5
//		{0, 0, 80, 138, (int)(80*0.85), (int)(138*0.85), 90},
		{0, 0, 128, 94, (int)(128*1.2), (int)(94*1.2), 122},
	};
	
	public boolean shouldDelete() {
		return false;
	}
	
	public void paint(Graphics g) {
	}
	
	public int getPositionX() {
		return 0;
	}

	public int getPositionY() {
		return 0;
	}

	public int getWidth() {
		return 0;
	}
	
	public int getHeight() {
		return 0;
	}
	
	public int getCenterX() {
		return 0;
	}
	
	public int getCenterY() {
		return 0;
	}
}
