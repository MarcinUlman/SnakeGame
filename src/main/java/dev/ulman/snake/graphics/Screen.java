package dev.ulman.snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.util.ArrayList;

import javax.swing.JPanel;

import dev.ulman.snake.entities.BodyPart;

public class Screen extends JPanel implements Runnable {

	private static final int WIDTH = 500, HEIGHT = 500;

	private Thread thread;
	private boolean running = false;

	private BodyPart bodyPart;
	private ArrayList<BodyPart> snake;

	private int startXCoor = 10, startYCoor = 10;
	private int size = 5;

	private boolean right = true, left = false, up = false, down = false; //moving directory
	private int ticks = 0;

	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));

		snake = new ArrayList<BodyPart>();

		start();
	}

	public void start() {
		this.running = true;
		this.thread = new Thread(this, "Game");
		thread.start();
	}

	public void stop() {

	}

	public void run() {
		while (this.running) {
			thick();
			repaint();
		}
	}

	public void thick() {
		if (snake.size() == 0) {
			bodyPart = new BodyPart(startXCoor, startYCoor, 10);
			snake.add(bodyPart);
		}

		ticks++;
		if (ticks > 25000) {
			if (right)
				startXCoor++;
			if (left)
				startXCoor--;
			if (up)
				startYCoor--;
			if (down)
				startYCoor++;
			
			ticks = 0;
			bodyPart = new BodyPart(startXCoor, startYCoor, 10);
			snake.add(bodyPart);
			
			if(snake.size() > size) {
				snake.remove(0);
			}
		}
	}

	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.BLACK);
		for (int i = 0; i < WIDTH / 10; i++) {
			g.drawLine(i * 10, 0, i * 10, HEIGHT);
		}
		for (int i = 0; i < HEIGHT / 10; i++) {
			g.drawLine(0, i * 10, WIDTH, i * 10);
		}
		for (int i = 0; i < snake.size(); i++) {
			snake.get(i).draw(g);
		}
	}

}
