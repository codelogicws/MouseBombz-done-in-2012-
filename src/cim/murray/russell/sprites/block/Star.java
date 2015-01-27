package cim.murray.russell.sprites.block;

import java.awt.image.BufferedImage;

import cim.murray.russell.sprites.NewAnimatedSprite;

public class Star extends NewAnimatedSprite{

	public Star(BufferedImage[] image, double x, double y) {
		super(image, x, y);
		setupAnimations();
	}
	
	
	private void setupAnimations(){
		int[] stillFrames = {0,1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20};
		int[] stillOdds = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,25,25};
		addAnimation("still", stillFrames, stillOdds);
		int[] rightFrames = {21,22,23,24,25,26,27,28,21,22,23,24,25,26,27,28,21,21,21,21,21};
		int[] rightOdds = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,2};
		addAnimation("fall", rightFrames, rightOdds);
		int[] fallingFrames ={29,30,31,32,33,34,35,36,37,38,39,40,41,42,43,44,45,46,47,48,29};
		int[] fallingtime = {50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,50,2};
		addAnimation("falling", fallingFrames, fallingtime);
	}

}
