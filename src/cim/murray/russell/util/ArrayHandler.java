package cim.murray.russell.util;


/**
 * 
 * @author Russell Murray (Killutch)
 *
 */
public class ArrayHandler {

	public int[][][] resize3DArray(int[][][] oldArray, int slot1, int slot2, int slot3) {
		int[][][] ret = new int[slot1][slot2][slot3];
		ret=fill3DArray(oldArray, ret);
		return ret;
	}
	
	
	public String[] resizeArray(String[] oldArray, int slot1){
		String[] ret = new String[slot1];
		ret=fillArray(oldArray, ret);
		return ret;
	}


	
	//************************fill array*****************************
	private int[][][] fill3DArray(int[][][] oldArray, int[][][] newArray) {
		for(int a=0;a<oldArray.length;a++){
			for(int b=0;b<oldArray[a].length;b++){
				for(int c=0;c<oldArray[a][b].length;c++){
					newArray[a][b][c] = oldArray[a][b][c];
				}
			}
		}
		return newArray;
	}
	
	private int[] fillArray(int[] oldArray, int[] newArray){
		for(int i=0;i<oldArray.length;i++){
			newArray[i]=oldArray[i];
		}
		return newArray;
	}
	
	private String[] fillArray(String[] oldArray, String[] newArray){
		for(int i=0;i<oldArray.length;i++){
			newArray[i]=oldArray[i];
		}
		return newArray;
	}
	
	
	
	//****************************print*************************************
	public void print3DArray(int[][][] array){
		for(int a=0;a<array.length;a++){
			for(int b=0;b<array[a].length;b++){
				for(int c=0;c<array[a][b].length;c++){
					System.out.println("["+a+"]["+b+"]["+c+"] = "+array[a][b][c]);
				}
			}
		}
	}
	
	public void printArray(int[] array){
		for(int i=0;i<array.length;i++){
			System.out.println("["+i+"] = "+array[i]);
		}
	}
	
	public void printArray(String[] array){
		for(int i=0;i<array.length;i++){
			System.out.println("["+i+"] = "+array[i]);
		}
	}
	
	public void printArray(boolean[][] array){
		for(int i=0;i<array.length;i++){
			for(int a=0;a<array[0].length;a++){
				if(array[i][a] == true){
					System.out.print(1);
				}else{
					System.out.print(0);
				}
			}
			System.out.print("\n");
		}
	}
	
	public void printArray(int[][] array){
		for(int i=0;i<array[0].length;i++){
			for(int a=0;a<array.length;a++){
				System.out.print(""+array[a][i]);
			}
			System.out.print("\n");
		}
	}
	
	
	
	
	//*****************************some of my older code*************************
	//**************************even java doced************************************
public int[] deleteMemory;
	
	/**
	 * insert a simple array into a 2d array
	 * @param receivingArray array that will get another array inserted into it
	 * @param insertArray array that will be inserted into receivingArray
	 * @param inLine is what row you would like to insert the array on.
	 * @return
	 */
	public boolean[][] insert1ArrayInto2DArray(boolean[][] receivingArray, boolean[] insertArray, int inLine){
		for(int i=0;i<receivingArray.length;i++){
			receivingArray[i][inLine] = insertArray[i];
		}
		return receivingArray;
	}
	
	public int[] copyArray(int[] array){
		int[] ret =new int[array.length];
		for(int i=0;i<array.length;i++){
			ret[i] = array[i];
		}
		return ret;
	}
	
	

	

	
	/**
	 * add all the elements in an array
	 * @param array
	 * @return
	 */
	public int addAllArrayInts(int[] array){
		int ret=0;
		for(int i=0;i<array.length;i++){
			ret = ret + array[i];
		}
		return ret;
	}
	
	/**
	 * Delete elements out of an array. This means shrinking an array
	 * @param array array to edit
	 * @param arrayToDeleteFrom say you have a 5 and a 3 in this array the program would then
	 * go through the array and any elements = to 5 or 3 will be deleted
	 * @return The edited array.
	 */
	public int[] deleteElementContaining(int array, int[] arrayToDeleteFrom){
		int newArraySize = 0;
		int deleted=0;
		deleteMemory = new int[arrayToDeleteFrom.length];
		int[] tempArray = new int[arrayToDeleteFrom.length];
		for(int i=0;i<arrayToDeleteFrom.length;i++){
			if(array != arrayToDeleteFrom[i]){
				tempArray[newArraySize] = arrayToDeleteFrom[i];
				newArraySize++;
			}else{
				deleteMemory[deleted] = i;
				deleted++;
			}
		}
		deleteMemory = resizeArray(deleteMemory, deleted);
		return resizeArray(tempArray, newArraySize);
	}
	
	public int[] deleteFromElementLocations(int[] array, int location){
		int[] elementLocations = {location};
		return deleteFromElementLocations(array, elementLocations);
	}
	
	/**
	 * delete elements out of an array. this means shrinking an array
	 * @param array array to edit
	 * @param elementLocations an array with numbers that represent the element location
	 * of what needs to be deleted
	 * @return a smaller array without the deleted elements
	 */
	public int[] deleteFromElementLocations(int[] array, int[] elementLocations){
		int newArraySize=0;
		int deleted=0;
		deleteMemory = new int[array.length];
		int[] newArray = new int[array.length];
		for(int i=0;i<array.length;i++){
			if(!arrayMatch(i, elementLocations)){
				newArray[newArraySize] = array[i];
				newArraySize++;
			}else{
				deleteMemory[deleted] = i;
				deleted++;
			}
		}
		deleteMemory = resizeArray(deleteMemory, deleted);
		return resizeArray(newArray, newArraySize);
	}
	
	/**
	 * test if arrays match then return true if they do
	 * @param test
	 * @param array
	 * @return
	 */
	private boolean arrayMatch(int test, int[] array) {
		boolean ret = false;
		for(int i=0;i<array.length;i++){
			if(test == array[i]){
				ret = true;
			}
		}
		return ret;
	}
	
	/**
	 * resize an array. This program was only designed to shrink an array. When shrinking an array
	 * make sure that all your wanted valuse arn't at the end of the array because the end of the
	 * array is what gets deleted.
	 * @param array array to be edited
	 * @param newSize new size of array
	 * @return array that has been resized
	 */
	public int[] resizeArray(int[] array, int newSize){
		int[] ret = new int[newSize];
		for(int i=0;i<newSize;i++){
			ret[i] = array[i];
		}
		return ret;
	}
	
	public int[] getDeleteMemory(){
		return deleteMemory;
	}

}
