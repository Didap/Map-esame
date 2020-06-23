package com.globalsoftwaresupport.model;


import java.awt.event.KeyEvent;

import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.Image;
import com.globalsoftwaresupport.image.ImageFactory;

public class SpaceShip extends Sprite {

    public SpaceShip() {
        initialize();
    }
    //inizializziamo la nave che controlleremo durante il gioco
    private void initialize() {

    	var imageIcon = ImageFactory.createImage(Image.SPACESHIP);
        setImage(imageIcon.getImage());

        int start_x = Constants.BOARD_WIDTH/2-Constants.SPACESHIP_WIDTH/2;
        int start_y = Constants.BOARD_HEIGHT-100;
        
        setX(start_x);
        setY(start_y);
    }

    @Override
    public void move() {

        x += dx;

        //come le bombe o il laser, anche la nave va "costretta" nel canvas di gioco a sinistra
        if (x <= Constants.SPACESHIP_WIDTH) {
            x = Constants.SPACESHIP_WIDTH;
        }

        //'' a destra (il 2* è perchè la coordinata x parte dall'angolo in alto a sinistra della nave
        if (x >= Constants.BOARD_WIDTH-2*Constants.SPACESHIP_WIDTH) {
            x =Constants.BOARD_WIDTH-2*Constants.SPACESHIP_WIDTH;
        }
    }

    public void keyPressed(KeyEvent e) {

        int key = e.getKeyCode();

        //con questo Keyevent va a sinistra se viene premuto
        if (key == KeyEvent.VK_LEFT) {
            dx = -2;
        }

        //con questo Keyevent va a destra se viene premuto
        if (key == KeyEvent.VK_RIGHT) {
            dx = 2;
        }
    }

    public void keyReleased(KeyEvent e) {

    	//dobbiamo dichiarare cosa accade anche al rilascio della key, altrimenti la nave rimarrebbe ferma...
        int key = e.getKeyCode();

        if (key == KeyEvent.VK_LEFT) {
            dx = 0;
        }

        if (key == KeyEvent.VK_RIGHT) {
            dx = 0;
        }
    }
}
