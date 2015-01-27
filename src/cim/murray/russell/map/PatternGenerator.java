package cim.murray.russell.map;

import java.util.Random;

import cim.murray.russell.util.ArrayHandler;
import cim.murray.russell.util.MyMath;
import cim.murray.russell.util.RandomRolls;




/**
 * This class goes through a serious of pattern algorithms to make a pattern file this pattern file will then tell the MapGenerator
 * where it is allowed to generate blocks.
 * @author Russell John Murray (killutch)
 *
 */
public class PatternGenerator {
	
	/**
	 * create a new patterns with the the best pattern filters or makers combined into one.
	 * @param x size of pattern
	 * @param y size of pattern
	 * @return pattern that is ready to be use with a map
	 */
	public boolean[][] createFullPattern(int x, int y){
		Pattern shape = createShapePattern(x, y, 75, 90, 10);
		Pattern columns = createColumnsPattern(x, y, 75, 20);
		Pattern current = patternFuse(shape, columns);
		return current.getArray();
	}
	
	
	/**
	 * this pattern clears out the bottom area so player has room to play
	 * @param x size of pattern
	 * @param y size of pattern
	 * @param bottomClearPercentage of much space should be cleared for player to play
	 * @return
	 */
	private Pattern createBottomSpace(int x, int y, int bottomClearPercentage){
		boolean[][] patArray = new boolean[x][y];
		MyMath mMath = new MyMath();
		int endRow = x-(mMath.percentageOf(x, bottomClearPercentage));
		for(int i=0;i<x;i++){
			for(int a=0;a<endRow;a++){
				patArray[i][a] = true;
			}
		}
		Pattern ret = new Pattern(patArray);
		return ret;
	}
	
	/**
	 * This creates a pattern that has all the void columns in it.
	 * @param x size of pattern
	 * @param y size of pattern
	 * @param changeablePercentage Default 75 this is how much of X is changeable (or able to have void rows) starting from center
	 * @param percentageVoidColumns Default 20 how often there is a row that will have air rather then blocks
	 * @return
	 */
	private Pattern createColumnsPattern(int x, int y, int changeablePercentage, int percentageVoidColumns){
		boolean[] voidColumns = makeVoidColumns(x, percentageVoidColumns, changeablePercentage);
		return makeColumnPattern(x, y, voidColumns);
	}

	
	/**
	 * This class creates a pattern for the outside shape that can later be used to fuse with other patterns or used
	 * to make a map.
	 * @param x X axis size for pattern
	 * @param y Y axis size for pattern
	 * @param rowRetainPercentage default 75 starting from center what percentage of the X axis has to be blocks and
	 * not air. when the number of blocks protected is calculated starting from the middle.
	 * @param changeChance default 90 this program calculates 1 row at a time this number is the percentage chance that
	 * the row will change its size.
	 * @param directionChance default 10 every time a row changes it has a direction boolean that tells it weather to
	 * grow or shrink. this percentage tells it the odds it has of changing directions. example say this number is 10
	 *  if a row shrunk in one action the next row by default it would want to shrink again but there would be a 10
	 *  percent chance it would switch directions and grow.
	 * @return
	 */
	private Pattern createShapePattern(int x, int y, int rowRetainPercentage, int changeChance, int directionChance){
		MyMath mMath = new MyMath();
		int[] rowSizes= makeRowSizes(mMath.percentageOf(x, rowRetainPercentage), x, y, changeChance, directionChance);
		return new Pattern(makeAllRows(x, rowSizes));
	}
	
	
	/**
	 * This combines 2 patterns to make one. goes through every element in the patterns and only the elements that
	 * say true for both will be true in the result. 
	 * @param p1 Pattern 1
	 * @param p2 Pattern 2
	 * @return 
	 */
	private Pattern patternFuse(Pattern p1, Pattern p2){
		boolean[][] patBool = new boolean[p1.getPatternWidth()][p2.getPatternHeight()];
		boolean[][] pattern1 = p1.getPatternMap();
		boolean[][] pattern2 = p2.getPatternMap();
		if(patternSizeSame(p1, p2)){
			for(int i=0;i<pattern1.length;i++){
				for(int a=0;a<pattern1[0].length;a++){
					if(pattern1[i][a] && pattern2[i][a]){
						patBool[i][a] = true;
					}
				}
			}
		}else{
			System.out.println("error the to patterns you attempted to fuse are not the same size");
		}
		return new Pattern(patBool);
	}
	
	/**
	 * checks to see if the pattern sizes are the same for 2 patterns
	 * @param p1 Pattern 1
	 * @param p2 Pattern 2
	 * @return true if patterns are the same size.
	 */
	private boolean patternSizeSame(Pattern p1, Pattern p2){
		boolean ret = false;
		if(p1.getPatternWidth() == p2.getPatternWidth() && p1.getPatternHeight() == p2.getPatternHeight()){
			ret = true;
		}
		return ret;
	}
	
	/**
	 * Randomly generates how many blocks are in each row
	 * @param minSize min amount of blocks in each row
	 * @param maxSize max amount of blocks in each row
	 * @param entitiesNeeded how many rows there are
	 * @param changeChance odds in percentage that a row will change size
	 * @param directionChance odds that a row will change direction form the way its growing shrinking
	 * @return returns a array each item in the array is a number of how many blocks there should be in that row
	 */
	private int[] makeRowSizes(int minSize, int maxSize, int NumberOfRows, int changeChance, int directionChance){
		int[] returnArray = new int[NumberOfRows];
		RandomRolls roll = new RandomRolls();
		Random rand = new Random();
		int size = roll.randomFromTo(minSize, maxSize);
		boolean grow = rand.nextBoolean();
		
		for(int i=0;i<NumberOfRows;i++){
			grow = roll.booleanChange(grow, directionChance);
			size = roll.changeByOne(size, changeChance, grow, minSize, maxSize);
			returnArray[i]=size;
		}
		return returnArray;
	}
	
	/**
	 * This class makes row by row meaning it draws the pattern across the x axis then moves down on the y axis to make the next row.
	 * This class
	 * @param xLength width of the pattern to be made. X axis
	 * @param rowSizes this is an array[YaxisBlocks] in each node the information for how many blocks should be on that row is stored.
	 * @return
	 */
	private boolean[][] makeAllRows(int xLength, int[] rowSizes){
		boolean[][] pattern =  new boolean[xLength][rowSizes.length];
		boolean[] currentRow = new boolean[xLength];
		ArrayHandler aHand = new ArrayHandler();
		
		for(int i=0;i<rowSizes.length;i++){
			currentRow = makeARow(xLength, rowSizes[i]);
			aHand.insert1ArrayInto2DArray(pattern, currentRow, i);
		}
		return pattern;
	}
	
	/**
	 * this class makes an array with random columns taken out. so random places will have full Y lines down the map of no blocks.
	 * @param x how big x should be for this pattern
	 * @param y how big y should be for this pattern
	 * @param voidColumns array represents the x axis and where the air columns will be. example array[5]{true, true, false, true, ture}
	 * this would mean that the middle row will be air. so all down the Y axis at (x 2) it will be air.
	 * @return column patter that will probably be fused with other patterns to make a more complete patterns
	 */
	private Pattern makeColumnPattern(int x, int y, boolean[] voidColumns) {
		boolean[][] pat = new boolean[x][y];
		boolean tempBool;
		for(int i=0;i<x;i++){
			tempBool = voidColumns[i];
			for(int a=0;a<y;a++){
				pat[i][a] = tempBool;
			}
		}
		return new Pattern(pat);
	}

	
	/**
	 * make a single row
	 * @param rowSize how many blocks wide is this row
	 * @param numberOfBlocks how many blocks are placed in that rows
	 * @return elements that are true are areas with a possible block. false means no block 
	 */
	private boolean[] makeARow(int rowSize, int numberOfBlocks) {
		boolean[] retArray = new boolean[rowSize];
		MyMath mMath = new MyMath();
		numberOfBlocks = mMath.matchOddEven(rowSize, numberOfBlocks);
		int voidBlocksEachSide = (rowSize-numberOfBlocks)/2;
		int firstVoidBlockRightSide = rowSize-voidBlocksEachSide;
		
		for(int i=0;i<rowSize;i++){
			if(i < voidBlocksEachSide){
				retArray[i]=false;
			}else if(i >= firstVoidBlockRightSide){
				retArray[i]=false;
			}else{
				retArray[i]=true;
			}
		}
		return retArray;
	}
	
	/**
	 * this makes a 1 line array that shows what rows will be air/block spaces. This method basically just calls
	 * 2 other methods to do all the work. calcHalfVoidColumns makes half of the array randomly and then
	 * calcFullVoidColumns mirrors that result the reason we use mirror is it looks nicer when the level is
	 * "perfectly symmetrical" (soul eaters quote)
	 * @param x this is the length of how many grid spaces on the x axis
	 * @param percentageVoidColumns percentage of rows that will be rows of air. Of course this is based on a
	 * roll and your only setting the odds not the actual percentage.
	 * @param changeblePercentage example say this number is 80 and your x size was 100. the center 80 blocks
	 * have a chance of getting air rows while the outside 10 blocks on both sides will not get such rows
	 * @return example: array[5]{true, true, false, true, true} this would mean the center row of the map
	 * should be air space.
	 */
	private boolean[] makeVoidColumns(int x, int percentageVoidColumns, int changeblePercentage) {
		boolean[] ret = new boolean[x];
		boolean[] halfVoidColumns = calcHalfVoidColumns(x, percentageVoidColumns, changeblePercentage);
		ret = calcFullVoidColumns(halfVoidColumns, x);
		return ret;
	}
	
	/**
	 * This takes halfVoidColums and mirrors it to make a full VoidColumn array that shows which columns
	 * should be void columns. if the x is odd and halfVoidColumns and halfVoidColumns mirrored doesn't
	 * equal a fullVoidColumn this method will take care of it. This method also takes halfVoidColumns and
	 * mirrors it for the other side of this array.
	 * @param halfVoidColumns this is half of the work this array tells which columns will be air columns and
	 * this array gets mirrored to make a larger array thats more complete
	 * @param x this is the size of the patterns x that the given array will be use to help create.
	 * @return returns a array that will tell you where the columns that will be air are located
	 */
	private boolean[] calcFullVoidColumns(boolean[] halfVoidColumns, int x) {
		boolean[] ret = new boolean[x];
		MyMath mMath = new MyMath();
		if(halfVoidColumns.length*2 == x-1){
			ret[halfVoidColumns.length] = true;
		}
		for(int i=0;i<halfVoidColumns.length;i++){
			ret[i] = halfVoidColumns[i];
			ret[mMath.lineMirror(i, x-1)] = ret[i];
		}
		return ret;
	}

	/**
	 * this randomly produces rows to be false or true influenced by your percentages.
	 * @param x this array was created to represent half of the full array needed this x is the x for this array only
	 * not the pattern that it will end up assisting in creating. so simply see x as the size for the array that this
	 * method will return.
	 * @param percentageVoidColumns percentage of chance each row has of being a air or void row
	 * @param changeblePercentage what percentage of this array do you want possible void rows on.
	 * @return array that has random false elements that represent the columns that will be void this array usually.
	 * represents only half of the final array that will tell  makeColumnPattern where it needs to make void columns.
	 */
	private boolean[] calcHalfVoidColumns(int x, int percentageVoidColumns, int changeblePercentage){
		boolean[] ret = new boolean[x];
		RandomRolls roll = new RandomRolls();
		
		for(int i=0;i<x;i++){
			if(i>findLeftBoundry(x, changeblePercentage) && i<findRightBoundry(x, changeblePercentage) && roll.percentageRoll(percentageVoidColumns)){
				ret[i] = false;
			}else{
				ret[i] = true;
			}
		}
		return ret;
	}
	
	/**
	 * think of containerSize as a row and percentage as how much space blocks take up in the center of that row
	 * this method tells you at what location on the right of the center does the 
	 * @param containerSize
	 * @param percentage
	 * @return
	 */
	private int findLeftBoundry(int containerSize, int percentage){
		int ret;
		ret = containerSize-(int)(containerSize*(percentage*.01));
		return ret;
	}
	
	/**
	 * think of containerSize as a row and percentage as how much space blocks take up in the center of that row
	 * this method tells you at what location on the right of the center does the 
	 * @param containerSize
	 * @param percentage
	 * @return
	 */
	private int findRightBoundry(int containerSize, int percentage){
		int ret;
		ret = findLeftBoundry(containerSize, percentage);
		ret = containerSize - ret;
		return ret;
	}
	
}
