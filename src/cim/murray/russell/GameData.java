package cim.murray.russell;

public class GameData {
	private static double bombSpeed;
	private static double playerSpeed;
	private static boolean gameOver;
	
	public double getBombSpeed() {
		return bombSpeed;
	}
	
	public double getPlayerSpeed() {
		return playerSpeed;
	}
	
	
	public static boolean isGameOver() {
		return gameOver;
	}

	public static void setGameOver(boolean i) {
		gameOver = i;
	}

	public void setBombSpeed(double i) {
		bombSpeed = i;
	}
	
	public void setPlayerSpeed(double i) {
		playerSpeed = i;
	}

}
