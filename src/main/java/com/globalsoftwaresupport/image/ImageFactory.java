package com.globalsoftwaresupport.image;
import javax.swing.ImageIcon;

import com.globalsoftwaresupport.constants.Constants;

public class ImageFactory {

	public static ImageIcon createImage(Image image) {
		
		ImageIcon imageIcon = null;
		
		switch (image) {
		case UFO:
			imageIcon = new ImageIcon(Constants.UFO_IMAGE_URL);
			break;
		case LASER:
			imageIcon = new ImageIcon(Constants.LASER_IMAGE_URL);
			break;
		case BOMB:
			imageIcon = new ImageIcon(Constants.BOMB_IMAGE_URL);
			break;
		case BACKGROUND:
			imageIcon = new ImageIcon(Constants.BACKGROUND_IMAGE_URL);
			break;
		case SPACESHIP:
			imageIcon = new ImageIcon(Constants.SPACESHIP_URL);
			break;
		default:
			return null;
		}
		
		return imageIcon;
	}
}
