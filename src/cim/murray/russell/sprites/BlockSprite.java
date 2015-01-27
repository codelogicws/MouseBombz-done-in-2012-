package cim.murray.russell.sprites;

import java.awt.image.BufferedImage;

import cim.murray.russell.Animation;

import com.golden.gamedev.object.sprite.AdvanceSprite;

public class BlockSprite extends AdvanceSprite{
	
	Animation spriteAnimation = new Animation();
	
	private String[] names;
	
	public BlockSprite(BufferedImage[] image, double x, double y){
		super(image,x,y);
	}

	private void setupAnimations(String name, int[] frameNumbers, int[] frameDurations) {
		spriteAnimation.addMode(name, frameNumbers, frameDurations);
	}
	
	//----------------------------------Get Methods--------------------------------------
	public int getModeNumber(String modeName){
		return spriteAnimation.getAnimationModeNumber(modeName);
	}
	
	//----------------------------------Set Animation------------------------------------
	
}
