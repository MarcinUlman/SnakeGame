package dev.ulman.snake.graphics;

import java.awt.Dimension;
import java.awt.Graphics;

import javax.swing.JPanel;

public class Screen extends JPanel implements Runnable {

	private static final int WIDTH = 500;
	private static final int HEIGHT = 500;

	private Thread thread;
	private boolean running = false;

	public Screen() {
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
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
		System.out.println("running...");
	}
	
	public void repaint(Graphics g) {
		
	}
	
	
}
