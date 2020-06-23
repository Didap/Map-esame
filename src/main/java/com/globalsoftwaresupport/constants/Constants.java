package com.globalsoftwaresupport.constants;

public class Constants {

	private Constants() {

	}

	// titolo del gioco
	public static final String TITLE = "Map Invaders";

	public static final int BOARD_WIDTH = 900;
	public static final int BOARD_HEIGHT = 750;
	public static final int BORDER_RIGHT = 50;
	public static final int BORDER_LEFT = 50;
	public static final int GROUND = 700;

	public static final int BOMB_HEIGHT = 5;

	public static final int ENEMY_SHIP_HEIGHT = 24;
	public static final int ENEMY_SHIP_WIDTH = 32;
	public static final int ENEMY_SHIP_INIT_X = 400;
	public static final int ENEMY_SHIP_INIT_Y = 100;
	public static final int ENEMY_SHIPS_ROW = 4;
	public static final int ENEMY_SHIPS_COLUMN = 8;

	public static final int GO_DOWN = 30;
	public static final int NUMBER_OF_ALIENS_TO_DESTROY = 32;
	public static final double BOMB_DROPPING_PROBABILITY = 0.0005;
	// velocità di gioco (più è alto più è difficile il gioco)
	public static final int GAME_SPEED = 10;
	public static final int SPACESHIP_WIDTH = 34;
	public static final int SPACESHIP_HEIGHT = 28;

	public static final int LASER_HORIZONTAL_TRANSLATION = 4;

	// grafiche per il gioco
	public static final String UFO_IMAGE_URL = "images/ufo.png";
	public static final String LASER_IMAGE_URL = "images/laser.png";
	public static final String BOMB_IMAGE_URL = "images/bomb.png";
	public static final String BACKGROUND_IMAGE_URL = "images/background.jpg";
	public static final String SPACESHIP_URL = "images/spaceship.png";

	// messaggi di stato del gioco
	public static final String WIN = "VITTORIA!";
	public static final String GAME_OVER = "GAME OVER!";
}
