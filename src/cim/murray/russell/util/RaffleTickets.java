package cim.murray.russell.util;


/**
 * 
 * @author Russell Murray (Killutch)
 *
 */
public class RaffleTickets {
	public int[] possibleWinners;
	public int[] numberOfTickets;
	
	public RaffleTickets(int[] possibleWinners, int[] numberOfTickets){
		this.possibleWinners = possibleWinners;
		this.numberOfTickets = numberOfTickets;
	}
	
	public void setPossibleWinners(int[] possibleWinners){
		this.possibleWinners = possibleWinners;
	}
	
	public void setNumberOfTickets(int[] numberOfTickets){
		this.numberOfTickets = numberOfTickets;
	}
	
	public int[] getNumberOfTickets(){
		return numberOfTickets;
	}
	
	public int[] getPossibleWinners(){
		return possibleWinners;
	}
	
	public RaffleTickets createRaffleTickets(int[] possibleWinners, int[] numberOfTickets){
		return new RaffleTickets(possibleWinners, numberOfTickets);
	}

}
