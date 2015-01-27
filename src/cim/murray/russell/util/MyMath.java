package cim.murray.russell.util;

/**
 * 
 * @author Russell Murray (Killutch)
 *
 */
public class MyMath {
	/**
	 * gets a percentage of a number.
	 * @param selectedNumber
	 * @param percentage
	 * @return
	 */
	public int percentageOf(int selectedNumber, int percentage){
		selectedNumber = (int)((percentage * .01)*selectedNumber);
		return selectedNumber;
	}
	
	/**
	 * if toMatch and toChange arn't both odd or arn't both even then it will add 1 to toChange to make them
	 * match
	 * @param toMatch
	 * @param toChange
	 * @return
	 */
	public int matchOddEven(int toMatch, int toChange){
		if(!shareOddEvenTrait(toMatch, toChange)){
			toChange++;
		}
		
		return toChange;
	}
	
	/**
	 * Finds out if number is even
	 * @param checkValue
	 * @return
	 */
	public boolean isEven(int checkValue){
		boolean ret;
		if(checkValue%2 == 0){
			ret = true;
		}else{
			ret = false;
		}
		return ret;
	}
	
	/**
	 * returns true if (both are odd) or (both are even)
	 * @param numb1
	 * @param numb2
	 * @return
	 */
	public boolean shareOddEvenTrait(int numb1, int numb2){
		return (isEven(numb1) == isEven(numb2));
	}
	
	public int lineMirror(int possition, int lengthOfLine){
		return lengthOfLine - possition;
	}

}
