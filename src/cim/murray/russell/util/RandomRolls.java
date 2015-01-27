package cim.murray.russell.util;

import java.util.Random;



/**
 * 
 * @author Russell Murray (killutch)
 *
 */
public class RandomRolls {
	public Random rand = new Random();
	public int[] pool;
	public int[] poolOdds;
	
	
	
	
	
	
	
	public int[] rollWinners(int[] players, int[] playersOdds, int numberOfWinners){
		//Declarations
		ArrayHandler ah = new ArrayHandler();
		int[] newPlayers = ah.copyArray(players);
		int[] newPlayersOdds = ah.copyArray(playersOdds);
		int[] ret = new int[numberOfWinners];
		

		
		for(int i=0;i<numberOfWinners;i++){
			int winnerElement = selectAWinner(newPlayers, newPlayersOdds);
			newPlayers = ah.deleteFromElementLocations(newPlayers, winnerElement);
			newPlayersOdds = ah.deleteFromElementLocations(newPlayersOdds, winnerElement);
			ret[i]=winnerElement;
		}
		
		return ret;
	}


	private int selectAWinner(int[] newPlayers, int[] newPlayersOdds) {
		int ret=0;
		int totalOdds = addElements(newPlayersOdds);
		Random rand = new Random();
		int raffleResault = rand.nextInt(totalOdds);
		int accumulation = 0;
		
		for(int i=0;i<newPlayersOdds.length;i++){
			accumulation = accumulation+newPlayersOdds[i];
			if(accumulation>raffleResault){
				ret=i;
				break;
			}
		}
		
		return ret;
	}

	


	private int addElements(int[] newPlayersOdds) {
		int ret =0;
		for(int i=0;i<newPlayersOdds.length;i++){
			ret = ret+newPlayersOdds[i];
		}
		return ret;
	}

	
	

	/**
	 * random number
	 * @param minSize min int value the number can have
	 * @param maxSize max int value the number can have
	 * @return random number.
	 */
	public int randomFromTo(int minSize, int maxSize) {
		int size = maxSize;
		if(minSize<maxSize){
			
			size = (((rand.nextInt(maxSize-minSize))+minSize)+1);
		}else{
			System.out.println("RandomRolls says minSize is not less then maxSize");
		}
		return size;
	}
	
	/**
	 *what ever percentage you inputed is that chance that this will return true.
	 * @param percentageOfTrue chances it will be true
	 * @return result of the roll
	 */
	public boolean percentageRoll(int percentageOfTrue){
		Random rand = new Random();
		if(rand.nextInt(100)+1 <= percentageOfTrue){
			return true;
		}else{
			return false;
		}
	}
	
	/**
	 * This class gives a chance at changing a number by 1
	 * @param numberToChange this is the number that might be changed
	 * @param changeOdds this is the percentage chance that the number will change
	 * @param add true and you add false and you subtract if anything
	 * @param minNumber smallest number you want the method to return
	 * @param maxNumber largest number you want the method to return
	 * @return returns either numberToChange or numberToChange with 1 sub or added
	 */
	public int changeByOne(int numberToChange, int changeOdds, boolean add, int minNumber, int maxNumber){
		if(percentageRoll(changeOdds)){
			if(add && (maxNumber >= numberToChange+1)){
				numberToChange++;
			}else if(!add && (minNumber <= numberToChange-1)){
				numberToChange--;
			}
		}
		return numberToChange;
	}
	
	/**
	 * this takes a percentage of possible change and rolls to see if it will change the
	 * boolean to the opposite value.
	 * @param boolToChange the boolean that might get changed
	 * @param changeOdds what % chance does the boolean have of changing
	 * @return the boolean either the same or changed if the roll told it to.
	 */
	public boolean booleanChange(boolean boolToChange, int changeOdds){
		if(percentageRoll(changeOdds)){
			boolToChange = !boolToChange;
		}
		return boolToChange;
	}
	
	/**
	 * This sets the pool or array that you will pull numbers from in the future. numbers won't be able to be pulled
	 * twice
	 * @param pool a pool of numbers that are to be selected at random in the future
	 * @param odds the odds each number has of being pulled if pool[0] is 10 and poolOdds[0] 10 then
	 * pool[0] has 10 tickets in the raffle to be drawen against all the other numbers
	 */
	public void setRandomPool(int[] pool, int[] poolOdds){
		this.pool = pool;
		this.poolOdds = poolOdds;
	}
	
	/**
	 * grab next number and delete it out of the array
	 * @param numberOfRandoms how many random numbers do you want to pull
	 * @return array of random number selected
	 */
	public int[] getNextRandoms(int numberOfRandoms, RaffleTickets RaffleTicket){
		int[] ret = new int[numberOfRandoms];
		for(int i=0;i<numberOfRandoms;i++){
			ret[i] = raffle(RaffleTicket);
			RaffleTicket = deleteElementFromRaffle(RaffleTicket, ret[i]);
		}
		return ret;
	}
	
	/**
	 * Deletes one of the numbers from the raffle ticket so it cant be returned again. example if the raffle returned the number 5
	 * and you didn't want to pull that number again. Then you could call this method to delete the number 5
	 * @param raff raffleTickets to delete from
	 * @param itemToDelete what number you wanted deleted from the raffle
	 * @return new RaffleTickets that don't have said number contained.
	 */
	public RaffleTickets deleteElementFromRaffle(RaffleTickets raff, int itemToDelete){
		int[] elementes = raff.getPossibleWinners();
		int[] ticketNumbers = raff.getNumberOfTickets();
		ArrayHandler arrayH = new ArrayHandler();
		elementes = arrayH.deleteElementContaining(itemToDelete, elementes);
		ticketNumbers = arrayH.deleteFromElementLocations(ticketNumbers, arrayH.getDeleteMemory());
		return new RaffleTickets(elementes, ticketNumbers);
	}

	
	
	
	/**
	 * chooses a random number number int the contestants array using the numberOfTickets array for its odds. contestants and numberOfTickets
	 * must have the same number of elements in the array for this to work right.
	 * @param contestants one of the elements of this array will be the winner and it will return
	 * there int value.
	 * @param numberOfTickets think of each element in the array as number of tickets in the raffle and a ticket will be
	 * pulled and if the tickets from numberOfTickets[5] are pulled contestants[5] wins and his int is returned.
	 * @return returns the result of the winner
	 */
	public int raffle(int[] contestants, int[] numberOfTickets){
		int ticketCount = new ArrayHandler().addAllArrayInts(numberOfTickets);
		int testTicketCount=0;
		int winningTicket = (new Random().nextInt(ticketCount))+1;
		int ret = 0;
		for(int i=0;i<ticketCount;i++){
			testTicketCount = testTicketCount + numberOfTickets[i];
			if(testTicketCount>=winningTicket){
				ret = contestants[i];
				break;
			}
		}
		return ret;
	}
	
	public int raffle(RaffleTickets raftTickets){
		return raffle(raftTickets.possibleWinners, raftTickets.numberOfTickets);
	}
}
