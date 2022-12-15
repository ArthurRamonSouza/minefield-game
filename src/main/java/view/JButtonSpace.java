package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Gameboard;
import model.Space;

// JBUtton extends an abstract class called AbstractButton
// And this class implements, by hInheritance, the Serializable interface
public class JButtonSpace extends JButton {

	private static final long serialVersionUID = 1L;

	private int line;
	private int column;
	private short[] coordinates;
	private static Gameboard gamebBoard;
	private Space space = new Space();

	public JButtonSpace(short[] coordinates, Gameboard gb) {
		gamebBoard = gb;
		this.coordinates = coordinates;
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(e, JButtonSpace.this.coordinates);
			}
		});
	}

	public int getLine() {
		return line;
	}

	public int getColumn() {
		return column;
	}

	public void setPosition(int line, int column) {
		this.line = line;
		this.column = column;
	}

	public Space getSpace() {
		return space;
	}

	public void setSpace(Space space) {
		this.space = space;
	}

	// This method will show the space
	public void show() {
		if (this.space.hasBomb()) {
			this.setText("x");

		} else {
			this.getSpace().show();
			if (this.space.getBombsNear() != 0) {
				this.setText(Integer.toString(this.space.getBombsNear()));

			} else {
				this.setText(" ");
			}

		}
		this.setEnabled(false);
	}

	public void onClick(ActionEvent e, short[] coordinates) {
		
		coordinates[0] = (short) line;
		coordinates[1] = (short) column;
		JFrameGameboard.showArea();
		
		// Condition for the game to continue
		int win = (gamebBoard.getLength() * gamebBoard.getLength()) - gamebBoard.getBombs().length;
		System.out.println(win);
		System.out.println(JFrameGameboard.getWinCondition());
		if (JFrameGameboard.getWinCondition() == win) {
			JFrameGameboard.win(gamebBoard);
		}
		if(space.hasBomb()) {
			JFrameGameboard.gameOver(gamebBoard);
		}
	}
}