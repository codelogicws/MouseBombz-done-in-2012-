package cim.murray.russell.timer;

public class LoopTimer {
	
	public long startTime;
	public long timerLength;
	
	public void makeTimer(int timerLengthMillis){
		timerLength = timerLengthMillis;
		startTimer();
	}
	
	public int getTime(){
		CheckToResetTime();
		return timePassed();
	}
	
	public void resetTimer(){
		startTimer();
	}
	
	private void startTimer(){
		startTime = System.currentTimeMillis();
	}
	
	private int timePassed(){
		return (int)(System.currentTimeMillis() - startTime);
	}
	
	private void CheckToResetTime(){
		if(timePassed()>timerLength){
			startTimer();
		}
	}

}
