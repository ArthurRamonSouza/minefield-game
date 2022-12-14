package view;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import model.Space;

// JBUtton extends an abstract class called AbstractButton
// And this class implements, by hInheritance, the Serializable interface
public class JButtonSpace extends JButton {

	private static final long serialVersionUID = 1L;

	private int line;
	private int column;
	private Space space = new Space();

	public JButtonSpace() {
		this.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				onClick(e);
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

	public void onClick(ActionEvent e) {
		this.space.show();
		System.out.println("(Line: " + column + ", Column: " + line + ")");
		System.out.println("Space is hidden? " + this.space.isHidden());

	}

}
