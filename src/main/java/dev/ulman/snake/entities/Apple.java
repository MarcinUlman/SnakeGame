package dev.ulman.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

import org.javatuples.Pair;

public class Apple {

	private Pair<Integer, Integer> coordinates;
	private int tileSize;
	Color color;

	public Apple(int tileSize, int boardSize) {
		super();
		put(boardSize);
		this.tileSize = tileSize;
		setColor();
	}

	private void put(int boardSize) {
		Random random = new Random();
		int xCoor = random.nextInt(boardSize);
		int yCoor = random.nextInt(boardSize);
		this.coordinates = new Pair<Integer, Integer>(xCoor, yCoor);
	}

	public void draw(Graphics g) {
		g.setColor(color);
		int arc = (int) (0.5 * tileSize);
		g.fillRoundRect(coordinates.getValue0() * tileSize, coordinates.getValue1() * tileSize, tileSize, tileSize, arc,
				arc);
	}

	private void setColor() {
		Random randColor = new Random();
		this.color = new Color(randColor.nextInt(255), randColor.nextInt(255), randColor.nextInt(255));
	}

	public Pair<Integer, Integer> getCoordinates() {
		return coordinates;
	}
}
