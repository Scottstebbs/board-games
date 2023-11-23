package client;

import java.awt.BorderLayout;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

public class TicTacToeGame extends JPanel {

	private static final long serialVersionUID = 1L;
	private JLabel playerTurn;
	private String player1;
	private String player2;
	private int currentMove = 0;
	private int[] winningMove = { 7, 56, 73, 84, 146, 273, 292, 448 };
	private int[] btnVals = { 1, 2, 4, 8, 16, 32, 64, 128, 256 };
	private List<Integer> totals = new ArrayList<>(Arrays.asList(0, 0));
	private boolean isTie = false;

	/**
	 * Create the panel.
	 */
	
	// Create constructor for 1 parameter if user selects AI
	
	
	public TicTacToeGame(String player1, String player2) {
		this.player1 = player1;
		this.player2 = player2;
		setLayout(new BorderLayout());
		setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		playerTurn = newTurnLbl();
		JPanel gamePanel = newGamePanel();

		add(playerTurn, BorderLayout.NORTH);
		add(gamePanel, BorderLayout.CENTER);
	}

	private JPanel newGamePanel() {
		JPanel gamePanel = new JPanel();
		gamePanel.setLayout(new GridLayout(3, 3));

		for (int i = 0; i < 9; i++) {
			JButton btn = newBtn();
			btn.setName(Integer.toString(btnVals[i]));
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					if (currentMove == 0) {
						btn.setText("X");
						totals.set(0, totals.get(0) + Integer.parseInt(btn.getName()));
					} else if (currentMove == 1) {
						totals.set(1, totals.get(1) + Integer.parseInt(btn.getName()));
						btn.setText("O");
					}
					
					if (checkWinner()) {
						// handle game win
						System.out.println("Win");
					}
					
					if (isTie) {
						// handle tie game;
						System.out.println("Tie");
					}
					
					handleMoveChange();
				}
			});
			gamePanel.add(btn);
		}

		return gamePanel;
	}

	private JButton newBtn() {
		JButton btn = new JButton();
		btn.setFont(new Font("Arial", Font.PLAIN, 40));
		return btn;
	}

	/**
	 * Label for current player's turn
	 * 
	 * @return
	 */
	private JLabel newTurnLbl() {
		JLabel playerTurn = new JLabel();
		playerTurn.setOpaque(true);
		playerTurn.setHorizontalAlignment(SwingConstants.CENTER);
		playerTurn.setText(player1 + "'s Turn");
		System.out.println(player1);
		return playerTurn;
	}

	/**
	 * Increments or decrements currentMove 0 = X 1 = O
	 */
	private void handleMoveChange() {
		if (currentMove == 0) {
			currentMove = 1;
			playerTurn.setText(player2 + "'s Turn");
		} else if (currentMove == 1) {
			playerTurn.setText(player1 + "'s Turn");
			currentMove = 0;
		}
	}

	private boolean checkWinner() {
		for (int i = 0; i < winningMove.length; i++) {
			if ((totals.get(currentMove) & winningMove[i]) == winningMove[i]) {
				return true;
			}
		}

		if (totals.get(0) + totals.get(1) == 511) {
			isTie = true;
		}

		return false;
	}
}