package br.game.castleduel.util;

import javax.swing.JFrame;
import javax.swing.JPanel;

public class SimpleJFrame extends JFrame {
	private static final long serialVersionUID = 1L;

	public SimpleJFrame(String title, JPanel panel) {
		super(title);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setResizable(false);
		add(panel);
		pack();
		setVisible(true);
		setLocationRelativeTo(null);
	}
	
	public static void create(String title, JPanel panel) {
		new SimpleJFrame(title, panel);
	}
	
}
