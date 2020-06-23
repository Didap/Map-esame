package com.globalsoftwaresupport.ui;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GameLoop implements ActionListener {

	private GamePanel board;
	
	public GameLoop(GamePanel board) {
		this.board = board;
	}
	
	
        // in ogni iterazione facciamo 2 cose: aggiorniamo le posizioni delle sprites e renderizziamo loro sullo schermo
	@Override
	public void actionPerformed(ActionEvent e) {
		board.doOneLoop();
	}
}
