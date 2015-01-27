package cim.murray.russell.sprites;

import java.awt.image.BufferedImage;

import cim.murray.russell.Animation;
import cim.murray.russell.timer.LoopTimer;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class PlayerSprite extends NewAnimatedSprite{
	
	
	
	public PlayerSprite(BufferedImage[] image, double x, double y){
		super(image,x,y);
		setupAnimations();
	}

	

	
	
	
	//---------------------Initialize Animations---------------------
	
	private void setupAnimations(){
		int[] stillFrames = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15};
		int[] stillOdds = {100, 100,100, 100,100, 100,100, 100,100, 100,100, 100,100, 100,100, 100};
		addAnimation("still", stillFrames, stillOdds);
		int[] rightFrames = {16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31};
		int[] rightOdds = {25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25};
		addAnimation("right", rightFrames, rightOdds);
		int[] leftFrames = {32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47};
		int[] leftOdds = {25,25,25,25,25,25,25,25,25,25,25,25,25,25,25,25};
		addAnimation("left", leftFrames, leftOdds);
	}
	




	
}
