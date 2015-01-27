package cim.murray.russell.util;

public class LoopHelper {
	int length;
	int current = 0;
	
	public LoopHelper(int length){
		this.length = length-1;
	}

	public void next(){
		current++;
		if(current> length){
			current=0;
		}
	}
	
	public int getCurrent(){
		return current;
	}
	
	public int getLength(){
		return length;
	}
}
