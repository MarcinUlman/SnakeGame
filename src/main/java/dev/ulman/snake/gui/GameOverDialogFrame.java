package dev.ulman.snake.gui;

import javax.swing.JOptionPane;

public class GameOverDialogFrame {

	public GameOverDialogFrame(int score) {
		JOptionPane window = new JOptionPane();
		window.showMessageDialog(null, "Your score: " + score, "Game Over", JOptionPane.PLAIN_MESSAGE);
	}

}
