// This code is for playing XO with another person, RED is X, and BLUE is O.
import java.util.Scanner;

public class XO {
static Scanner s = new Scanner(System.in);
final static String RED_COLOR = "\033[31;1m";
final static String BLUE_COLOR = "\033[36;1m";
final static String RESET_COLOR = "\033[0m";
final static String RED_BACKGROUND_COLOR = "\033[37;41;1m";
final static String BLUE_BACKGROUND_COLOR = "\033[39;46;1m";
final static String PURPLE_BACKGROUND_COLOR = "\033[37;45m";
final static String CLEAR_SCREEN = "\033[H\033[2J";
final static String SET_CURSOR_AT_TOP_LEFT = "\033[H";


	public static void main(String[] arg) {
		System.out.println(CLEAR_SCREEN);

		char[] ArrXO = new char[9];
		for (int turn = 0; turn < 10; turn++) {
			ClearTheScreen();
			PrintTheBoard(ArrXO);
			if (turn % 2 == 0) {
			if (isWinner(ArrXO)) {
					System.out.println(BLUE_BACKGROUND_COLOR + "\n   Player O Won!   \n" + RESET_COLOR);
					break;
				}
			} else {
				if (isWinner(ArrXO)) {
					System.out.println(RED_BACKGROUND_COLOR + "\n   Player X Won!   \n" + RESET_COLOR);
					break;
				}
			}
			if (turn == 9){
				System.out.println(PURPLE_BACKGROUND_COLOR + "\n       Draw       \n" + RESET_COLOR);
				break;
			}
			playersTurn(turn, ArrXO);
		}
	}

	public static void PrintTheBoard(char[] Arr ) {
		for ( int row = 0; row < 3 ; row++){
			System.out.println("     |     |     ");	
			String numbersRow = "";
			for ( int i = row*3 ; i < row*3 + 3 ; i++){
				if (Arr[i] != 0) {
					if ( Arr[i] == 'X' ){
					numbersRow += RED_COLOR + "  " + Arr[i] + "  " + RESET_COLOR;
					}
					else{
					numbersRow += BLUE_COLOR + "  " + Arr[i] + "  " + RESET_COLOR;
					}
				}
				else {
					numbersRow += "  " + (i + 1) + "  ";
				}
				if ( (i+1) % 3 != 0)
				numbersRow += "|";
			}
					
			System.out.println(numbersRow);
			System.out.println("     |     |     ");	
			if ( row != 2)
			System.out.println("------------------");
		}
}

	public static void ClearTheScreen() {
		System.out.print(SET_CURSOR_AT_TOP_LEFT);
	}

	public static void playersTurn(int i, char[] Arr) {
		char Player;
		if (i % 2 == 0){
			Player = 'X';
			System.out.print(RED_COLOR + "\n   " + Player + "'s turn: " );
		}
		else {
			Player = 'O';
			System.out.print(BLUE_COLOR + "\n   " + Player + "'s turn: ");
		}
		int Num = s.nextInt();
		System.out.print(RESET_COLOR);
		while (notInRange(Num) || isRepititive(Num, Arr)) {
			System.out.print("Choose another number: ");
			Num = s.nextInt();
		}
		Arr[Num - 1] = Player;
	}

	public static boolean notInRange(int Num) {
		if (Num < 1 || Num > 9) {
			System.out.println("Your number should be from 1-9");
			return true;
		}

		return false;
	}

	public static boolean isRepititive(int Num, char[] Arr) {
		// [null, 'X', null, null ,'X', 'O', null, 'X']
		if (Arr[Num - 1] != 0) {
			System.out.println("This number has been chosen");
			return true;
		}

		return false;
	}

	public static boolean isWinner ( char [] Arr ){
		// Horizontal Check

		if ( (Arr [0] != 0 && Arr[0] == Arr[1] && Arr[1] == Arr[2]) ||  (Arr [3] != 0 && Arr[3] == Arr[4] && Arr[4] == Arr[5]) ||  (Arr [6] != 0 && Arr[6] == Arr[7] && Arr[7] == Arr[8]) )
		return true;
		// Vertical Check 
		else if ((Arr [0] != 0 && Arr[0] == Arr[3] && Arr[3] == Arr[6]) ||  (Arr [1] != 0 && Arr[1] == Arr[4] && Arr[4] == Arr[7]) ||  (Arr [2] != 0 && Arr[2] == Arr[5] && Arr[5] == Arr[8]) ) 
		return true;
		// Diagonal Check
		else if ( (Arr [0] != 0 && Arr[0] == Arr[4] && Arr[4] == Arr[8]) ||  (Arr [2] != 0 && Arr[2] == Arr[4] && Arr[4] == Arr[6])) 
		return true;

		else
	    return false;
}
}
