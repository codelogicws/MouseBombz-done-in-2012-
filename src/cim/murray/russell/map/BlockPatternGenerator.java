package cim.murray.russell.map;

import java.util.Random;

import cim.murray.russell.util.RandomRolls;



/**
 * 
 * @author Russell Murray (Killutch)
 *
 */
public class BlockPatternGenerator {
	int[] currentBlockList = new int[3];
	int[] currentLinePattern;
	int[] blockTypes;
	int[] blockOdds;
	int[][] mapArray;
	RandomRolls rr;

	
	//Declaring the line patterns here program will brake these down and use them as
	//patterns
	int[][] linePatterns = {{1,2},{1,2,2},/*{1,2,3,2},*/{1,1,2,2},{1,1}};
	
	/**
	 * Generates a new pattern
	 * @param x size of x for new pattern
	 * @param y size of y for new pattern
	 * @param blockTypes each element in the array represents a block id
	 * @param blockOdds the odds of the block showing up in the map elements in this array
	 * line up with the block ids in "blockType" array.
	 */
	public BlockPatternGenerator(int x, int y, int[] blockTypes, int[] blockOdds){
		//Declarations
		this.blockTypes = blockTypes;
		this.blockOdds = blockOdds;
		rr = new RandomRolls();
		
		 mapArray = new int[x][y];
		
		for(int a=0;a<y;a++){
			setCurrentLinePattern();
			setCurrentBlockList();
			for(int b=0;b<x;b++){
				mapArray[b][a] = blockTypes[getCurrentBlock(b)];
			}
		}
		
	}
	
	public int[][] getMapArray(){
		return mapArray;
	}
	
	
	private void setCurrentBlockList(){
		currentBlockList = rr.rollWinners(blockTypes, blockOdds, 3);
	}
	
	private int getCurrentBlock(int position){
		int ret=0;
		int temp = currentLinePattern[position%currentLinePattern.length];
		ret = currentBlockList[temp-1];
		return ret;
	}


	private void setCurrentLinePattern() {
		Random rand = new Random();
		int selection = rand.nextInt(linePatterns.length);
		int arrayLength = nonZeroArrayElements(linePatterns[selection]);
		currentLinePattern = new int[linePatterns[selection].length];
		
		
		for(int i=0;i<arrayLength;i++){
			currentLinePattern[i] = linePatterns[selection][i];
		}
		
	}
	
	private int nonZeroArrayElements(int[] array) {
		int ret =0;
		for(int i=0;i<array.length;i++){
			if(array[i] > 0){
				ret++;
			}else{
				break;
			}
		}
		return ret;
	}
	
}
