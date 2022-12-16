package view;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import model.Difficulty;
import model.Gameboard;

// Inheritance this class extends JFrame
// And this class implements, by hInheritance (JComponent), the Serializable interface
public class JFrameGameboard extends JFrame {

	private static final long serialVersionUID = 1L;

	private JPanel contentPane;
	private byte buttonSize = 50;
	private static short winCondition = 0;
	private static Gameboard gb = null;
	private static JButtonSpace[][] spaces;
	// Storing the selected button coordinates
	private static short coordinates[] = new short[2];

	/**
	 * Launch the application. later we will delete the main method and just call
	 * the class constructor in our Main class
	 */
	public static void main(String[] args, Difficulty difficulty) {

		try {
			for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
				if ("Nimbus".equals(info.getName())) {
					javax.swing.UIManager.setLookAndFeel(info.getClassName());
					break;
				}
			}
		} catch (ClassNotFoundException ex) {
			System.err.println(ex);
		} catch (InstantiationException ex) {
			System.err.println(ex);
		} catch (IllegalAccessException ex) {
			System.err.println(ex);
		} catch (javax.swing.UnsupportedLookAndFeelException ex) {
			System.err.println(ex);
		}

		gb = new Gameboard(difficulty);

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

	/**
	 * Create the frame.
	 */
	public JFrameGameboard() {
		setResizable(false);
		winCondition = 0;
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setLayout(null);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		this.setSize((buttonSize * gb.getLength()+15), (buttonSize * gb.getLength())+40);

		for (int column = 0; column < gb.getLength(); column++) {
			for (int line = 0; line < gb.getLength(); line++) {
				spaces[line][column].setPosition(line, column);
				spaces[line][column].setSize(buttonSize, buttonSize);
				spaces[line][column].setLocation(buttonSize * line, buttonSize * column);
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
		JOptionPane.showMessageDialog(null, "You lose!", "Minefield Game", JOptionPane.ERROR_MESSAGE);

		for (int line = 0; line < gb.getLength(); line++) {
			for (int column = 0; column < gb.getLength(); column++) {
				spaces[line][column].show();
			}
			System.out.println();
		}
	}

	// When the user win the game
	public static void win(Gameboard gb) {
		System.out.println("You win.");
		System.out.println();
		JOptionPane.showMessageDialog(null, "You win!", "Minefield Game", JOptionPane.OK_CANCEL_OPTION);

		for (int line = 0; line < gb.getLength(); line++) {
			for (int column = 0; column < gb.getLength(); column++) {
				spaces[line][column].show();
				spaces[line][column].setEnabled(false);
				
			}
			System.out.println();
		}
	}

	public static short getWinCondition() {
		return winCondition;
	}

}
