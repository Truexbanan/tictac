import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;
import java.util.List;



public class AI {

	static ArrayList<Integer> playerPositions = new  ArrayList<Integer>();
	static ArrayList<Integer> cpuPositions = new  ArrayList<Integer>();


	public static void main(String[] args) {

		//chooses a difficulty for the user
		int dif=0;
		while(dif!=1 || dif!=2 || dif!=3 || dif!=4) {
			Scanner scan1 = new Scanner(System.in);
			System.out.println("Choose your difficulty 1-easy , 2-medium or 3-hard or 4 impossible");
			dif = scan1.nextInt();
			if (dif==1 || dif==2 || dif==3 || dif==4)
				break;
			if (dif!=1 || dif!=2 || dif!=3 || dif!=4)
				System.out.println("type 1 2 3 or 4 please");
		}
		char [][] board = { {' ', '|',' ','|',' '},
				{'-', '-','-','-','-'},
				{' ', '|',' ','|',' '},
				{'-', '-','-','-','-'},
				{' ', '|',' ','|',' '} 
		};

		printBoard(board);

		while(true) {
			Scanner scan = new Scanner(System.in);
			System.out.println("Choose your spot 1-9");
			int playerPos = scan.nextInt();

			while(playerPositions.contains(playerPos) || cpuPositions.contains(playerPos)) {
				System.out.println("That place is already taken pick a new spot!");
				playerPos = scan.nextInt();
			}
			placement(board,playerPos,"player");
			if (playerPos ==64) {
				printBoard(board);
				System.out.println("WINNER WINNER WINNER LETS GOOOOOOOOO!!!!!!!!");
				break;
			}
			String result = checkWinner();
			
			if (result.length()>0) {
				printBoard(board);
				System.out.println(result);
				break;
			}

			Random rand = new Random();
			int cpuPos = rand.nextInt(9) + 1;

			// if the CPU can win then it will if it can block and not win then it will block

			cpuPos = aI(cpuPos,dif);
			// if it can not win or block it chooses a random spot
			while(playerPositions.contains(cpuPos) || cpuPositions.contains(cpuPos)) {
				cpuPos = rand.nextInt(9) + 1; ;
			}

			placement(board,cpuPos,"cpu");
			printBoard(board);
			result = checkWinner();

			if (result.length()>0) {
				System.out.println(result);
				break;
			}

		}
	}

	
	public static void printBoard(char [][] board) {

		for (char [] row : board ) {
			for( char c: row) {
				System.out.print(c);	}
			System.out.println();}
	}
	
	public static void placement(char[][] board, int pos, String user) {

		char symbol =' ';
		if (user.equals("player")) {
			symbol = 'X';
			playerPositions.add(pos);}
		else if(user.equals("cpu")) {
			symbol = 'O';
			cpuPositions.add(pos);
		}

		switch(pos) {
		case 1:
			board[0][0] = symbol;
			break;
		case 2:
			board[0][2] = symbol;
			break;
		case 3:
			board[0][4] = symbol;
			break;
		case 4:
			board[2][0] = symbol;
			break;
		case 5:
			board[2][2] = symbol;
			break;
		case 6:
			board[2][4] = symbol;
			break;
		case 7:
			board[4][0] = symbol;
			break;
		case 8:
			board[4][2] = symbol;
			break;
		case 9:
			board[4][4] = symbol;
			break;
		case 64:
			board[4][4] = symbol;
			board[4][2] = symbol;
			board[4][0] = symbol;
			board[2][4] = symbol;
			board[2][2] = symbol;
			board[2][0] = symbol;
			board[0][4] = symbol;
			board[0][2] = symbol;
			board[0][0] = symbol;
			System.out.println("WINNER WINNER WINNER LETS GOOOOOOOOO!!!!!!!!");
			break;
		default:
			System.out.println("please choose between 1-9 you missed your turn");
			break;
		}
	}


	public static int aI(int cpuPos, int dif) {
		
		
		
		if (dif==1) {
			return cpuPos;
		}
		if (dif==2) {
			cpuPos= blocking(cpuPos);
		}
		if (dif==3) {
			cpuPos= blocking(cpuPos);
			cpuPos= attacking(cpuPos);
		}
		if (dif==4) {
			
			
			if( playerPositions.size()+ cpuPositions.size() == 1 && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) 
				return 5; 
			if ( playerPositions.size()+ cpuPositions.size() == 3 && ( playerPositions.contains(2) || playerPositions.contains(4) || playerPositions.contains(6) || playerPositions.contains(8) )   ) { 
				if ( playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(2) && playerPositions.contains(6))
					return 3;
				if ( playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(2) && playerPositions.contains(4))
					return 1;
				if (playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(8) && playerPositions.contains(6)) 
					return 9;
				if (playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(8) && playerPositions.contains(4)) 
					return 7;
			}
			
		
				if ( playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(7) && playerPositions.contains(3))
					return 2;
				if ( playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(1) && playerPositions.contains(9))
					return 2;
				

			if( playerPositions.size()+ cpuPositions.size() == 1 && playerPositions.contains(5)) 
				return 9; 	
			if( playerPositions.size()+ cpuPositions.size() == 3 && playerPositions.contains(5) && playerPositions.contains(1)) 
				return 7; 	
		

			cpuPos= blocking(cpuPos);
			cpuPos= attacking(cpuPos);

		}

		return cpuPos;
	}

	public static int attacking(int cpuPos) {

		//blocking
		//connecting
		if(cpuPositions.contains(1) && cpuPositions.contains(2) && playerPositions.contains(3) == false && cpuPositions.contains(3) == false) {
			return 3;
		}
		if(cpuPositions.contains(1) && cpuPositions.contains(4) && playerPositions.contains(7) == false && cpuPositions.contains(7) == false) {
			return 7;
		}
		if(cpuPositions.contains(1) && cpuPositions.contains(5) && playerPositions.contains(9) == false && cpuPositions.contains(9) == false) {
			return 9;
		}
		if(cpuPositions.contains(2) && cpuPositions.contains(3) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		if(cpuPositions.contains(2) && cpuPositions.contains(5) && playerPositions.contains(8) == false && cpuPositions.contains(8) == false) {
			return 8;
		}
		if(cpuPositions.contains(3) && cpuPositions.contains(6) && playerPositions.contains(9) == false && cpuPositions.contains(9) == false) {
			return 9;
		}
		if(cpuPositions.contains(3) && cpuPositions.contains(5) && playerPositions.contains(7) == false && cpuPositions.contains(7) == false) {
			return 7;
		}
		if(cpuPositions.contains(4) && cpuPositions.contains(5) && playerPositions.contains(6) == false && cpuPositions.contains(6) == false) {
			return 6;
		}
		if(cpuPositions.contains(4) && cpuPositions.contains(7) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		if(cpuPositions.contains(5) && cpuPositions.contains(6) && playerPositions.contains(4) == false && cpuPositions.contains(4) == false) {
			return 4;
		}
		if(cpuPositions.contains(5) && cpuPositions.contains(7) && playerPositions.contains(3) == false && cpuPositions.contains(3) == false) {
			return 3;
		}
		if(cpuPositions.contains(5) && cpuPositions.contains(8) && playerPositions.contains(2) == false && cpuPositions.contains(2) == false) {
			return 2;
		}
		if(cpuPositions.contains(5) && cpuPositions.contains(9) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		if(cpuPositions.contains(6) && cpuPositions.contains(9) && playerPositions.contains(3) == false && cpuPositions.contains(3) == false) {
			return 3;
		}
		if(cpuPositions.contains(7) && cpuPositions.contains(8) && playerPositions.contains(9) == false && cpuPositions.contains(9) == false) {
			return 9;
		}
		if(cpuPositions.contains(8) && cpuPositions.contains(9) && playerPositions.contains(7) == false && cpuPositions.contains(7) == false) {
			return 7;
		}
		if(cpuPositions.contains(5) && cpuPositions.contains(9) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		//crossing
		if(cpuPositions.contains(1) && cpuPositions.contains(3) && playerPositions.contains(2) == false && cpuPositions.contains(2) == false) {
			return 2;
		}
		if(cpuPositions.contains(1) && cpuPositions.contains(7) && cpuPositions.contains(4) == false && cpuPositions.contains(4) == false) {
			return 4;
		}
		if(cpuPositions.contains(1) && cpuPositions.contains(9) && cpuPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}
		if(cpuPositions.contains(3) && cpuPositions.contains(9) && playerPositions.contains(6) == false && cpuPositions.contains(6) == false) {
			return 6;
		}
		if(cpuPositions.contains(3) && cpuPositions.contains(7) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}
		if(cpuPositions.contains(7) && cpuPositions.contains(9) && playerPositions.contains(8) == false && cpuPositions.contains(8) == false) {
			return 8;
		}
		//up down and side ways
		if(cpuPositions.contains(2) && cpuPositions.contains(8) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}
		if(cpuPositions.contains(4) && cpuPositions.contains(6) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}






		return cpuPos;
	}

	public static int blocking(int cpuPos) {


		//blocking
		//connecting
		if(playerPositions.contains(1) && playerPositions.contains(2) && playerPositions.contains(3) == false && cpuPositions.contains(3) == false) {
			return 3;
		}
		if(playerPositions.contains(1) && playerPositions.contains(4) && playerPositions.contains(7) == false && cpuPositions.contains(7) == false) {
			return 7;
		}
		if(playerPositions.contains(1) && playerPositions.contains(5) && playerPositions.contains(9) == false && cpuPositions.contains(9) == false) {
			return 9;
		}
		if(playerPositions.contains(2) && playerPositions.contains(3) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		if(playerPositions.contains(2) && playerPositions.contains(5) && playerPositions.contains(8) == false && cpuPositions.contains(8) == false) {
			return 8;
		}
		if(playerPositions.contains(3) && playerPositions.contains(6) && playerPositions.contains(9) == false && cpuPositions.contains(9) == false) {
			return 9;
		}
		if(playerPositions.contains(3) && playerPositions.contains(5) && playerPositions.contains(7) == false && cpuPositions.contains(7) == false) {
			return 7;
		}
		if(playerPositions.contains(4) && playerPositions.contains(5) && playerPositions.contains(6) == false && cpuPositions.contains(6) == false) {
			return 6;
		}
		if(playerPositions.contains(4) && playerPositions.contains(7) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		if(playerPositions.contains(5) && playerPositions.contains(6) && playerPositions.contains(4) == false && cpuPositions.contains(4) == false) {
			return 4;
		}
		if(playerPositions.contains(5) && playerPositions.contains(7) && playerPositions.contains(3) == false && cpuPositions.contains(3) == false) {
			return 3;
		}
		if(playerPositions.contains(5) && playerPositions.contains(8) && playerPositions.contains(2) == false && cpuPositions.contains(2) == false) {
			return 2;
		}
		if(playerPositions.contains(5) && playerPositions.contains(9) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		if(playerPositions.contains(6) && playerPositions.contains(9) && playerPositions.contains(3) == false && cpuPositions.contains(3) == false) {
			return 3;
		}
		if(playerPositions.contains(7) && playerPositions.contains(8) && playerPositions.contains(9) == false && cpuPositions.contains(9) == false) {
			return 9;
		}
		if(playerPositions.contains(8) && playerPositions.contains(9) && playerPositions.contains(7) == false && cpuPositions.contains(7) == false) {
			return 7;
		}
		if(playerPositions.contains(5) && playerPositions.contains(9) && playerPositions.contains(1) == false && cpuPositions.contains(1) == false) {
			return 1;
		}
		//crossing
		if(playerPositions.contains(1) && playerPositions.contains(3) && playerPositions.contains(2) == false && cpuPositions.contains(2) == false) {
			return 2;
		}
		if(playerPositions.contains(1) && playerPositions.contains(7) && playerPositions.contains(4) == false && cpuPositions.contains(4) == false) {
			return 4;
		}
		if(playerPositions.contains(1) && playerPositions.contains(9) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}
		if(playerPositions.contains(3) && playerPositions.contains(9) && playerPositions.contains(6) == false && cpuPositions.contains(6) == false) {
			return 6;
		}
		if(playerPositions.contains(3) && playerPositions.contains(7) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}
		if(playerPositions.contains(7) && playerPositions.contains(9) && playerPositions.contains(8) == false && cpuPositions.contains(8) == false) {
			return 8;
		}
		//up down and side ways
		if(playerPositions.contains(2) && playerPositions.contains(8) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}
		if(playerPositions.contains(4) && playerPositions.contains(6) && playerPositions.contains(5) == false && cpuPositions.contains(5) == false) {
			return 5;
		}






		return cpuPos;
	}

	public static String checkWinner() {

		List topRow = Arrays.asList(1,2,3);
		List midRow = Arrays.asList(4,5,6);
		List botRow = Arrays.asList(7,8,9);

		List leftCol = Arrays.asList(1,4,7);
		List midCol = Arrays.asList(2,5,8);
		List rightCol = Arrays.asList(3,6,9);

		List cross1 = Arrays.asList(1,5,9);
		List cross2 = Arrays.asList(7,5,3);

		List<List> winning = new ArrayList<List>();
		winning.add(topRow);
		winning.add(midRow);
		winning.add(botRow);
		winning.add(leftCol);
		winning.add(midCol);
		winning.add(rightCol);
		winning.add(cross1);
		winning.add(cross2);

		for(List l: winning) {
			if(playerPositions.containsAll(l)) {
				return "You Won! Good Job! :)";
			}
			else if(cpuPositions.containsAll(l)) {
				return "You Lost! unlucky! Try again? :(";
			}
			else if (playerPositions.size() + cpuPositions.size() == 9) {
				return "TIE";
			}
		}
		return "";
	}
}
