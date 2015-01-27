package cim.murray.russell.collision;

import java.net.URL;

import cim.murray.russell.GameData;
import cim.murray.russell.MouseBombz;

import com.golden.gamedev.engine.audio.WaveRenderer;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.collision.BasicCollisionGroup;

public class PlayerBombCollision extends BasicCollisionGroup{
	GameData gameData = new GameData();
	private long timeLastHit=System.currentTimeMillis()-300000;//subtracting the time by anything to allow hit detection right off the bat
	
	public PlayerBombCollision(){
		pixelPerfectCollision =true;
	}

	@Override
	public void collided(Sprite player, Sprite bomb) {
		
		double totalSpeed = gameData.getBombSpeed();
		double newYSpeed;
		double newXSpeed;
		
		int hitLocation = (int)(player.getX()-bomb.getX()+22);
		
		
		newYSpeed = -(bomb.getVerticalSpeed());
		newXSpeed = -(bomb.getHorizontalSpeed());
		
		
		if(hitLocation > 50){
			newYSpeed = -(totalSpeed*.2);
			newXSpeed = -(totalSpeed*.8);
		}else if(hitLocation >35){
			newYSpeed = -(totalSpeed*.5);
			newXSpeed = -(totalSpeed*.5);
		}else if(hitLocation>12){
			newYSpeed = -(totalSpeed*.75);
			newXSpeed = -(totalSpeed*.25);
		}else if(hitLocation>0){
			newYSpeed = -(totalSpeed*.90);
			newXSpeed = -(totalSpeed*.10);
		}else if (hitLocation >-12){
			newYSpeed = -(totalSpeed*.90);
			newXSpeed = totalSpeed*.10;
		}else if(hitLocation >-35){
			newYSpeed = -(totalSpeed*.75);
			newXSpeed = totalSpeed*.25;
		}else if(hitLocation >-50){
			newYSpeed = -(totalSpeed*.5);
			newXSpeed = totalSpeed*.5;
		}else{
			newYSpeed = -(totalSpeed*.2);
			newXSpeed = totalSpeed*.8;
		}
		
		
		if(System.currentTimeMillis()>timeLastHit+300){
			timeLastHit = System.currentTimeMillis();
			bomb.setSpeed(newXSpeed, newYSpeed);
		}else{
			bomb.setSpeed(newXSpeed, bomb.getVerticalSpeed());
		}
		
	}
		
}
