package dev.ulman.snake.entities;

import java.awt.Color;
import java.awt.Graphics;

import org.javatuples.Pair;

public class BodyPart {

	private Pair<Integer, Integer> coordinates;
	private int width, height;
	private Color color = Color.GREEN;

	public BodyPart(int xCoor, int yCoor, int tileSize) {
		this.width = tileSize;
		this.height = tileSize;
		this.coordinates = new Pair<Integer, Integer>(xCoor, yCoor);
	}

	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.drawOval(coordinates.getValue0() * width, coordinates.getValue1() * height, width, height);
		g.setColor(color);
		g.fillOval(coordinates.getValue0() * width, coordinates.getValue1() * height, width, height);
	}

	public int getxCoor() {
		return coordinates.getValue0();
	}
	
	public void setxCoor(int xCoor) {
		this.coordinates = this.coordinates.setAt0(xCoor);
	}

	public int getyCoor() {
		return coordinates.getValue1();
	}

	public void setyCoor(int yCoor) {
		this.coordinates = this.coordinates.setAt1(yCoor);
	}
	
	public void setColor(Color color) {
		this.color = color;
	}
	
	public Pair<Integer, Integer> getCoordinates() {
		return coordinates;
	}
	
}
