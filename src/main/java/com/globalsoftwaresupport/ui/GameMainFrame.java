package com.globalsoftwaresupport.ui;

import javax.swing.JFrame;
import com.globalsoftwaresupport.constants.Constants;
import com.globalsoftwaresupport.image.Image;
import com.globalsoftwaresupport.image.ImageFactory;

public class GameMainFrame extends JFrame {

	public GameMainFrame() {
		initializeLayout();
	}

	private void initializeLayout() {

       add(new GamePanel());
        
        setTitle(Constants.TITLE);
        
        
        //Utilizziamo il frame.pack anzichè il frame.setSize() perchè fitta componente per componente la size preferita
        pack();
        setIconImage(ImageFactory.createImage(Image.SPACESHIP).getImage());
        
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        setResizable(false);
        setVisible(true);
    }
}
