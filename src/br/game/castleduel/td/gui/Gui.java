package br.game.castleduel.td.gui;

import java.awt.Graphics;

import javax.swing.JFrame;

public class Gui extends JFrame {
	private static final long serialVersionUID = 1L;
	
	public Gui() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setResizable(false);
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
		g.drawRect(0, 0, 100, 100);
	}
}
