package dev.ulman.snake.gui;

import java.awt.GridLayout;

import javax.swing.JFrame;

import dev.ulman.snake.graphics.Screen;

public class SnakeFrame extends JFrame {

	public SnakeFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		setLocationRelativeTo(null);
		setVisible(true);

		init();

	}

	public void init() {
		setLayout(new GridLayout(1, 1, 0, 0));

		Screen screen = new Screen();

		add(screen);
		pack();
	}

}
