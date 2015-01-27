package cim.murray.russell.collision;


import cim.murray.russell.Score;

import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class BombBlockCollision extends BasicCollisionGroup{
	Score score = new Score();
	
	public BombBlockCollision(){
		pixelPerfectCollision =true;
	}

	@Override
	public void collided(Sprite bomb, Sprite block) {
		block.setActive(false);
		double bombXLocation = (bomb.getX()+(bomb.getWidth()/2));
		double bombYLocation = (bomb.getY()+(bomb.getHeight()/2));
		double blockXLocation = (block.getX())+(block.getWidth()/2);
		double blockYLocation = (block.getY())+(block.getHeight()/2);
		
		double xDistance = blockXLocation - bombXLocation;
		double yDistance = blockYLocation - bombYLocation;
		
		if(Math.abs(xDistance)>=Math.abs(yDistance)){
			bomb.setSpeed(-(bomb.getHorizontalSpeed()), bomb.getVerticalSpeed());
		}else{
			bomb.setSpeed(bomb.getHorizontalSpeed(), -(bomb.getVerticalSpeed()));
		}
		
		score.scorePlusPlus();
	}

}
