package cim.murray.russell;

import java.awt.Dimension;

import com.golden.gamedev.GameLoader;

public class main {
	
	static int screenX = 1280;
	static int screenY = 800;
	static MouseBombz mouseBombz = new MouseBombz(screenX, screenY);
	

	/**
	 * where everything starts
	 * @param args
	 */
	public static void main(String[] args){
		
		GameLoader game = new GameLoader();
		game.setup(mouseBombz, new Dimension(screenX,screenY), false);
		game.start();
	}

	
	

}
