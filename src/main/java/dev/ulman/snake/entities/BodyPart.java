package dev.ulman.snake.entities;

import java.awt.Color;
import java.awt.Graphics;

public class BodyPart {

	private int xCoor, yCoor;
	private int width, height;

	public BodyPart(int xCoor, int yCoor, int tileSize) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		this.width = tileSize;
		this.height = tileSize;
	}
	
	public void tick() {
		
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(xCoor * width, yCoor * height, width, height);
		g.setColor(Color.GREEN);
		g.fillRect(xCoor * width + 2, yCoor * height + 2, width - 2, height - 4);
	}
	
}
