package model;

import java.util.Random;

public class Gameboard {

	// This array multidimensional represents the spaces on the gameboard.
	// Space is a separeted class with attributes and methods.
	private Space[][] spaces;

	// Quantity of spaces N in our board NxN
	private int length;

	// This array will contain the coordinates of the bombs
	private Bomb[] bombs = new Bomb[10];

	public Gameboard(Difficulty dificulty) {
		if (Difficulty.EASY == dificulty) {
			spaces = new Space[8][8];
			length = 8;
		} else if (Difficulty.NORMAL == dificulty) {
			spaces = new Space[9][9];
			length = 9;
		} else if (Difficulty.HARD == dificulty) {
			spaces = new Space[10][10];
			length = 10;
		}

		// Initializing the spaces in the board
		for (int i = 0; i < length; i++) {
			for (int j = 0; j < length; j++) {
				spaces[i][j] = new Space();
				;
			}
		}

		generateBomb();
		printBoardConsole();
	}

	public Bomb[] getBombs() {
		return bombs;
	}

	public Space[][] getSpaces() {
		return spaces;
	}

	public int getLength() {
		return length;
	}

	public void generateBomb() {
		Random random = new Random();

		for (int i = 0; i < bombs.length; i++) {
			bombs[i] = new Bomb((random.nextInt(0, length)), (random.nextInt(0, length)));
		}

		// Checking if we have two or more bombs in the same position (x,y), if yes, we
		// change the coordinates
		for (int line = 1; line < bombs.length; line++) {
			for (int column = 0; column < line; column++) {
				if ((bombs[line].getCoordinateX() == bombs[column].getCoordinateX())
						&& (bombs[line].getCoordinateY() == bombs[column].getCoordinateY())) {
					bombs[column].setCoordinateX(random.nextInt(0, length));
					bombs[column].setCoordinateY(random.nextInt(0, length));
					line = -1;
				}
			}
		}

		// Planting the bombs
		for (int i = 0; i < bombs.length; i++) {
			int x = bombs[i].getCoordinateX();
			int y = bombs[i].getCoordinateY();
			spaces[x][y].addBomb();
		}

		settingBombsNear();
	}

	private void settingBombsNear() {
		for (int line = 0; line < length; line++) {
			for (int column = 0; column < length; column++) {
				if (spaces[line][column].hasBomb()) {
					// (x-1,y-1) (x-1,y) (x-1,y+1)
					// (x,y-1)   (x,y)   (x, y+1)
					// (x+1,y-1) (x+1,y) (x+1,y+1)

					if ((line - 1) >= 0 && (column - 1) >= 0) {
						spaces[(line - 1)][(column - 1)].addBombsNear();
					}

					if ((line - 1) >= 0) {
						spaces[(line - 1)][column].addBombsNear();
					}

					if ((line - 1) >= 0 && (column + 1) < length) {
						spaces[(line - 1)][column + 1].addBombsNear();
					}

					if ((column - 1) >= 0) {
						spaces[line][(column - 1)].addBombsNear();
					}

					if ((column + 1) < length) {
						spaces[line][(column + 1)].addBombsNear();
					}

					if ((line + 1) < length && (column - 1) >= 0) {
						spaces[(line + 1)][(column - 1)].addBombsNear();
					}

					if ((line + 1) < length) {
						spaces[(line + 1)][column].addBombsNear();
					}

					if ((line + 1) < length && (column + 1) < length) {
						spaces[(line + 1)][column + 1].addBombsNear();
					}

				}
			}
		}
	}

	public void printBoardConsole() {
		for (int line = 0; line < length; line++) {
			for (int column = 0; column < length; column++) {
				if (spaces[line][column].hasBomb()) {
					// filled square -> \u25A0
					System.out.print("\u25A0 ");
				} else if (spaces[line][column].isHidden()) {
					System.out.print("\u25A0 ");

				} else if(!spaces[line][column].isHidden()) {
					if (spaces[line][column].getBombsNear() != 0) {
						System.out.print(spaces[line][column].getBombsNear() + " ");
					} else {
					System.out.print("  ");
					}
				}
			}
			System.out.println();
		}
	}

	// Switch the property hidden if the space is hidden
	public void showSpace(int x, int y) {
		if (spaces[x][y].isHidden() == true) {
			spaces[x][y].show();
		}
	}
}