package com.globalsoftwaresupport.callbacks;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

import com.globalsoftwaresupport.ui.GamePanel;


//dobbiamo tracciare l'evento key perchè è come muoviamo la nostra nave
public class GameEventListener extends KeyAdapter {

	private GamePanel board;

	public GameEventListener(GamePanel board) {
		this.board = board;
	}

	
        //questo metodo è chiamato ogni volta che l'utente rilascia una key che ha utilizzato, come destra e sinistra per il movimento
	@Override
	public void keyReleased(KeyEvent e) {
		this.board.keyReleased(e);
	}


	//questo metodo è chiamato ogni volta che l'utente preme una key che vuole utilizzare, come destra e sinistra per il movimento
	@Override
	public void keyPressed(KeyEvent e) {
		this.board.keyPressed(e);
	}
}
