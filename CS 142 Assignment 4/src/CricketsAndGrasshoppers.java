import java.util.Scanner;

/*
 * Dustin Feldt
 * CS 142 Assignment 4: Crickets and Grasshoppers
 *  * 
 * This program runs a console version of the game Crickets and Grasshoppers,
 * with user-adjusted parameters for the game board.
 */
public class CricketsAndGrasshoppers {

	/*
	 * creates a scanner to read user input from the console,
	 * prompts the user to choose the number of pieces and empty board spaces,
	 * creates an array to represent the game board,
	 * then loops through turns until the game is won
	 */
	public static void main(String[] args) {
		Scanner s = new Scanner(System.in);
						
		String prompt = "Please enter the number of pieces for each player (1-10): ";
		int piecesPerPlayer = promptNumberReadLine(s, prompt, 10);
		
		prompt = "Please enter the number of spaces in the middle (1-9): ";
		int spacesInMiddle = promptNumberReadLine(s, prompt, 9);
		int [] board = createBoard(piecesPerPlayer, spacesInMiddle);
		
		int turn = 1;
		
		//defines the turn loop
		while(true) {
			//prints the game board
			System.out.println(boardToString(board));
			
			// creates and prints the user prompt, then stores the input
			String insect = turnToInsect(turn);
			prompt = insect + ", please enter your move (1-" + (board.length) + "): ";
			int position = promptNumberReadLine(s, prompt, (board.length));
			
			// checks if the user's move is valid and either modifies the board or prints an error
			if(move(board, turn, position) == false) {
				System.out.println("That space does not contain a piece you can move! Please try again.");
				continue;
			}
					
			// changes turns
			if(turn == 1) {
				turn = 2;
			}
			else {
				turn = 1;
			}
			
			// checks to see if the player has any available moves and declares a winner if not
			if(canMove(board, turn) == false) {
				if(turn == 1) {
					System.out.println("Grasshoppers win!");
				}
				else 
				{
					System.out.println("Crickets win!");
				}
				break;
			}	
		}
		
	}
	
	/*
	 * prints the user prompt and scans in their response,
	 * printing an error if it is invalid
	 */
	public static int promptNumberReadLine(Scanner s, String prompt, int max) {
		int input = 0;
		while(true) {
			System.out.print(prompt);
			if(s.hasNextInt()) {
				input = s.nextInt();
				s.nextLine();
				if(input >= 1 && input <= max) {
					break;
				}
				else {
					System.out.println("That was not a valid number! Please try again.");
					continue;
				}
			}
			s.nextLine();
			System.out.println("That was not a valid number! Please try again.");
		}
		return input; 
	}
	
	/*
	 * creates the initial game board based on the user's input
	 * for number of pieces and empty spaces,
	 * with 1 representing Crickets, 2 representing Grasshoppers, and 0 representing empty spaces
	 */
	public static int[] createBoard(int piecesPerPlayer, int spacesInMiddle) {
		int boardSize = piecesPerPlayer * 2 + spacesInMiddle;
		int [] board = new int[boardSize];
		for(int i = 0; i < board.length; i++) {
			if(i < piecesPerPlayer) {
				board[i] = 1;
			}
			else if(i >= piecesPerPlayer && i < board.length - piecesPerPlayer) {
				board[i] = 0;
			}
			else if(i >= board.length - piecesPerPlayer) {
				board[i] = 2;
			}
		}
		return board;
		
	}
	
	//converts the board array to a printable String
	public static String boardToString(int[] board) {
		String result = "";
		for(int i = 0; i < board.length; i++) {
			result = result + playerToPiece(board[i]);
		}
		return result;
	}
	
	//converts each int in the board array to its corresponding game piece
	public static char playerToPiece(int player) {
		if(player == 1) {
			return 'C';
		}
		else if(player == 2) {
			return 'G';
		}
		else {
			return '.';
		}
	}
	
	//gives a name for each player to be used in prompts
	public static String turnToInsect(int player) {
		if(player == 1) {
			return "Crickets";
		}
		else {
			return "Grasshoppers";
		}
	}
	
	//determines if any moves are available to the player
	public static boolean canMove(int[] board, int player) {
		//checks moves for Crickets
		if(player == 1) {
			for(int i = 0; i < board.length - 1; i++) {
				if(board[i] == 1 && board[i+1] == 0) {
					return true;
				}
				if(i <= board.length - 3 && board[i] == 1 && board[i+2] == 0) {
						return true;
				}
			}
			return false;
		}
		//checks moves for Grasshoppers
		else{
			for(int i = board.length - 1; i > 0; i--) {
				if(board[i] == 2 && board[i-1] == 0) {
					return true;
				}
				if(i >= 2 && board[i] == 2 && board[i-2] == 0) {
					return true;
				}
			}
			return false;
		}
	}
	
	// determines if user's move is legal and changes the board if so
	public static boolean move(int[] board, int player, int position) {
		if(position > board.length || position == 0) {
			return false;
		}
		position--;
		if(canMove(board, player) == true && board[position] == player) {
			//checks moves for Crickets
			if(player == 1) {
				if(position <= board.length - 2 && board[position + 1] == 0) {
					board[position] = 0;
					board[position + 1] = 1;
				}
				else if(position <= board.length - 3 &&	board[position + 2] == 0 && board[position + 1] == 2) {
					board[position] = 0;
					board[position + 2] = 1;
				}
				else {
					return false;
				}
			}
			//checks moves for Grasshoppers
			if(player == 2) {
				if(position >= 1 && board[position - 1] == 0) {
					board[position] = 0;
					board[position - 1] = 2;
				}
				else if(position >= 2 && board[position - 2] == 0 && board[position - 1] == 1) {
					board[position] = 0;
					board[position - 2] = 2;
				}
				else {
					return false;
				}
			}
			return true;
		}
		
		else {
			return false;
		}
	}
}	
