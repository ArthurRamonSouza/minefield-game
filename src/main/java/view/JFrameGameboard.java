package view;

import java.awt.EventQueue;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.TerminalGameMain;
import model.Difficulty;
import model.Gameboard;

// Inheritance this class extends JFrame
// And this class implements, by hInheritance (JComponent), the Serializable interface
public class JFrameGameboard extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private byte buttonSize = 50;
	private static short winCondition = 0;

	private static byte option = 0;
	private static Gameboard gb = null;
	private static JButtonSpace[][] spaces;
	// Storing the selected button coordinates
	private static short coordinates[] = new short[2];
	private static Scanner scanOption = new Scanner(System.in);

	/**
	 * Launch the application. later we will delete the main method and just call
	 * the class constructor in our Main class
	 */
	public static void main(String[] args) {

		while (option != 3) {
			TerminalGameMain.clearConsole();
			System.out.println("Do you want play in the terminal or with a GUI?");
			System.out.println("1. Terminal\n2. GUI\n3. Quit");
			try {
				option = scanOption.nextByte();

				switch (option) {
				case 1: {
					TerminalGameMain.main(args);
					break;
				}

				case 2: {

					// Choosing the game difficulty
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

					// Initializing the spaces array
					spaces = new JButtonSpace[gb.getLength()][gb.getLength()];

					// And the array objects
					for (int i = 0; i < gb.getLength(); i++) {
						for (int j = 0; j < gb.getLength(); j++) {
							spaces[i][j] = new JButtonSpace(coordinates, gb);
							spaces[i][j].setSpace(gb.getSpaces()[i][j]);
						}
					}

					EventQueue.invokeLater(new Runnable() {
						public void run() {
							try {
								JFrameGameboard frame = new JFrameGameboard();
								frame.setVisible(true);
							} catch (Exception e) {
								e.printStackTrace();
							}
						}
					});
				}

				case 3: {
					System.out.println("Closing the game.");
					break;
				}

				default: {
					System.out.println("Your input is invalid! Try again.");
					scanOption.nextLine();
				}
				}

			} catch (RuntimeException e) {
				System.out.println("Sorry your input is not correct! Try again.");
				scanOption.nextLine();
			}
		}

	}

	/**
	 * Create the frame.
	 */
	public JFrameGameboard() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize(buttonSize * (gb.getLength() + 1), buttonSize * (gb.getLength() + 1));

		int i = 0;
		for (int column = 0; column < gb.getLength(); column++) {
			for (int line = 0; line < gb.getLength(); line++) {
				spaces[line][column].setPosition(line, column);
				spaces[line][column].setSize(buttonSize, buttonSize);
				spaces[line][column].setLocation(buttonSize * line, buttonSize * column);
				spaces[line][column].setText(Integer.toString(i++));
				contentPane.add(spaces[line][column]);

			}
			System.out.println();
			System.out.println();

		}

		setContentPane(contentPane);
	}

	protected static void showArea() {
		// Showing the selected area around the coordinate (x,y)
		int coordX = coordinates[0];
		int coordY = coordinates[1];

		// (x-1,y-1) (x-1,y) (x-1,y+1)
		// (x,y-1) (x,y) (x, y+1)
		// (x+1,y-1) (x+1,y) (x+1,y+1)

		if ((coordX - 1) >= 0 && (coordY - 1) >= 0) {
			if (!spaces[(coordX - 1)][(coordY - 1)].getSpace().hasBomb()) {
				if (spaces[(coordX - 1)][(coordY - 1)].getSpace().isHidden()) {
					spaces[(coordX - 1)][(coordY - 1)].show();
					winCondition++;
				}
			}

		}

		if ((coordX - 1) >= 0) {
			if (!spaces[(coordX - 1)][coordY].getSpace().hasBomb()) {
				if (spaces[(coordX - 1)][coordY].getSpace().isHidden()) {
					spaces[(coordX - 1)][coordY].show();
					winCondition++;
				}
			}
		}

		if ((coordX - 1) >= 0 && (coordY + 1) < gb.getLength()) {
			if (!spaces[(coordX - 1)][(coordY + 1)].getSpace().hasBomb()) {
				if (spaces[(coordX - 1)][(coordY + 1)].getSpace().isHidden()) {
					spaces[(coordX - 1)][(coordY + 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordY - 1) >= 0) {
			if (!spaces[coordX][(coordY - 1)].getSpace().hasBomb()) {
				if (spaces[coordX][(coordY - 1)].getSpace().isHidden()) {
					spaces[coordX][(coordY - 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordY + 1) < gb.getLength()) {
			if (!spaces[coordX][(coordY + 1)].getSpace().hasBomb()) {
				if (spaces[coordX][(coordY + 1)].getSpace().isHidden()) {
					spaces[coordX][(coordY + 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength() && (coordY - 1) >= 0) {
			if (!spaces[(coordX + 1)][(coordY - 1)].getSpace().hasBomb()) {
				if (spaces[(coordX + 1)][(coordY - 1)].getSpace().isHidden()) {
					spaces[(coordX + 1)][(coordY - 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength() && (coordY - 1) >= 0) {
			if (!spaces[(coordX + 1)][(coordY - 1)].getSpace().hasBomb()) {
				if (spaces[(coordX + 1)][(coordY - 1)].getSpace().isHidden()) {
					spaces[(coordX + 1)][(coordY - 1)].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength()) {
			if (!spaces[(coordX + 1)][coordY].getSpace().hasBomb()) {
				if (spaces[(coordX + 1)][coordY].getSpace().isHidden()) {
					spaces[(coordX + 1)][coordY].show();
					winCondition++;
				}
			}
		}

		if ((coordX + 1) < gb.getLength() && (coordY + 1) < gb.getLength()) {
			if (!spaces[(coordX + 1)][(coordY + 1)].getSpace().hasBomb()) {
				if (spaces[(coordX + 1)][(coordY + 1)].getSpace().isHidden()) {
					spaces[(coordX + 1)][(coordY + 1)].show();
					winCondition++;
				}
			}
		}

		if (spaces[coordX][coordY].getSpace().isHidden()) {
			spaces[coordX][coordY].show();
			winCondition++;
		}
	}

	public static void gameOver(Gameboard gb) {
		System.out.println("You lose.");
		System.out.println();

		for (int line = 0; line < gb.getLength(); line++) {
			for (int column = 0; column < gb.getLength(); column++) {
				spaces[line][column].show();
			}
			System.out.println();
		}
	}

	// What the user needs to win the game
	public static void win(Gameboard gb) {
		System.out.println("You win.");
		System.out.println();
		for (int line = 0; line < gb.getLength(); line++) {
			for (int column = 0; column < gb.getLength(); column++) {
				spaces[line][column].setEnabled(false);;
			}
			System.out.println();
		}
	}

	public static short getWinCondition() {
		return winCondition;
	}

}
