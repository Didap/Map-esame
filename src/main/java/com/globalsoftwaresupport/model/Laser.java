package com.globalsoftwaresupport.model;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.Image;
import com.globalsoftwaresupport.image.ImageFactory;

public class Laser extends Sprite {

	public Laser() {

	}
	
	public Laser(int x, int y) {
		this.x = x;
		this.y = y;
		initialize();
	}

	private void initialize() {

		var image = ImageFactory.createImage(Image.LASER);
		setImage(image.getImage());

		setX(x + Constants.SPACESHIP_WIDTH/2);
		setY(y);
	}
	
	@Override
	public void move() {
		
		//il laser ascende, da sotto a sopra...
		this.y -= Constants.LASER_HORIZONTAL_TRANSLATION;

		//fino a morire se supera lo schema di gioco (come le bombe, al contrario)
		if (y < 0) {
			this.die();
		} 
	}
}
