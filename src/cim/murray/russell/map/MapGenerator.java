package cim.murray.russell.map;




/**
 * This class is used to randomly generate maps.
 * @author Russell John Murray (Killutch)
 *
 */
public class MapGenerator {
	
	public int mapWidth;
	public int mapHeight;
	public int numberOfBlocks;
	// percentage format .75 would = 75%
	public final double minBlockFillPercentage = 0.1;
	public double PercentageScreenBlocksCanFill = .75;
	public int[][] map;
	
	
	public MapGenerator(){
	}
	
	public MapGenerator(int mapX, int mapY, int[] blockNumbers, int[] blockOdds){
		createMap(mapX, mapY, blockNumbers, blockOdds);
	}
	
	/**
	 * Used to create a map
	 * @param mapX
	 * @param mapY
	 * @param numberOfBlockTypes example if you have 4 diffrient randomly genarated blocks
	 * this number would be 4.
	 * @return
	 */
	public int[][] createMap(int mapX, int mapY, int[] blockNumbers, int[] blockOdds){
		//shape generation
		PatternGenerator pg = new PatternGenerator();
		boolean[][] pattern = pg.createFullPattern(mapX, mapY);
		//block generation
		BlockPatternGenerator bpg = new BlockPatternGenerator(mapX, mapY, blockNumbers, blockOdds);
		//fuse the 2
		int[][] returnMap = fuseBlockAndNormalPatterns(pattern, bpg.getMapArray());
		
		return returnMap;
	}
	
	/**
	 * both arrays need to be the same size this takes a shape pattern and a block pattern
	 * and makes 1 map.
	 * @param shapePattern
	 * @param blockPattern
	 * @return
	 */
	public int[][] fuseBlockAndNormalPatterns(boolean[][] pattern, int[][] blockArray){
		int[][] mapArray = new int[pattern.length][pattern[0].length];
		
		for(int i=0;i<pattern.length;i++){
			for(int a=0;a<pattern[i].length;a++){
				if(pattern[i][a]){
					mapArray[i][a] = blockArray[i][a];
				}
			}
			
		}
		map=mapArray;
		return mapArray;
	}
	
	public int[][] getMap(){
		return map;
	}
	
	public int[][] calcPattern(int blockWidth, int blockHeight, int[] allUsableBlockIDs){
		int[][] returnInt = new int[mapWidth][mapHeight];
		
		//make sure that there is room to place blocks
		if(blockWidth > 1 && blockHeight > 1){
			//make sure there is enough allotted blocks to place down on map
			if(allUsableBlockIDs != null){
			}else{
				System.out.println("Map Generator isn't happy the array allUsableBlockIDs" +
						" comes up null and the method calcPatterns is mad");
			}
		}else{
			System.out.println("MapGenerator isn't happy BlockWidth or blockHeight is not" +
					" greater then 1 so map does not want to genarate - method calcPattern is mad");
		}
		return returnInt;
	}

}
