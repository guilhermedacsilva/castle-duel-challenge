package br.game.castleduel.gui;

public class SpriteConsts {
	static final int X1 = 0;
	static final int Y1 = 1;
	static final int X2 = 2;
	static final int Y2 = 3;
	static final int WIDTH = 4;
	static final int HEIGHT = 5;
	static final int POS_Y = 6;
	
	static final int[][] TYPE_DATA = new int[][]{
		// image x1, y1, x2, y2, width, height, groundY
		// 0
		{0, 0, 19, 32, (int)(19*1.5), (int)(32*1.5), 144},
		// 1
		{0, 0, 49, 45, (int)(49), (int)(45), 144},
		// 2
		{0, 0, 56, 80, (int)(56*1.3), (int)(80*1.3), 94},
		// 3
		{0, 0, 40, 54, (int)(40*1.1), (int)(54*1.1), 133},
	};
	
}
