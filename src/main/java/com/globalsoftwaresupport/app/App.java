package com.globalsoftwaresupport.app;
import java.awt.EventQueue;

import com.globalsoftwaresupport.ui.GameMainFrame;

public class App {

	public static void main(String[] args) {
		
		//we create a new Thread for the game. This is usually called "game thread"
		EventQueue.invokeLater(() -> {
			new GameMainFrame();
		});
	}
}
