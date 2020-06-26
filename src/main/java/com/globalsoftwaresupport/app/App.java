package com.globalsoftwaresupport.app;
import java.awt.EventQueue;

import com.globalsoftwaresupport.ui.GameMainFrame;

public class App {

	public static void main(String[] args) {
		
		//creiamo una nuova istanza di gioco
		EventQueue.invokeLater(() -> {
			new GameMainFrame();
		});
	}
}
