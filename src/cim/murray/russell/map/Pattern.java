package cim.murray.russell.map;



/**
 * @author Russell John Murray (Killutch)
 *
 */
public class Pattern {
	public boolean[][] patternMap;
	public int patternWidth;
	public int patternHeight;

	
	
	/**
	 * builds the pattern off an array of boolean. format patternMap[Xaxis][Yaxis]. if true then square belongs there
	 * @param newPatternMap array of the pattern
	 */
	Pattern(boolean[][]newPatternMap){
		patternWidth = newPatternMap.length;
		patternHeight = newPatternMap[0].length;
		patternMap = newPatternMap;
	}
	
	/**
	 * builds the pattern off x and y sizes. format patternMap[X][Y]. in patternMap true means a square belongs there
	 * false means it will be a air block.
	 * @param x how many x blocks on the grid for this pattern
	 * @param y how many y blocks on the grid for this pattern
	 */
	Pattern(int x, int y){
		patternWidth = x;
		patternHeight = y;
		patternMap = new boolean[x][y];
	}
	
	/**
	 * Simply gives you the patternMap array. format patternMap[Xaxis][Yaxis]. if true then square belongs there
	 * if false then square should be air.
	 * @return array of the pattern
	 */
	public boolean[][] getPatternMap(){
		boolean[][] returnPatternMap = patternMap;
		return returnPatternMap;
	}
	
	/**
	 * Simply sets patternMap array. format patternMap[Xaxis][Yaxis]. if true block belongs at cordiant
	 * @param newPatternMap array of the pattern
	 */
	public void makePatternMap(boolean[][] newPatternMap, int newPatternWidth, int newPatternHeight){
		patternMap = newPatternMap;
	}
	
	/**
	 * returns width of the pattern
	 * @return the width of the pattern
	 */
	public int getPatternWidth(){
		return patternWidth;
	}
	
	/**
	 * Returns height of the pattern.
	 * @return the height of the pattern
	 */
	public int getPatternHeight(){
		return patternHeight;
	}
	
	/**
	 * print pattern in console. Print is in text format for debugging purposes
	 */
	public void printPattern(){
		for(int i=0;i<patternMap[0].length;i++){
			for(int a=0;a<patternMap.length;a++){
				System.out.print(patternMap[a][i] + " ");
			}
			System.out.print("\n");
		}
	}
	
	//----------------------------------get methods------------------------------
	public boolean[][] getArray(){
		return patternMap;
	}
	

	
	
}
