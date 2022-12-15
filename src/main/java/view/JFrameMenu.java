package view;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import main.TerminalGameMain;
import model.Difficulty;

public class JFrameMenu extends JFrame {

	private JPanel contentPane;
	protected Difficulty difficulty = Difficulty.EASY;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {

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

		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					JFrameMenu frame = new JFrameMenu();
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

	JFrameSetDifficulty menuDifficulty = new JFrameSetDifficulty();
	
	public JFrameMenu() {
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(contentPane);

		JButton btnNewButton = new JButton("Open in terminal");
		btnNewButton.setBounds(10, 156, 130, 30);
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				TerminalGameMain.main(null);
			}
		});

		JButton btnNewButton_1 = new JButton("Play");
		btnNewButton_1.setBounds(296, 156, 130, 30);
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				difficulty = JFrameSetDifficulty.difficulty;
				System.out.println(difficulty);
				JFrameGameboard.main(null, difficulty);
			}
		});
		contentPane.setLayout(null);
		contentPane.add(btnNewButton_1);
		contentPane.add(btnNewButton);

		JLabel lblNewLabel = new JLabel("Minefield Game");
		lblNewLabel.setBounds(170, 57, 100, 50);
		contentPane.add(lblNewLabel);
		
		JButton btnChangeDifficulty = new JButton("Change Difficulty");
		btnChangeDifficulty.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (menuDifficulty == null) {
					menuDifficulty.setLocationRelativeTo(null);
					menuDifficulty.setVisible(true);
					menuDifficulty.setResizable(false);
				} else {
					menuDifficulty.setLocationRelativeTo(null);
					menuDifficulty.setVisible(true);
					menuDifficulty.setResizable(false);
				}
				difficulty = JFrameSetDifficulty.difficulty;
			}
		});
		btnChangeDifficulty.setBounds(157, 156, 130, 30);
		contentPane.add(btnChangeDifficulty);
	}
}