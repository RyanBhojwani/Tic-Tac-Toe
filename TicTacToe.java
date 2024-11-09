/* Project 1: Tic-Tac-Toe
 * Author: Ryan Bhojwani
 * File: TicTacToe.java
 * Date: 6/23/2024
 */

package tictactoe;

import java.util.Scanner;

public class TicTacToe {
	char[][] board; // 2D array for tic-tac-toe board
	Scanner scanner;
	
	// Main method that plays the game
	public static void main(String[] args) {
		System.out.println("Let's Play Tic-Tac-Toe!");
		TicTacToe game = new TicTacToe();
		game.initBoard(); // Initializes board
		
		int turnCount = 0; // Counter to keep track of number of turns
		boolean gameWon = false; // Flag to indicate if the game has been won
		
		// Game loop continues until the game is won or board is full
		while (!gameWon && turnCount < 9) {
			game.gameBoard(); // Displays the board
			if (turnCount % 2 == 0) {
				game.yourTurn(); // Player's turn "X"
			} else {
				game.machineTurn(); // Computer's turn "O"
			}
			gameWon = game.checkWinner(); // Check for a winner
			turnCount++; // Increment the turn count
		}
		game.gameBoard(); // Print the final board
		
		// Determine and print the result of the game
		if (gameWon) {
			if (turnCount % 2 == 1) {
				System.out.println("You win!"); // Player wins when game ends on odd turns
			} else {
				System.out.println("You lose!"); // Computer wins when game ends on even turns
			}
		} else {
			System.out.println("It's a tie!");
		}
		
		
	}
	
	// Method to initialize the empty tic-tac-toe board
	public void initBoard() {
		board = new char[3][3];
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				board[i][j] = ' ';
			}
		}	
	}
	
	// Method to display the current game board throughout the game
	public void gameBoard() {
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				System.out.print(board[i][j]); // Print the value of each cell
				if (j < 2) {
					System.out.print(" | "); // Separator between columns
				}	
			}
			System.out.println();
			if (i < 2) {
				System.out.println("------------"); // Separator between rows
			}
		}
	}
	
	// Method to check if there is a winner with the current board
	public boolean checkWinner() {
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != ' ') {
				return true; // Return true if all 3 cells in any row are the same character and not a space
			}
		}
		
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[1][j] == board[2][j] && board[0][j] != ' ') {
				return true; // Return true if all 3 cells in any column are the same character and not a space
			}
		}
		
		if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != ' ') || (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != ' ')) {
			return true; // Return true if all 3 cells in any diagonal are the same character and not a space
		}
		
		return false; // Return false if there is no winner
	}

	// Method allowing the player to take a turn
	public void yourTurn() {
		scanner = new Scanner(System.in); // Initialize scanner for user input
		int row;
		int col;
		do {
			System.out.println("Your turn!");
			System.out.println("Please enter the row you would like to make your move in (1-3):");
			row = scanner.nextInt() - 1; // Read input and adjust the row for 0 index
			System.out.println("Please enter the column you would like to make your move in (1-3):");
			col = scanner.nextInt() - 1; // Read input and adjust the column for 0 index
		} while (!isValidMove(row, col)); // Repeat until a valid move is made
		
		board[row][col] = 'X'; // Place an X in the chosen cell
	}
	
	// Method to determine if the player made a valid move
	private boolean isValidMove(int row, int col) {
		if (row < 0 || row >= 3 || col < 0 || col >=3) {
			System.out.println("Invalid move. The row and column must be between 1 and 3."); // Check for input out of bounds
			return false;
		}
		if (board[row][col] != ' ') {
			System.out.println("Invalid move. That spot has already been taken."); // Check for cell already taken
			return false;
		}
		return true; // Return true if the move is valid
	}
	
	// Method determining the computer's move
	public void machineTurn() {
		System.out.println("Computer's Turn:");
		
		// Check if the computer can block or win the game
		if (checkOneAway()) {
			return;
		}
		
		// Take the middle cell if available
		if (board[1][1] == ' ' ) {
			board[1][1] = 'O';
			return; 
		}
		
		// Take a corner cell if available
		for (int i = 0; i < 3; i += 2) {
			for (int j = 0; j < 3; j += 2) {
				if (board[i][j] == ' ') {
					board[i][j] = 'O';
					return;
				}
			}
		}
		
		// Take the next available cell
		for (int i = 0; i < 3; i++) {
			for (int j = 0; j < 3; j++) {
				if (board[i][j] == ' ') {
					board[i][j] = 'O';
					return;
				}
			}
		}
	}
	
	// Method that checks if the player or computer is one away from winning and blocks or wins the game
	private boolean checkOneAway() {
		
		// Check rows for potential win/block
		for (int i = 0; i < 3; i++) {
			if (board[i][0] == board[i][1] && board[i][0] != ' ' && board[i][2] == ' ') {
				board[i][2] = 'O';
				return true;
			}
			if (board[i][1] == board[i][2] && board[i][1] != ' ' && board[i][0] == ' ') {
				board[i][0] = 'O';
				return true;
			}
			if (board[i][0] == board[i][2] && board[i][0] != ' ' && board[i][1] == ' ') {
				board[i][1] = 'O';
				return true;
			}
		}
		
		// Check columns for potential win/block
		for (int j = 0; j < 3; j++) {
			if (board[0][j] == board[1][j] && board[0][j] != ' ' && board[2][j] == ' ') {
				board[2][j] = 'O';
				return true;
			}
			if (board[1][j] == board[2][j] && board[1][j] != ' ' && board[0][j] == ' ') {
				board[0][j] = 'O';
				return true;
			}
			if (board[0][j] == board[2][j] && board[0][j] != ' ' && board[1][j] == ' ') {
				board[1][j] = 'O';
				return true;
			}
		}
		
		// Check top left to bottom right diagonal for potential win/block
		if ((board[0][0] == board[1][1] && board[0][0] != ' ') || (board[2][2] == board[1][1] && board[1][1] != ' ') || (board[0][0] == board[2][2] && board[0][0] != ' ')) {
			if (board[0][0] == ' ') {
				board[0][0] = 'O';
			} else if (board[1][1] == ' ') {
				board[1][1] = 'O';
			} else if (board[2][2] == ' '){
				board[2][2] = 'O';
			} else {
				return false;
			}
			return true;
		}
		
		// Check bottom left to top right diagonal for potential win/block
		if ((board[0][2] == board[1][1] && board[1][1] != ' ') || (board[2][0] == board[1][1] && board[1][1] != ' ') || (board[2][0] == board[0][2] && board[2][0] != ' ')) {
			if (board[0][2] == ' ') {
				board[0][2] = 'O';
			} else if (board[1][1] == ' ') {
				board[1][1] = 'O';
			} else if (board[2][0] == ' ') {
				board[2][0] = 'O';
			} else {
				return false;
			}
			return true;
		}
		
		return false;
	}
	
}
