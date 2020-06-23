package com.globalsoftwaresupport.model;
import com.globalsoftwaresupport.image.Image;
import com.globalsoftwaresupport.image.ImageFactory;

public class EnemyShip extends Sprite {

	//i nemici non muoiono, diventano invisibili...lo so, potevo fare di meglio T_T
	private boolean visible = true;
	
    public EnemyShip(int x, int y) {
    	this.x = x;
    	this.y = y;
        initialize();
    }

    private void initialize() {
        var image = ImageFactory.createImage(Image.UFO);
        setImage(image.getImage());
    }

    public void move(int direction) {
        this.x += direction;
    }
    
    public boolean isVisible() {
    	return this.visible;
    }
    
    public void setVisible(boolean visible) {
    	this.visible = visible;
    }

	@Override
	public void move() {
		//abbiamo bisogno anche della direzione!
	}
}
