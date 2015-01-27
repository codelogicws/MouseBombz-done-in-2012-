package cim.murray.russell.sprites;

import java.awt.image.BufferedImage;

import cim.murray.russell.Animation;
import cim.murray.russell.GameData;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class BombSprite extends NewAnimatedSprite{
	GameData gameData = new GameData();
	
	
	public BombSprite(BufferedImage[] image, double x, double y){
		super(image,x,y);
		setSpeed(0, .03);
		setupAnimations();
	}

	
	private void setupAnimations() {
		int[] spinningFrames = {0,1,2,3,4,5,6,7,8,9};
		int[] spinningDuration = {100,100,100,100,100,100,100,100,100,100};
		addAnimation("spinning", spinningFrames, spinningDuration);
	}


	public void updateLocation(int xScreenSize, int yScreenSize){
		
		if(getY()<-32){
			setSpeed(getHorizontalSpeed(), (Math.abs(getVerticalSpeed())));
		}else if(getY()+90>yScreenSize){
			gameData.setGameOver(true);
		}else if(getX()<-32){
			setSpeed(Math.abs(getHorizontalSpeed()),getVerticalSpeed());
		}else if(getX()+90>xScreenSize){
			setSpeed(-(Math.abs(getHorizontalSpeed())), getVerticalSpeed());
		}
		
	}


}
