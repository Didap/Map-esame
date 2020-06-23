package com.globalsoftwaresupport.model;

import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.Image;
import com.globalsoftwaresupport.image.ImageFactory;

public class Bomb extends Sprite {

	public Bomb(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}

	private void initialize() {
		ImageIcon imageIcon = ImageFactory.createImage(Image.BOMB);
		setImage(imageIcon.getImage());
	}

	@Override
	public void move() {
		
		this.y++;

		//impediamo alla bomba di andare oltre al pannello di gioco, quando accade die() la killa
		if (y >= Constants.BOARD_HEIGHT - Constants.BOMB_HEIGHT) {
			die();
		}
	}
}
