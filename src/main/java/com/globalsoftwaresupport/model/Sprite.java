package com.globalsoftwaresupport.model;

import java.awt.Image;
// questa classe astratta serve come dispensa di metodi comuni a tutte le sprite
// che la extenderanno ereditando così di base tutte le variabili e metodi
// infatti ogni nave, nemico, laser o bomba necessità prima o poi di
// essere inizializzata, di morire o di muoversi nello schema di gioco
abstract public class Sprite {

	private Image image;
	private boolean dead;
	protected int x;
	protected int y;
	protected int dx;

	public abstract void move();
	
	public Sprite() {
		this.dead = false;
	}

	public void die() {
		this.dead = true;
	}

	public void setImage(Image image) {
		this.image = image;
	}

	public Image getImage() {
		return this.image;
	}

	public void setX(int x) {
		this.x = x;
	}

	public void setY(int y) {
		this.y = y;
	}

	public int getY() {
		return this.y;
	}

	public int getX() {
		return this.x;
	}

	public void setDead(boolean dead) {
		this.dead = dead;
	}

	public boolean isDead() {
		return this.dead;
	}
}
