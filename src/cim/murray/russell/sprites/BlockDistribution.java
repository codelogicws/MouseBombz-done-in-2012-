package cim.murray.russell.sprites;

import java.awt.image.BufferedImage;

import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.SpriteGroup;

import cim.murray.russell.map.MapGenerator;
import cim.murray.russell.sprites.block.Balloon;
import cim.murray.russell.sprites.block.Diamond;
import cim.murray.russell.sprites.block.Skull;
import cim.murray.russell.sprites.block.Star;
import cim.murray.russell.util.SpriteArrayHandler;

public class BlockDistribution {
	int imageWidth;
	int imageHeight;
	//margins in pixels
	int topMargin = 150;
	int rightLeftMargin = 0;
	double bottomMargin=.35;
	
	int blocksForX;
	int blocksForY;
	
	int startBlockX;
	int startBlockY;
	
	int[][] map;
	
	PlayField gameboard;
	
	SpriteArrayHandler spriteArrayH = new SpriteArrayHandler();
	
	Star starCurrent;
	Skull skullCurrent;
	Diamond diamondCurrent;
	Balloon balloonCurrent;
	
	Star[] starBlocks = new Star[0];
	Skull[] skullBlocks = new Skull[0];
	Diamond[] diamondBlocks = new Diamond[0];
	Balloon[] balloonBlocks = new Balloon[0];
	
	SpriteGroup BLOCK_GROUP;
	
	// 1 = skull, 2 = star, 3 = balloon, 4 = diamond
	int[]blockIDs = {1,2,3,4};
	int[]blockOdds = {100,100,100,100};
	
	BufferedImage[] skull;
	BufferedImage[] star;
	BufferedImage[] balloon;
	BufferedImage[] diamond;
	
	BufferedImage[] currentImages;
	
	

	//--------------------------------Constructor-----------------------------------
	public BlockDistribution(BufferedImage[] skull, BufferedImage[] diamond,
			BufferedImage[] balloon, BufferedImage[] star, PlayField gameboard,
			int screenX, int screenY, SpriteGroup BLOCK_GROUP) {
		
		this.gameboard = gameboard;
		this.BLOCK_GROUP = BLOCK_GROUP;
		this.skull = skull;
		this.star = star;
		this.balloon = balloon;
		this.diamond = diamond;
		
		calcNeededMapSize(skull, screenX, screenY);
		map = new MapGenerator(blocksForX,blocksForY,blockIDs,blockOdds).getMap();
		drawBlocks();
		
		
	}

	private void drawBlocks() {
		BLOCK_GROUP = gameboard.addGroup(new SpriteGroup("Block Group"));
		for(int a=0;a<blocksForY;a++){
			for(int b=0;b<blocksForX;b++){
				int blockNumber = map[b][a];
				if(blockNumber > 0){
					makeBlock(b,a,blockNumber);
				}
			}
		}
	}

	private void makeBlock(int x, int y, int blockNumber) {
		int currentX = imageWidth*x+startBlockX;
		int currentY = imageHeight*y+startBlockY;
		
		currentImages = skull;

		if(blockNumber==1){
			currentImages = skull;
			skullCurrent = new Skull(currentImages, currentX,currentY);
			gameboard.add(skullCurrent);
			if(skullCurrent!=null){
				BLOCK_GROUP.add(skullCurrent);
				skullCurrent.updateAnimation("still");
				skullCurrent.startAnimation();
				skullBlocks = spriteArrayH.addSkullToArray(skullCurrent, skullBlocks);
			}
			
			
		}else if(blockNumber==2){
			currentImages = star;
			starCurrent = new Star(currentImages, currentX,currentY);
			gameboard.add(starCurrent);
			if(starCurrent!=null){
				BLOCK_GROUP.add(starCurrent);
				starCurrent.updateAnimation("still");
				starCurrent.startAnimation();
				starBlocks = spriteArrayH.addStarToArray(starCurrent, starBlocks);
			}
			
			
		}else if(blockNumber==3){
			currentImages = balloon;
			balloonCurrent = new Balloon(currentImages, currentX,currentY);
			gameboard.add(balloonCurrent);
			if(balloonCurrent!=null){
				BLOCK_GROUP.add(balloonCurrent);
				balloonCurrent.updateAnimation("still");
				balloonCurrent.startAnimation();
				balloonBlocks = spriteArrayH.addBalloonToArray(balloonCurrent, balloonBlocks);
			}
			
			
		}else if(blockNumber==4){
			currentImages = diamond;
			diamondCurrent = new Diamond(currentImages, currentX,currentY);
			gameboard.add(diamondCurrent);
			if(diamondCurrent!=null){
				BLOCK_GROUP.add(diamondCurrent);
				diamondCurrent.updateAnimation("still");
				diamondCurrent.startAnimation();
				diamondBlocks = spriteArrayH.addDiamondToArray(diamondCurrent, diamondBlocks);
			}
			
		}
		
		
		
		
	}

	private void calcNeededMapSize(BufferedImage[] skull, int screenX, int screenY) {
		imageWidth = skull[0].getWidth();
		imageHeight = skull[0].getHeight();
		blocksForX = (screenX - (rightLeftMargin*2))/imageWidth;
		blocksForY = (screenY-(topMargin+(int)(bottomMargin*screenY)))/imageHeight;
		startBlockY = (((screenY-(topMargin+(int)(bottomMargin*screenY)))%imageHeight)/2)+topMargin;
		startBlockX = (((screenX - (rightLeftMargin*2))%imageWidth)/2)+rightLeftMargin;
	}
	
	public SpriteGroup getBlockGroup(){
		return BLOCK_GROUP;
	}
	
	//---------------------get methods-------------------------------
	public Skull[] getSkullBlocks(){
		return skullBlocks;
	}
	
	public Balloon[] getBalloonBlocks(){
		return balloonBlocks;
	}
	
	public Diamond[] getDiamondBlocks(){
		return diamondBlocks;
	}
	
	public Star[] getStarBlocks(){
		return starBlocks;
	}

	
}