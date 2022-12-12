package main;

import java.util.NoSuchElementException;
import java.util.Scanner;

import model.Difficulty;
import model.Gameboard;
import model.Space;

public class Main {

	static Gameboard gb = null;
	static byte option = 0;
	static short winCondition = 0;
	static Space[][] spaces = null;
	static boolean gameOver = false;
	static int[] coordinates = new int[2];
	static Scanner scan = new Scanner(System.in);
	static Scanner scanOption = new Scanner(System.in);

	public static void main(String[] args) {
		while (option != 2) {
			System.out.println("Let's play the minefield game! Select one option bellow:");
			System.out.print("1. Play \n2. Quit\n");

			try {
				option = scanOption.nextByte();

				switch (option) {
				case 1: {
					System.out.println("Chose the difficulty: ");
					System.out.println("1. Easy");
					System.out.println("2. Normal");
					System.out.println("3. Hard");
					option = scanOption.nextByte();

					if (option == 1) {
						gb = new Gameboard(Difficulty.EASY);

					} else if (option == 2) {
						gb = new Gameboard(Difficulty.NORMAL);

					} else if (option == 3) {
						gb = new Gameboard(Difficulty.HARD);

					} else {
						System.out.println("Invalid input.");
						continue;
					}
					spaces = gb.getSpaces();
					Thread.sleep(2000);
					clearConsole();

					while (!gameOver && !win(gb)) {
						makeAMove(gb);
						clearConsole();
					}
					continue;

				}
				case 2: {
					System.out.println("Closing the game.");
					Thread.sleep(2000);
					break;

				}

				default: {
					System.out.println("Your input is invalid! Try again.");
					Thread.sleep(2000);
					clearConsole();
				}
				}
			} catch (Error e) {
				System.out.println("Sorry your input is not correct! Try again.");
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
		scan.close();

	}

	// Getting and storing the user input
	public static int[] makeAMove(Gameboard gb) {
		System.out.print("Type the coordinate that you want to verify, like (x,y): ");

		try {
			String strgCoordinates = scan.nextLine();

			// Parsing cahr to int
			int coordX = Character.getNumericValue(strgCoordinates.codePointAt(1));
			int coordY = Character.getNumericValue(strgCoordinates.codePointAt(3));

			// Transforming into an array index type
			coordX--;
			coordY--;

			// Some inappropriate character or possibles invalid coordinates will be treat
			// here.
			if ((coordX < 0) || (coordY < 0) || (coordX >= gb.getLength() || coordY >= gb.getLength())) {
				System.out.printf("Invalid coordinate!\nThe minimum coordinate is (1,1) and the maximum is (%d,%d).\n",
						gb.getLength(), gb.getLength());
				makeAMove(gb);
			}
			// If the coordinate has bomb, game over!
			if (spaces[coordX][coordY].hasBomb()) {
				gameOver(gb);

			} else {

				if (!spaces[coordX][coordY].isHidden()) {
					System.out.println("This space is not hidden.");
					makeAMove(gb);

				} else {
					coordinates[0] = coordX;
					coordinates[1] = coordY;
					showArea(gb);
					gb.printBoardConsole();
				}
			}

		} catch (NoSuchElementException e1) {
			e1.getMessage();

		} catch (IllegalStateException e2) {
			e2.getMessage();

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
		System.out.println("You lose.");
		System.out.println();

		for (int line = 0; line < gb.getLength(); line++) {
			for (int column = 0; column < gb.getLength(); column++) {
				if (gb.getSpaces()[line][column].hasBomb()) {
					System.out.print("X ");
				} else {
					System.out.print("  ");
				}
			}
			System.out.println();
			System.out.println();

		}
		return gameOver = true;
	}

	public static boolean win(Gameboard gb) {
		int win = (gb.getLength() * gb.getLength()) - gb.getBombs().length;
		if (winCondition == win) {
			return true;
		}
		return false;
	}

	public final static void clearConsole() {
		try {
			final String os = System.getProperty("os.name");

			if (os.contains("Windows")) {
				Runtime.getRuntime().exec("cls");
			} else {
				Runtime.getRuntime().exec("clear");
			}
		} catch (final Exception e) {
			// Handle any exceptions.
		}
	}
}
