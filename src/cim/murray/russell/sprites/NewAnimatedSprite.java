package cim.murray.russell.sprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.sprite.AdvanceSprite;

import cim.murray.russell.Animation;

public class NewAnimatedSprite extends AdvanceSprite{
	
public Animation spriteAnimation = new Animation();
	
	
	public NewAnimatedSprite(BufferedImage[] image, double x, double y){
		super(image,x,y);
	}
	
	//----------------------Get Methods----------------------------
	public int getAnimationModeNumber(String modeName) {
		return spriteAnimation.getAnimationModeNumber(modeName);
	}
	
	
	//---------------------Animation Handling
	public void startAnimation(){
		spriteAnimation.startAnimation();
	}
	
	public void updateAnimation(int animationMode){
		if(spriteAnimation.getCaurrentMode()!=animationMode){
			changeMode(animationMode);
		}else{
			playAnimation();
		}
	}
	
	public void updateAnimation(String mode){
		updateAnimation(getAnimationModeNumber(mode));
	}
	
	private void changeMode(int newMode){
		this.setFrame(spriteAnimation.switchMode(newMode));
	}
	
	private void playAnimation(){
		this.setFrame(spriteAnimation.currentFrame());
	}
	
	
	
	
	//---------------------Setup Animations---------------------
	
	public void addAnimation(String modeName, int[]frameNumbers, int[]frameDurations){
		spriteAnimation.addMode(modeName, frameNumbers, frameDurations);
	}



}
