package dev.ulman.snake.entities;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

import org.javatuples.Pair;

public class Snake {

	private ArrayList<BodyPart> body;
	private int speed;
	private int eatedApples;
	private ArrayList<Pair<Integer, Integer>> growPlace;
	private int tileSize;

	private enum MOVEMENT_DICECTION {
		UP, DOWN, RIGHT, LEFT
	};

	private MOVEMENT_DICECTION currentMoveDicection;
	private Pair<Integer, Integer> tailPosition;

	public Snake(int tileSize, int starTile, int startingBodyLength, int speed) {
		super();
		this.tileSize = tileSize;
		this.speed = speed;
		this.eatedApples = 0;
		this.currentMoveDicection = MOVEMENT_DICECTION.RIGHT;
		this.growPlace = new ArrayList<Pair<Integer, Integer>>();
		create(starTile, starTile, startingBodyLength);
	}

	public Snake(int tileSize, int starTile, int startingBodyLength) {
		this(tileSize, starTile, startingBodyLength, 500);
	}

	public int size() {
		return this.body.size();
	}

	private void create(int xCoor, int yCoor, int startingBodyLength) {
		this.body = new ArrayList<BodyPart>();
		addHead(xCoor, yCoor);
		for (int i = 0; i < startingBodyLength - 1; i++) {
			addBody(xCoor - (i + 1), yCoor);
		}
	}

	private void addHead(int xCoor, int yCoor) {
		BodyPart head = new BodyPart(xCoor, yCoor, tileSize);
		head.setColor(Color.BLACK);
		this.body.add(head);
	}

	private void addBody(int xCoor, int yCoor) {
		this.body.add(new BodyPart(xCoor, yCoor, tileSize));
	}

	public void increaseSpeed() {
		if (eatedApples % 5 == 0) {
			this.speed -= 25;
		}
	}

	public void eatApple(Pair<Integer, Integer> apple) {
		this.eatedApples++;
		growPlace.add(apple);

		increaseSpeed();
	}

	public void draw(Graphics g) {
		for (BodyPart bodyPart : body) {
			bodyPart.draw(g);
		}
	}

	public void move() {
		int xHeadPosiotion = body.get(0).getxCoor();
		int yHeadPosiotion = body.get(0).getyCoor();

		switch (currentMoveDicection) {
		case UP:
			yHeadPosiotion--;
			break;
		case DOWN:
			yHeadPosiotion++;
			break;
		case RIGHT:
			xHeadPosiotion++;
			break;
		case LEFT:
			xHeadPosiotion--;
			break;
		default:
			break;
		}
		tailPosition = body.get(size() - 1).getCoordinates();
		for (BodyPart bodyPart : body) {
			int tempX = bodyPart.getxCoor();
			int tempY = bodyPart.getyCoor();
			bodyPart.setxCoor(xHeadPosiotion);
			bodyPart.setyCoor(yHeadPosiotion);
			xHeadPosiotion = tempX;
			yHeadPosiotion = tempY;
		}
		grow();
	}

	private void grow() {
		if (this.growPlace.size() > 0 && tailPosition.equals(growPlace.get(0))) {
			addBody(tailPosition.getValue0(), tailPosition.getValue1());
			this.growPlace.remove(0);
		}
	}

	public void changeMoveDirection(KeyEvent e) {
		int key = e.getKeyCode();
		if (key == KeyEvent.VK_UP && currentMoveDicection != MOVEMENT_DICECTION.DOWN) {
			currentMoveDicection = MOVEMENT_DICECTION.UP;
		}
		if (key == KeyEvent.VK_DOWN && currentMoveDicection != MOVEMENT_DICECTION.UP) {
			currentMoveDicection = MOVEMENT_DICECTION.DOWN;
		}
		if (key == KeyEvent.VK_RIGHT && currentMoveDicection != MOVEMENT_DICECTION.LEFT) {
			currentMoveDicection = MOVEMENT_DICECTION.RIGHT;
		}
		if (key == KeyEvent.VK_LEFT && currentMoveDicection != MOVEMENT_DICECTION.RIGHT) {
			currentMoveDicection = MOVEMENT_DICECTION.LEFT;
		}
	}

	public int getSpeed() {
		return this.speed;
	}

	public int getEatedApples() {
		return this.eatedApples;
	}

	public Pair<Integer, Integer> getBodyCoordinates(int i) {
		Pair<Integer, Integer> coordinates = body.get(i).getCoordinates();
		return coordinates;
	}

	public Pair<Integer, Integer> getTailPosition() {
		return tailPosition;
	}
}
