package cim.murray.russell.sprites.block;

import java.awt.image.BufferedImage;

import cim.murray.russell.sprites.NewAnimatedSprite;

public class Balloon extends NewAnimatedSprite{

	public Balloon(BufferedImage[] image, double x, double y) {
		super(image, x, y);
		setupAnimations();
	}
	
	
	
	private void setupAnimations(){
		int[] stillFrames = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22};
		int[] stillOdds = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50};
		addAnimation("still", stillFrames, stillOdds);
		int[] rightFrames = {23,24,25,26,27,28,29,23,24,25,26,27,28,29,23,23,23,23,23,23,23,23,23};
		int[] rightOdds = {100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100,100};
		addAnimation("fall", rightFrames, rightOdds);
	}

}
