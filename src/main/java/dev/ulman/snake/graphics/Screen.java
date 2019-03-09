package dev.ulman.snake.graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.JPanel;

import dev.ulman.snake.entities.Apple;
import dev.ulman.snake.entities.BodyPart;

public class Screen extends JPanel implements Runnable {

	private static final int WIDTH = 800, HEIGHT = 800;

	private Thread thread;
	private boolean running = false;

	private BodyPart bodyPart;
	private ArrayList<BodyPart> snake;

	private int xCoor = 10, yCoor = 10;
	private int size = 5;

	private boolean right = true, left = false, up = false, down = false; // moving directory
	private int ticks = 0;
	
	private Key key;
	
	private Apple apple;
	private ArrayList<Apple> apples;

	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		setFocusable(true);
		key = new Key();
		addKeyListener(key);
		
		snake = new ArrayList<BodyPart>();
		apples = new ArrayList<Apple>();

		start();
	}

	public void start() {
		this.running = true;
		this.thread = new Thread(this, "Game");
		thread.start();
	}

	public void stop() {
		running = false;
		try {
			thread.join();
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
	}

	public void run() {
		while (this.running) {
			thick();
			repaint();
		}
	}

	public void thick() {
		if (snake.size() == 0) {
			bodyPart = new BodyPart(xCoor, yCoor, 10);
			snake.add(bodyPart);
		}
		
		if (apples.size() == 0) {
			Random random = new Random();
			
			int xCoor = random.nextInt(WIDTH / 10 - 1);
			int yCoor = random.nextInt(HEIGHT / 10 - 1);
			apple = new Apple(xCoor, yCoor, 10);
			apples.add(apple);
		}
		
		for (int i = 0; i < apples.size(); i++) {
			if (xCoor == apple.getxCoor() && yCoor == apple.getyCoor()) {
				size++;
				apples.remove(i);
				i--;
			}
		}
		
		for (int i = 0; i < snake.size(); i++) {
			if(xCoor == snake.get(i).getxCoor() && yCoor == snake.get(i).getyCoor()) {
				if (i != snake.size() - 1) {
					stop();
				}
			}
		}
		
		if (xCoor < 0 || xCoor > WIDTH / 10 - 1 || yCoor < 0 || yCoor > HEIGHT / 10 - 1) {
			stop();
		}

		ticks++;
		if (ticks > 500000) {
			if (right)
				xCoor++;
			if (left)
				xCoor--;
			if (up)
				yCoor--;
			if (down)
				yCoor++;

			ticks = 0;
			bodyPart = new BodyPart(xCoor, yCoor, 10);
			snake.add(bodyPart);

			if (snake.size() > size) {
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
		for (int i = 0; i < apples.size(); i++) {
			apples.get(i).draw(g);
		}
	}

	private class Key implements KeyListener {

		public void keyPressed(KeyEvent e) {
			int key = e.getKeyCode();
			if (key == KeyEvent.VK_RIGHT && !left) {
				right = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_LEFT && !right) {
				left = true;
				up = false;
				down = false;
			}
			if (key == KeyEvent.VK_UP && !down) {
				left = false;
				up = true;
				right = false;
			}
			if (key == KeyEvent.VK_DOWN && !up) {
				left = false;
				down = true;
				right = false;
			}
		}

		public void keyReleased(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

		public void keyTyped(KeyEvent arg0) {
			// TODO Auto-generated method stub

		}

	}

}
