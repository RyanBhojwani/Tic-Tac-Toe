# Tic-Tac-Toe Game
Learning Java syntax.
This is a simple command-line Tic-Tac-Toe game implemented in Java. The player competes against a computer that makes strategic moves based on the current state of the board. The game ends when either the player or the computer wins, or when the board is full, resulting in a tie.

## How to Play
- The player goes first and plays as "X".
- The computer follows as "O".
- To make a move, the player inputs the row and column numbers (1-3) where they want to place their "X".
- The game board is displayed after each move, showing the updated positions.
- The game continues until there is a winner or all cells are filled, resulting in a tie.

## Game Logic
- **Player's Move**: The player is prompted to enter the row and column for their move.
- **Computer's Move**: The computer attempts to win if possible; if not, it will block the player’s winning moves. It selects the center if available, then a corner, and lastly any open spot.

## Code Structure
- **`initBoard()`**: Initializes the empty board.
- **`gameBoard()`**: Prints the current state of the board.
- **`checkWinner()`**: Checks if there is a winner after each move.
- **`yourTurn()`**: Handles the player's turn.
- **`machineTurn()`**: Contains the logic for the computer's turn.
- **`checkOneAway()`**: Allows the computer to identify if either it or the player is one move away from winning, then blocks or wins accordingly.

## Requirements
- Java 8 or higher

## How to Run
1. Compile the program:
   ```bash
   javac TicTacToe.java
   ```
2. Run the program:
   ```bash
   java TicTacToe
   ```
