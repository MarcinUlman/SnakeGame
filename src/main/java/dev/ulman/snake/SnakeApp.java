package dev.ulman.snake;

import java.awt.EventQueue;

import dev.ulman.snake.gui.SnakeFrame;

public class SnakeApp {

	public static void main(String[] args) {

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				SnakeFrame frame = new SnakeFrame();
			}
		});

	}

}
