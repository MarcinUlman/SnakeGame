package dev.ulman.snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;

import javax.swing.JPanel;
import javax.swing.Timer;

import org.javatuples.Pair;

import dev.ulman.snake.entities.Apple;
import dev.ulman.snake.entities.Snake;
import dev.ulman.snake.gui.GameOverDialogFrame;

public class Board extends JPanel implements ActionListener {

	private static final long serialVersionUID = 1L;

	private static final int WIDTH = 800, HEIGHT = WIDTH; // board size
	private int size = 25; // game size
	private int tileSize = WIDTH / size;

	private HashMap<Pair<Integer, Integer>, Boolean> board;
	private ArrayList<Apple> apples;
	private Snake snake;

	private boolean running = false;
	private Timer timer;

	public Board() {
		super();
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		initBoard();
		this.apples = new ArrayList<Apple>();
		this.snake = new Snake(tileSize, size / 2, 3); // game init values
		gameInit();

		addKeyListener(new KeyListener() {
			public void keyPressed(KeyEvent e) {
				snake.changeMoveDirection(e);
			}

			public void keyReleased(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

			public void keyTyped(KeyEvent arg0) {
				// TODO Auto-generated method stub

			}

		});
	}

	private void initBoard() {
		board = new HashMap<Pair<Integer, Integer>, Boolean>();
		for (int i = 0; i < size; i++) {
			for (int j = 0; j < size; j++) {
				Pair<Integer, Integer> field = new Pair<Integer, Integer>(i, j);
				board.put(field, true);
			}
		}
	}

	private void gameInit() {
		running = true;
		timer = new Timer(snake.getSpeed(), this);
		timer.start();
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		// printrin grig lines
//		g.setColor(Color.BLACK);
//		for (int i = 0; i < board.size(); i++) {
//			g.drawLine((i + 1) * tileSize, 0, (i + 1) * tileSize, HEIGHT);
//		}
//		for (int i = 0; i < board.size(); i++) {
//			g.drawLine(0, (i + 1) * tileSize, WIDTH, (i + 1) * tileSize);
//		}

		for (Apple apple : apples) {
			apple.draw(g);
		}
		snake.draw(g);
	}

	public void actionPerformed(ActionEvent e) {
		if (running) {
			checkApple();
			snakeMove();
			checkCollisions();
		}
		repaint();

	}

	private void checkCollisions() {
		Pair<Integer, Integer> snakeHead = snake.getBodyCoordinates(0);
		if (snakeHead.getValue0() < 0 || snakeHead.getValue0() >= size || snakeHead.getValue1() < 0
				|| snakeHead.getValue1() >= size) {
			GameOverDialogFrame endGameDialog = new GameOverDialogFrame(snake.getEatedApples());
			running = false;
		} else if (!board.get(snakeHead)) {
			if (apples.get(0).getCoordinates().equals(snakeHead)) {
				snake.eatApple(apples.get(0).getCoordinates());
				apples.remove(0);
			} else {
				GameOverDialogFrame endGameDialog = new GameOverDialogFrame(snake.getEatedApples());
				running = false;
			}
		}
	}

	private void snakeMove() {
		snake.move();
		for (int i = 1; i < snake.size(); i++) {
			board.put(snake.getBodyCoordinates(i), false);
		}
		board.put(snake.getTailPosition(), true);
	}

	private void checkApple() {
		if (apples.size() == 0) {
			Apple apple;
			do {
				apple = new Apple(tileSize, size);
			} while (!board.get(apple.getCoordinates()));
			board.put(apple.getCoordinates(), false);
			apples.add(apple);
		}
	}

}
