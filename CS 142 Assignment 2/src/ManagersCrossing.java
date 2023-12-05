/*
 * Dustin Feldt
 * CS 142 Assignment 2
 * 10/28/2022
 * 
 * This program tests the legality of any given move in the river crossing puzzle according to the following rules:
 * 
 * 1) All position values must be either 1 or 2
 * 2) The boat must change position
 * 3) All characters must move with the boat
 * 4) Only one or two characters may move at one time
 * 5) No manager from one company can be on the shore with an engineer from another company without
 *		that engineer's manager present
 */
public class ManagersCrossing {
	

	public static void main(String[] args) {
		System.out.println(isMoveOkay(1, 1, 1, 1, 1, 1, 1,		//before positions
									  1, 1, 1, 1, 1, 1, 1));    //after positions

	}
	
	//Returns true if no rules are violated and dispenses congratulations if the puzzle is solved
	//Returns false and prints a corresponding error message if a rule is violated
	public static boolean isMoveOkay(int boatBefore, int managerABefore, int managerBBefore, int 
			managerCBefore, int engineerABefore, int engineerBBefore, int engineerCBefore, int boatAfter, int 
			managerAAfter, int managerBAfter, int managerCAfter, int engineerAAfter, int engineerBAfter, int 
			engineerCAfter) {
		
		//tests to see if position values are within the bounds of the puzzle
		if((boatBefore != 1 && boatBefore != 2) || (boatAfter != 1 && boatAfter != 2)
			|| (managerABefore != 1 && managerABefore != 2) || (managerAAfter != 1 && managerAAfter != 2)
			|| (managerBBefore != 1 && managerBBefore != 2) || (managerBAfter != 1 && managerBAfter != 2)
			|| (managerCBefore != 1 && managerCBefore != 2) || (managerCAfter != 1 && managerCAfter != 2)
			|| (engineerABefore != 1 && engineerABefore != 2) || (engineerAAfter != 1 && engineerAAfter != 2)
			|| (engineerBBefore != 1 && engineerBBefore != 2) || (engineerBAfter != 1 && engineerBAfter != 2)
			|| (engineerCBefore != 1 && engineerCBefore != 2) || (engineerCAfter != 1 && engineerCAfter != 2)) {
			
				System.out.println("All positions must be 1 or 2!");
				return false;
		}
		
		//tests to make sure the boat is moving
		if(boatBefore == boatAfter) {
			System.out.println("The boat must move!");
			return false;
		}
		
		//tracks the number of people moving
		int numMoving = 0;
		
		//checks if Manager A is changing sides
		if(managerABefore != managerAAfter) {
			numMoving++;
			//checks if Manager A started on an illegal side
			if(managerABefore != boatBefore) {
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
		}
		
		//checks if Manager B is changing sides
		if(managerBBefore != managerBAfter) {
			numMoving++;
			//checks if Manager B started on an illegal side
			if(managerBBefore != boatBefore) {
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
		}
		
		//checks if Manager C is changing sides
		if(managerCBefore != managerCAfter) {
			numMoving++;
			//checks if Manager C started on an illegal side
			if(managerCBefore != boatBefore) {
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
		}
	
		//checks if Engineer A is changing sides
		if(engineerABefore != engineerAAfter) {
			numMoving++;
			//checks if Engineer A started on an illegal side
			if(engineerABefore != boatBefore) {
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
		}
		
		//checks if Engineer B is changing sides
		if(engineerBBefore != engineerBAfter) {
			numMoving++;
			//checks if Engineer B started on an illegal side
			if(engineerBBefore != boatBefore) {
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
		}

		//checks if Engineer C is changing sides
		if(engineerCBefore != engineerCAfter) {
			numMoving++;
			//checks if Engineer C started on an illegal side
			if(engineerCBefore != boatBefore) {
				System.out.println("You may not move a person who is not with the boat!");
				return false;
			}
		}
		
		//checks number of people moving and returns false if it is more than 2 or less than 1
		if(numMoving != 1 && numMoving != 2) {
			System.out.println("You must move one or two people!");
			return false;
		}
				
		//Checks whether Manager A would have the opportunity to recruit another engineer
		if(	((managerABefore == engineerBBefore) && (managerABefore != managerBBefore))
			|| ((managerAAfter == engineerBAfter) && (managerAAfter != managerBAfter))
			|| ((managerABefore == engineerCBefore) && (managerABefore != managerCBefore))
			|| ((managerAAfter == engineerCAfter) && (managerAAfter != managerCAfter))	) {
				System.out.println("Manager A would try to recruit someone!");
				return false;
		}
		
		//Checks whether Manager B would have the opportunity to recruit another engineer
		if( ((managerBBefore == engineerABefore) && (managerBBefore != managerABefore))
			|| ((managerBAfter == engineerAAfter) && (managerBAfter != managerAAfter))
			|| ((managerBBefore == engineerCBefore) && (managerBBefore != managerCBefore))
			|| ((managerBAfter == engineerCAfter) && (managerBAfter != managerCAfter))  ) {
				System.out.println("Manager B would try to recruit someone!");
				return false;
		}
		
		//Checks whether Manager C would have the opportunity to recruit another engineer
		if( ((managerCBefore == engineerABefore) && (managerCBefore != managerABefore))
			|| ((managerCAfter == engineerAAfter) && (managerCAfter != managerAAfter))
			|| ((managerCBefore == engineerBBefore) && (managerCBefore != managerBBefore))
			|| ((managerCAfter == engineerBAfter) && (managerCAfter != managerBAfter))  ) {
				System.out.println("Manager C would try to recruit someone!");
				return false;
		}
		
		//Checks whether the final position solves the puzzle
		if(boatAfter == 2 && managerAAfter == 2 && managerBAfter == 2 && managerCAfter == 2
			&& engineerAAfter == 2 && engineerBAfter == 2 && engineerCAfter == 2) {
				System.out.println("You solved the puzzle!");
		}
				
		return true;
	}

}
