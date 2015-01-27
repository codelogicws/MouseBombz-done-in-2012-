package cim.murray.russell.sprites;

import java.awt.image.BufferedImage;

import cim.murray.russell.Animation;

public class ScoreSprite extends NewAnimatedSprite{
	
	
	public ScoreSprite(BufferedImage[] image, double x, double y){
		super(image,x,y);
		setupAnimations();
	}

	
	
	//---------------------Initialize Animations---------------------
	
	private void setupAnimations(){
		int[] frame = {0};
		int[] timeing = {5000};
		addAnimation("0", frame, timeing);
		frame[0] = 1;
		timeing[0] = 5000;
		addAnimation("1", frame, timeing);
		frame[0] = 2;
		timeing[0] = 5000;
		addAnimation("2", frame, timeing);
		frame[0] = 3;
		timeing[0] = 5000;
		addAnimation("3", frame, timeing);
		frame[0] = 4;
		timeing[0] = 5000;
		addAnimation("4", frame, timeing);
		frame[0] = 5;
		timeing[0] = 5000;
		addAnimation("5", frame, timeing);
		frame[0] = 6;
		timeing[0] = 5000;
		addAnimation("6", frame, timeing);
		frame[0] = 7;
		timeing[0] = 5000;
		addAnimation("7", frame, timeing);
		frame[0] = 8;
		timeing[0] = 5000;
		addAnimation("8", frame, timeing);
		frame[0] = 9;
		timeing[0] = 5000;
		addAnimation("9", frame, timeing);
		frame[0] = 10;
		timeing[0] = 5000;
		addAnimation("empty", frame, timeing);
	}
	



}
