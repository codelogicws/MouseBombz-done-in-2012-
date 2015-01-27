package cim.murray.russell.sprites.block;

import java.awt.image.BufferedImage;

import cim.murray.russell.sprites.NewAnimatedSprite;

public class Diamond extends NewAnimatedSprite{

	public Diamond(BufferedImage[] image, double x, double y) {
		super(image, x, y);
		setupAnimations();
	}
	
	private void setupAnimations(){
		int[] stillFrames = {18,17,12,11,10,9,8,2,1,0};
		int[] stillTime = {100,100,100,100,100,100,100,100,100,100};
		addAnimation("still", stillFrames, stillTime);
		int[] rightFrames = {20,21,22,23,24,20,21,22,23,24};
		int[] rightTime = {100,100,100,100,100,100,100,100,100,100};
		addAnimation("fall", rightFrames, rightTime);
		int[] fallingFrames = {25,26,27,28,29,30,31,32,33,34};
		int[] fallingTime = {100,100,100,100,100,100,100,100,100,100};
		addAnimation("falling", fallingFrames, fallingTime);
	}

}
