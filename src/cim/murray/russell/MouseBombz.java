package cim.murray.russell;

import java.awt.Graphics2D;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

import cim.murray.russell.collision.BombBlockCollision;
import cim.murray.russell.collision.PlayerBombCollision;
import cim.murray.russell.map.PatternGenerator;
import cim.murray.russell.sprites.BlockDistribution;
import cim.murray.russell.sprites.BombSprite;
import cim.murray.russell.sprites.NewAnimatedSprite;
import cim.murray.russell.sprites.PlayerSprite;
import cim.murray.russell.sprites.ScoreSprite;
import cim.murray.russell.sprites.block.Balloon;
import cim.murray.russell.sprites.block.Diamond;
import cim.murray.russell.sprites.block.Skull;
import cim.murray.russell.sprites.block.Star;

import com.golden.gamedev.Game;
import com.golden.gamedev.object.Background;
import com.golden.gamedev.object.CollisionManager;
import com.golden.gamedev.object.PlayField;
import com.golden.gamedev.object.Sprite;
import com.golden.gamedev.object.SpriteGroup;
import com.golden.gamedev.object.background.ImageBackground;

/**
 * 
 * @author Russell Murray (Killutch)
 *
 */
public class MouseBombz extends Game{
	{ distribute = true;}
	//Game Settings
	double playersFixedY = 550;
	boolean gamePlayed = false;
	int screenX;
	int screenY;
	int scoreXLocation;
	int scoreYLocation;
	int scoreSpacing;
	int scoreDigits;
	
	
	//Game Status
	boolean playerMoved = false;
	double difficultyMultiplayer = 1;
	
	//Game Data
	GameData gameData = new GameData();
	Score score = new Score();
	LastScore lastScore = new LastScore();

	
	//Sprite Groups
	SpriteGroup PLAYER_GROUP;
	SpriteGroup BOMB_GROUP;
	SpriteGroup BLOCK_GROUP;
	
	//Game States
	public final int GAME_MENU = 0;
	public final int GAME_ACTIVE = 1;
	public final int QUIT_GAME = 2;
	int currentGameState = GAME_MENU;

	
	//CollisionManagers
	CollisionManager playerBombCollision;
	CollisionManager bombBlockCollision;

	
	//game sprite's or entity's
	ScoreSprite[] scoreSprites;
	NewAnimatedSprite backgroundSprite;
	Background background;
	PlayField gameboard;
	PlayerSprite player;
	Skull[] skullBlocks;
	Diamond[] diamondBlocks;
	Balloon[] balloonBlocks;
	Star[] starBlocks;
	BombSprite bomb;
	
	//main menu stats
	public final int START=0;
	public final int QUIT=1;
	int currentMenuItem = START;
	
	public MouseBombz(int x, int y){
		setScreenSize(x, y);
	}
	/*-****************************************************************
	//**************************Initialize*****************************
	 ******************************************************************/

	public void initResources() {
		initBackground();
		initPlayer();
		initBomb();
		setupMap();
		intScore(28, 50);
		player.startAnimation();
		initPlayField();
		initBlocks();
		setupGroups();
		setupCollision();
		setActiveAll(true);
	}



	/*-**********************************************************************
	//***************************Update Game Entities**********************
	 ***********************************************************************/
	public void update(long elapseTime) {
		switch(currentGameState){
		case GAME_MENU:
			menuInitIfNeeded();
			scoreUpdate(lastScore.getScore());
			gameboard.update(elapseTime);
			keyboardInputMainMenu(elapseTime);
			playerUpdate();
			break;
		
		case GAME_ACTIVE:
			checkGameOver();
			scoreUpdate(score.getScore());
			activeInitIfNeeded();
			collisionTest(elapseTime);
			gameboard.update(elapseTime);
			keyboardInput(elapseTime);
			playerUpdate();
			bombUpdate();
			blockUpdate();
			playerMoved = false;
			levelDone();
			break;
			
		case QUIT_GAME:
			
			break;
		}
	}

	/*-**********************************************************************
	//*****************************Draw Screen*******************************
	 ************************************************************************/
	public void render(Graphics2D g) {
		//this command renders all game sprites basically
		gameboard.render(g);
		
	}
	
	
	//-------------initialization methods
	private void intScore(int Yloc, int Xloc) {
		score.initScore();
		scoreYLocation = screenY - Yloc;
		scoreSpacing = 20;
		scoreXLocation = Xloc;
		scoreDigits = 15;
		scoreSprites  = new ScoreSprite[scoreDigits];
		int currentXLocation;
		for(int i=0;i<scoreSprites.length;i++){
			currentXLocation = (i*scoreSpacing)-scoreSpacing+scoreXLocation;
			scoreSprites[i] = new ScoreSprite(getImages("images"+System.getProperty("file.separator")+"numbers.png",11,1),currentXLocation,scoreYLocation);
		}
	}
	
	public void setScreenSize(int x, int y){
		screenX=x;
		screenY=y;
	}
	
	private void initBomb(){
		gameData.setBombSpeed(0.3);
		bomb = new BombSprite(getImages("images"+System.getProperty("file.separator")+"bomb.png",10,1),screenX/2,screenY-300);
		bomb.update(bomb.getAnimationModeNumber("spinning"));
		bomb.startAnimation();
		bomb.setActive(false);
	}
	
	private void initPlayer() {
		player = new PlayerSprite(getImages("images"+System.getProperty("file.separator")+"mouse.png",16,4),600,playersFixedY);
		playersFixedY = screenY - 150;
		gameData.setPlayerSpeed(0.6);
		
	}
	
	private void initBlocks(){
		BufferedImage[] skull = getImages("images"+System.getProperty("file.separator")+"skull.png",20,2);
		BufferedImage[] diamond = getImages("images"+System.getProperty("file.separator")+"diamond.png",19,2);
		BufferedImage[] balloon = getImages("images"+System.getProperty("file.separator")+"balloon.png",15,2);
		BufferedImage[] star = getImages("images"+System.getProperty("file.separator")+"star.png",5,10);
		BlockDistribution bd = new BlockDistribution(skull, diamond, balloon, star, gameboard,screenX,screenY,BLOCK_GROUP);
		BLOCK_GROUP = bd.getBlockGroup();
		skullBlocks = bd.getSkullBlocks();
		diamondBlocks = bd.getDiamondBlocks();
		starBlocks = bd.getStarBlocks();
		balloonBlocks = bd.getBalloonBlocks();
		}

	private void initPlayField() {
		gameboard = new PlayField(background);
		gameboard.add(backgroundSprite);
		gameboard.add(player);
		gameboard.add(bomb);
		
		for(int i=0;i<scoreSprites.length;i++){
			gameboard.add(scoreSprites[i]);
		}
	}

	private void initBackground() {
		background = new ImageBackground(getImage("images"+System.getProperty("file.separator")+"MouseBombzBG.jpg"));
		backgroundSprite = new NewAnimatedSprite(getImages("images"+System.getProperty("file.separator")+"back.png",2,1),0,0);
		backgroundSprite.setFrame(0);
	}
	
	private void setupMap(){
		PatternGenerator pg = new PatternGenerator();
		
	}
	
	
	//---Collision
	private void collisionTest(long elapseTime){
		playerBombCollision.checkCollision();
		bombBlockCollision.checkCollision();
	}
	
	
	
	//---Updating
	public void scoreUpdate(int currentScore){
		String scoreInt = ""+currentScore;
		int[] scoreArray = new int[scoreInt.length()];
		for(int i=0;i<scoreInt.length();i++){
			scoreArray[i] = Integer.parseInt(scoreInt.substring(i, i+1));
			drawNumber(scoreSprites, scoreArray[i], i);
		}
		
		for(int i=0;i<scoreDigits-scoreInt.length();i++){
			scoreSprites[i+scoreInt.length()].updateAnimation("empty");
		}
		
	}
	
	private void checkGameOver(){
		if(gameData.isGameOver()){
			lastScore.initScore();
			lastScore.setScore(score.getScore());
			initResources();
			currentGameState = GAME_MENU;
			gamePlayed = true;
			gameData.setGameOver(false);
		}
	}


	private void drawNumber(ScoreSprite[] scoreSprites2, int value, int where) {
		scoreSprites2[where].updateAnimation(value);
	}

	public void playerUpdate(){
		if(playerMoved==false){
			player.updateAnimation(player.getAnimationModeNumber("still"));
		}
	}
	
	public void levelDone(){
		if(checkLevelDone()){
			initBackground();
			initPlayer();
			initBomb();
			setupMap();
			player.startAnimation();
			initPlayField();
			initBlocks();
			setupGroups();
			setupCollision();
			setActiveAll(true);
			backgroundSprite.setFrame(1);
			if(difficultyMultiplayer<1.5){
				difficultyMultiplayer += .10;
			}
			setNewSpeeds();
		}
	}
	
	private void setNewSpeeds() {
		gameData.setBombSpeed(gameData.getBombSpeed()*difficultyMultiplayer);
		//gameData.setPlayerSpeed(gameData.getPlayerSpeed()*difficultyMultiplayer);
	}

	public boolean checkLevelDone(){
		boolean ret = false;
		for(int i=0;i<skullBlocks.length;i++){
			if(skullBlocks[i].isActive()){
				ret = true;
			}
		}
		
		if(ret==false){
			for(int i=0;i<starBlocks.length;i++){
				if(starBlocks[i].isActive()){
					ret = true;
				}
			}
		}
		
		if(ret==false){
			for(int i=0;i<balloonBlocks.length;i++){
				if(balloonBlocks[i].isActive()){
					ret = true;
				}
			}
		}
		
		if(ret==false){
			for(int i=0;i<diamondBlocks.length;i++){
				if(diamondBlocks[i].isActive()){
					ret = true;
				}
			}
		}
		
		ret = !ret;
		
		return ret;
	}
	
	public void bombUpdate(){
		bomb.updateAnimation("spinning");
		bomb.updateLocation(screenX, screenY);
	}
	
	public void blockUpdate(){
		for(int i=0;i<skullBlocks.length;i++){
			skullBlocks[i].updateAnimation("still");
		}
		for(int a=0;a<diamondBlocks.length;a++){
			diamondBlocks[a].updateAnimation("still");
		}
		for(int b=0;b<balloonBlocks.length;b++){
			balloonBlocks[b].updateAnimation("still");
		}
		for(int c=0;c<starBlocks.length;c++){
			starBlocks[c].updateAnimation("still");
		}
		

	}
	
	/**
	 * handles all the key presses
	 * @param elapsedTime how much time has gone by since last update
	 */
	private void keyboardInput(long elapsedTime) {
		if(keyPressed(KeyEvent.VK_ESCAPE)){
			initResources();
			currentGameState = GAME_MENU;
		}else if(keyDown(KeyEvent.VK_RIGHT)){
			if(player.getX()+125<screenX){
				player.setLocation(player.getX()+(elapsedTime*gameData.getPlayerSpeed()), playersFixedY);
				player.updateAnimation(player.getAnimationModeNumber("right"));
				playerMoved = true;
			}

		}else if(keyDown(KeyEvent.VK_LEFT)){
			if(player.getX()+20>0){
				player.setLocation(player.getX()-(elapsedTime*gameData.getPlayerSpeed()), playersFixedY);
				playerMoved = true;
				player.updateAnimation(player.getAnimationModeNumber("left"));
			}
			
			
		}
		
	}
	
	private void keyboardInputMainMenu(long elapsedTime){
		if(keyDown(KeyEvent.VK_ENTER)){
			if(currentMenuItem == START){
				currentGameState = GAME_ACTIVE;
			}else{
				finish();
			}
			
		}else if(keyDown(KeyEvent.VK_SPACE)){
			if(currentMenuItem == START){
				currentGameState = GAME_ACTIVE;
			}else{
				finish();
			}
		}else if(keyPressed(KeyEvent.VK_ESCAPE)){
			finish();
		}
		
		if(currentMenuItem == START){
			if(keyPressed(KeyEvent.VK_DOWN)){
				currentMenuItem = QUIT;
				player.setLocation(75, 415);
			}
		}else if (currentMenuItem == QUIT){
			if(keyPressed(KeyEvent.VK_UP)){
				currentMenuItem = START;
				player.setLocation(75, 300);
			}
		}
	}
	
	private void menuInitIfNeeded(){
		if(menuNeedsInit()){
			setActiveAll(false);
			player.setActive(true);
			player.setLocation(75, 300);
			backgroundSprite.setFrame(0);
			currentMenuItem = START;
		}
	}
	
	private void activeInitIfNeeded(){
		if(activeNeedsInit()){
			setActiveAll(true);
			backgroundSprite.setFrame(1);
			player.setLocation(600,playersFixedY);
		}
	}
	
	private boolean activeNeedsInit(){
		return !bomb.isActive();
	}
	
	private boolean menuNeedsInit(){
		return bomb.isActive();
	}
	
	//---Collision
	private void setupCollision(){
		playerBombCollision = new PlayerBombCollision();
		playerBombCollision.setCollisionGroup(PLAYER_GROUP, BOMB_GROUP);
		bombBlockCollision = new BombBlockCollision();
		bombBlockCollision.setCollisionGroup(BOMB_GROUP, BLOCK_GROUP);
	}
	
	
	//---Sprite Groups
	private void setupGroups(){
		PLAYER_GROUP = gameboard.addGroup(new SpriteGroup("Player Group"));
		BOMB_GROUP = gameboard.addGroup(new SpriteGroup("Bomb Group"));
		PLAYER_GROUP.add(player);
		BOMB_GROUP.add(bomb);
	}
	
	//------------------Hide / Show Objects------------------------------
	private void setActiveAll(boolean activeState){
		player.setActive(activeState);
		bomb.setActive(activeState);
		
		for(int i=0;i<skullBlocks.length;i++){
			skullBlocks[i].setActive(activeState);
		}
		
		for(int i=0;i<starBlocks.length;i++){
			starBlocks[i].setActive(activeState);
		}
		
		for(int i=0;i<diamondBlocks.length;i++){
			diamondBlocks[i].setActive(activeState);
		}
		
		for(int i=0;i<balloonBlocks.length;i++){
			balloonBlocks[i].setActive(activeState);
		}
	}
	
	

}
