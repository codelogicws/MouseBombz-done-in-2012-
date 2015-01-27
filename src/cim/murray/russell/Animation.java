package cim.murray.russell;

import cim.murray.russell.timer.LoopTimer;

import cim.murray.russell.util.ArrayHandler;
/**
 * 
 * @author Russell Murray (Killutch)
 *
 */
public class Animation {
	
	LoopTimer timer;
	
	// animationArray [Modes example Left or Right] [Frames To Play In Animation] [Duration in Millis]
	int[][][] animationArray;
	int currentMode;
	int currentTime=0;
	String animationName[];
	
	
	//*************************Constructors********************************
	/**
	 * make sure to call addMode to specify animation frames. this class stores diffrient modes that you
	 * can switch take for example you have a animation to walk left or right this can switch the animation
	 * for you.
	 */
	public Animation(){
		animationArray = new int[0][0][0];
		animationName = new String[0];
		currentMode = 0;
		timer = new LoopTimer();
		timer.makeTimer(200);
	}


	

	//************************Add New Mode(New Animation)********************
	/**
	 * All animations are made up of modes each mode is a new animation
	 * @param modeName name you want to call the mode
	 * @param animationFrames what frame numbers you want to use
	 * @param frameDurations this array matches with animationFrames and represents the duration each frame
	 * is to run in milliseconds.
	 */
	public void addMode(String modeName, int[] animationFrames, int[] frameDurations){
		increaseArraySizes(animationFrames.length);
		writeModeNameData(modeName);
		writeFrameData(animationFrames);
		writeDurationData(frameDurations);
	}
	
	
	//************************Sprite Called Methods*************************
	/**
	 * switch animation mode
	 * @param newMode this is a int representation of the mode if you know the name you can call
	 * getAnimationModeNumber(modeName);
	 * @return
	 */
	public int switchMode(int newMode) {
		currentMode=newMode;
		timer.makeTimer(calcTime());
		return grabFrameFromTime();
	}
	
	
	//returns the current frame to be played in the animation
	public int currentFrame() {
		currentTime = timer.getTime();
		return grabFrameFromTime();
	}
	
	//***************************Get Methods*********************************
	public int getCaurrentMode() {
		return currentMode;
	}
	
	public int getAnimationModeNumber(String modeName) {
		int ret=0;
		for(int i=0;i<animationName.length;i++){
			if(animationName[i].equalsIgnoreCase(modeName)){
				return i;
			}
		}
		return 0;
	}

	//**************************Time***********************************
	public void startAnimation(){
		timer.makeTimer(calcTime());
	}
	
	private int calcTime() {
		int ret=0;
		for(int i=0;i<animationArray[currentMode].length;i++){
			ret=ret+animationArray[currentMode][i][1];
		}
		return ret;
	}
	
	//get frame for this moment in time
	private int grabFrameFromTime(){
		int ret=0;
		int frameAnimationTime=0;
		for(int i=0;i<animationArray[currentMode].length;i++){
			frameAnimationTime = frameAnimationTime + animationArray[currentMode][i][1];
			if(currentTime<=frameAnimationTime){
				ret=animationArray[currentMode][i][0];
				break;
			}
		}
		return ret;
	}
	
	//**********************Add Animation Back End************************
	private void increaseArraySizes(int frames){
		ArrayHandler arrayH = new ArrayHandler();
		animationName = arrayH.resizeArray(animationName, animationName.length+1);
		animationArray = arrayH.resize3DArray(animationArray, animationArray.length+1, frames, 2);
	}
	
	private void writeModeNameData(String ModeName){
		animationName[animationName.length-1]=ModeName;
	}
	
	private void writeFrameData(int[] frames){
		for(int i=0;i<frames.length;i++){
			try{
				animationArray[animationArray.length-1][i][0]=frames[i];
			}catch(Exception e){}
		}
	}
	
	private void writeDurationData(int[] frameDurations){
		for(int i=0;i<frameDurations.length;i++){
			try{
				animationArray[animationArray.length-1][i][1]=frameDurations[i];
			}catch(Exception e){}
		}
	}
	
	//**************************Debug*****************************
	public void printAnimationArray(){
		ArrayHandler arrayH = new ArrayHandler();
		arrayH.print3DArray(animationArray);
	}




	

}
