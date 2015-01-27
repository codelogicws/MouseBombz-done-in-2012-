package cim.murray.russell.util;

import cim.murray.russell.sprites.block.Balloon;
import cim.murray.russell.sprites.block.Diamond;
import cim.murray.russell.sprites.block.Skull;
import cim.murray.russell.sprites.block.Star;

public class SpriteArrayHandler {
	
	Skull[] skullArray;
	Star[] starArray;
	Balloon[] balloonArray;
	Diamond[] diamondArray;
	
	public Skull[] addSkullToArray(Skull skull, Skull[] modifyArray){
		skullArray = new Skull[modifyArray.length+1]; 
		for(int i=0;i<modifyArray.length;i++){
			skullArray[i] = modifyArray[i];
		}
		skullArray[modifyArray.length]=skull;
		return skullArray;
	}
	
	public Star[] addStarToArray(Star star, Star[] modifyArray){
		starArray = new Star[modifyArray.length+1];
		for(int i=0;i<modifyArray.length;i++){
			starArray[i] = modifyArray[i];
		}
		starArray[modifyArray.length]=star;
		return starArray;
				
	}
	
	public Balloon[] addBalloonToArray(Balloon balloon, Balloon[] modifyArray){
		balloonArray = new Balloon[modifyArray.length+1];
		for(int i=0;i<modifyArray.length;i++){
			balloonArray[i] = modifyArray[i];
		}
		balloonArray[modifyArray.length]=balloon;
		return balloonArray;
				
	}
	
	public Diamond[] addDiamondToArray(Diamond diamond, Diamond[] modifyArray){
		diamondArray = new Diamond[modifyArray.length+1];
		for(int i=0;i<modifyArray.length;i++){
			diamondArray[i] = modifyArray[i];
		}
		diamondArray[modifyArray.length]=diamond;
		return diamondArray;
				
	}
	
	

}
