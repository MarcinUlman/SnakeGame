package dev.ulman.snake.gui;

import javax.swing.JFrame;

import dev.ulman.snake.graphics.Board;

public class SnakeFrame extends JFrame {

	public SnakeFrame() {
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		setTitle("Snake");
		setResizable(false);
		setLocationByPlatform(true);
		setVisible(true);

		init();

	}

	public void init() {
		Board board = new Board();

		add(board);
		pack();
	}

}
