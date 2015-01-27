package cim.murray.russell;

public class Score {
	private static int score;
	
	public void initScore(){
		score = 0;
	}
	
	public void scorePlusPlus(){
		score+= 100;
	}
	
	public int getScore(){
		return score;
	}

}
