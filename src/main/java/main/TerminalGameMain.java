package main;

import java.util.Scanner;

import model.Difficulty;
import model.Gameboard;
import model.Space;

public class TerminalGameMain {

	// Class attributes
	static byte option = 1;
	static boolean loop;
	static Gameboard gb = null;
	static short winCondition = 0;
	static Space[][] spaces = null;
	static boolean gameOver = false;
	static int[] coordinates = new int[2];
	static Scanner scan = new Scanner(System.in);
	static Scanner scanOption = new Scanner(System.in);

	public static void main(String[] args) {

		// Menu loop condition
		loop = true;
		while (loop) {
			// Reseting the attributes
			winCondition = 0;
			gameOver = false;
			
			try {
				System.out.println("\tLET'S PLAY THE MINEFIELD GAME!\t");
				System.out.println("\t If you want to quit, type 0.	\t\n");
				
				// Choosing the game difficult
				System.out.println("\tChose the difficulty: ");
				System.out.println("\t 1. Easy");
				System.out.println("\t 2. Normal");
				System.out.println("\t 3. Hard");
				System.out.println("\t 4. Custom");
				System.out.print("\t ");

				option = scanOption.nextByte();
				System.out.println();

				// Starting the game board;
				switch (option) {
				case 0: {
					if (option == 0) {
						System.out.println("\tGame closed.");
						loop = false;
						break;
					}
				}

				case 1: {
					gb = new Gameboard(Difficulty.EASY);
					
					spaces = gb.getSpaces();
					// Game loop that ends if the player win or loose
					while (!gameOver && !win(gb)) {
						makeAMove(gb);
					}
					continue;
				}

				case 2: {
					gb = new Gameboard(Difficulty.NORMAL);
					
					spaces = gb.getSpaces();
					// Game loop that ends if the player win or loose
					while (!gameOver && !win(gb)) {
						makeAMove(gb);
					}
					continue;
				}

				case 3: {
					gb = new Gameboard(Difficulty.HARD);
					spaces = gb.getSpaces();
					
					// Game loop that ends if the player win or loose
					while (!gameOver && !win(gb)) {
						makeAMove(gb);
					}
					continue;
				}

				case 4: {
					System.out.print("\tEnter the length of the game board,"
							+ "it must be a number greater than 3 and less than or equal to 20: ");

					option = scanOption.nextByte();
					System.out.println("");

					if (option == 0) {
						System.out.println("Game closed.");
						loop = false;
						break;

					} else if (option > 3 && option <= 20) {
						gb = new Gameboard(option);
						System.out.println("");
						
						spaces = gb.getSpaces();
						// Game loop that ends if the player win or loose
						while (!gameOver && !win(gb)) {
							makeAMove(gb);
						}
					}
					continue;
				}

				default: {
					System.out.println("\tYour input is invalid! Try again.");
				}
				}
				
			} catch (RuntimeException e) {
				System.out.println("\tSorry your input is not correct! Try again.");
				scanOption.nextLine();
				continue;
			}
		}

	}

	// Getting and storing the user input
	public static int[] makeAMove(Gameboard gb) {
		System.out.print("\tType the coordinate that you want to verify, like (x,y): ");

		try {
			String strgCoordinates = scan.nextLine();
			if(Character.getNumericValue(strgCoordinates.codePointAt(0)) == 0) {
				gameOver = true;
				System.out.println("Game closed.");
			}

			// Parsing cahr to int
			int coordX = Character.getNumericValue(strgCoordinates.codePointAt(1));
			int coordY = Character.getNumericValue(strgCoordinates.codePointAt(3));

			// Transforming into an array index type
			coordX--;
			coordY--;

			// Some inappropriate character or possibles invalid coordinates will be treat
			// here.
			if ((coordX < 0) || (coordY < 0) || (coordX >= gb.getLength() || coordY >= gb.getLength())) {
				System.out.printf("\tInvalid coordinate!\nThe minimum coordinate is (1,1) and the maximum is (%d,%d).\n",
						gb.getLength(), gb.getLength());
				makeAMove(gb);
			}
			// If the coordinate has bomb, game over!
			if (spaces[coordX][coordY].hasBomb()) {
				gameOver(gb);

			} else {

				if (!spaces[coordX][coordY].isHidden()) {
					System.out.println("\tThis space is not hidden.");
					makeAMove(gb);

				} else {
					coordinates[0] = coordX;
					coordinates[1] = coordY;
					showArea(gb);
					gb.printBoardConsole();
				}
			}

		} catch (RuntimeException e) {
			System.out.println("\tInvalid coordinate!");
			scan.nextLine();
		}

		return coordinates;
	}

	private static void showArea(Gameboard gb) {
		// Showing the selected area around the coordinate (x,y)
		int coordX = coordinates[0];
		int coordY = coordinates[1];
		// (x-1,y-1) (x-1,y) (x-1,y+1)
		// (x,y-1) (x,y) (x, y+1)
		// (x+1,y-1) (x+1,y) (x+1,y+1)

		if ((coordX - 1) >= 0 && (coordY - 1) >= 0) {
			if (!spaces[(coordX - 1)][(coordY - 1)].hasBomb()) {
				if (spaces[(coordX - 1)][(coordY - 1)].isHidden()) {
					spaces[(coordX - 1)][(coordY - 1)].show();
					winCondition++;
				}
			}

		}

		if ((coordX - 1) >= 0) {
			if (!spaces[(coordX - 1)][coordY].hasBomb()) {
				if (spaces[(coordX - 1)][coordY].isHidden()) {
					spaces[(coordX - 1)][coordY].show();
					winCondition++;
				}
			}
		}

		if ((coordX - 1) >= 0 && (coordY + 1) < gb.getLength()) {
			if (!spaces[(coordX - 1)][(coordY + 1)].hasBomb()) {
				if (spaces[(coordX - 1)][(coordY + 1)].isHidden()) {
					spaces[(coordX - 1)][(coordY + 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordY - 1) >= 0) {
			if (!spaces[coordX][(coordY - 1)].hasBomb()) {
				if (spaces[coordX][(coordY - 1)].isHidden()) {
					spaces[coordX][(coordY - 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordY + 1) < gb.getLength()) {
			if (!spaces[coordX][(coordY + 1)].hasBomb()) {
				if (spaces[coordX][(coordY + 1)].isHidden()) {
					spaces[coordX][(coordY + 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength() && (coordY - 1) >= 0) {
			if (!spaces[(coordX + 1)][(coordY - 1)].hasBomb()) {
				if (spaces[(coordX + 1)][(coordY - 1)].isHidden()) {
					spaces[(coordX + 1)][(coordY - 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength() && (coordY - 1) >= 0) {
			if (!spaces[(coordX + 1)][(coordY - 1)].hasBomb()) {
				if (spaces[(coordX + 1)][(coordY - 1)].isHidden()) {
					spaces[(coordX + 1)][(coordY - 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength()) {
			if (!spaces[(coordX + 1)][coordY].hasBomb()) {
				if (spaces[(coordX + 1)][coordY].isHidden()) {
					spaces[(coordX + 1)][coordY].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength() && (coordY + 1) < gb.getLength()) {
			if (!spaces[(coordX + 1)][(coordY + 1)].hasBomb()) {
				if (spaces[(coordX + 1)][(coordY + 1)].isHidden()) {
					spaces[(coordX + 1)][(coordY + 1)].show();
					winCondition++;
				}
			}
		}

		if (spaces[coordX][coordY].isHidden()) {
			spaces[coordX][coordY].show();
			winCondition++;
		}
	}

	// Shows on the board all the bombs in the game.
	public static boolean gameOver(Gameboard gb) {
		System.out.println();
		System.out.println("\tYou lose.");
		System.out.println();

		for (int line = 0; line < gb.getLength(); line++) {
			for (int column = 0; column < gb.getLength(); column++) {
				
				if(column == 0) {
					System.out.print("\t\t|");
				}
				
				if (gb.getSpaces()[line][column].hasBomb()) {
					System.out.print("X ");
					
				} else {
					System.out.print("  ");
				}
				
				if(column == (gb.getLength() - 1)){
					System.out.print("|");
				}
			}
			System.out.println();
		}
		
		System.out.println();
		return gameOver = true;
	}

	// What the user needs to win the game
	public static boolean win(Gameboard gb) {
		int win = (gb.getLength() * gb.getLength()) - gb.getBombs().length;
		if (winCondition == win) {
			System.out.println("\tYou win.");
			System.out.println();
			return true;
		}
		return false;
	}
}