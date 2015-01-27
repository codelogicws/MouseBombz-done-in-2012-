package cim.murray.russell.sprites;

public class SpriteCreationGroup {
	int[] names;
	int[] idNumbers;
	int[] odds;
	
	public SpriteCreationGroup(int[] names, int[] idNumbers, int[] odds){
		this.names = names;
		this.names = idNumbers;
		this.names = odds;
	}
	
	//------------------------Get Methods--------------------------
	public int[] getNames(){
		return names;
	}
	
	public int[] getIdNumbers(){
		return idNumbers;
	}
	
	public int[]odds(){
		return odds;
	}
	
	
	//------------------------Set Methods---------------------------
	public void setNames(int[] names){
		this.names = names;
	}
	
	public void setIdNumbers(int[] idNumbers){
		this.idNumbers = idNumbers;
	}
	
	public void setOdds(int[] odds){
		this.odds = odds;
	}

}
