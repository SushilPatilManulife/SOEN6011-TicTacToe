package ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import control.Controller;

public class GameBoard extends GUIParent implements ActionListener{
	

	private JPanel contentPane;
	//?//
	static JButton btnOnGameBoard[] = new JButton[9];
	
	JPanel gameBoardPannel = new JPanel(),
	scoreBoardPannel = new JPanel(),
	playerTurnPannel = new JPanel();
	static JLabel lblPlayerMove = new JLabel(""),
	
	lblPlayer2Score = new JLabel(""),
	lblPlayer1Score = new JLabel(""),
	lblTiesScore	= new JLabel(""),
	lblScoreBoard = new JLabel("Score Board"),
	invalidMove = new JLabel("Invalid move! Choose an empty square.");
	 
	static JButton nextRound = new JButton("Start next round"); 
	/**
	 * checkPlayer counts number of moves and is used to set turn
	 */
	static int checkPlayer=0;
	static String turn;
	static String name1, name2;
	static String mark1;
	static String mark2;
	static String mark;
	static String btnValue[] = new String[9];
	
	static int currentRound;
	int totalRound;
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GameBoard frame = new GameBoard();
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
	public GameBoard() {
		initialize();
		setPlayers();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		gameBoardPannel.setBounds(25, 25, 314, 191);
		contentPane.add(gameBoardPannel);
		gameBoardPannel.setLayout(new GridLayout(0, 3, 0, 0));
		
		JPanel validateMove = new JPanel();
		validateMove.setBounds(25, 230, 314, 40);
		validateMove.add(invalidMove);
		invalidMove.setVisible(false);
		invalidMove.setForeground(Color.RED);
		contentPane.add(validateMove);

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitGame();
			}
		});
		//TODO : not working properly
		mnNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				resetBoard();
				String[] args = {};
				dispose();
				Controller.main(args);
			}
		});
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				exitGame();
			}
		});
	
		scoreBoardPannel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scoreBoardPannel.setBounds(373, 56, 131, 105);
		contentPane.add(scoreBoardPannel);
		scoreBoardPannel.setLayout(null);
				
		lblPlayer2Score.setBounds(23, 58, 108, 14);
		scoreBoardPannel.add(lblPlayer2Score);
		lblPlayer2Score.setText(name2 + " : 0");
		
		lblPlayer1Score.setBounds(23, 33, 108, 14);
		scoreBoardPannel.add(lblPlayer1Score);
		lblPlayer1Score.setText(name1 + " : 0");
		
		lblTiesScore.setBounds(23, 80, 108, 14);
		scoreBoardPannel.add(lblTiesScore);
		lblTiesScore.setText("Ties : 0");
		
		lblScoreBoard.setBounds(23, 8, 91, 14);
		scoreBoardPannel.add(lblScoreBoard);
		
		
		currentRound = 1;
		ImageIcon imageIcon = new ImageIcon (new ImageIcon("src/" + mark + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(imageIcon);
		playerTurnPannel.setBounds(363, 160, 250, 95);
		playerTurnPannel.setLayout(null);
		lblPlayerMove.setBounds(0, 11, 250, 60);
		playerTurnPannel.add(lblPlayerMove);
		nextRound.setBounds(0, 11, 155, 30);
		playerTurnPannel.add(nextRound);
		nextRound.setVisible(false);
		nextRound.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				startNextRound();
			}
		});
		contentPane.add(playerTurnPannel);
		
		/**
		 * create board				
		 */
		for(int i = 0 ; i < 9 ; i++){
			btnOnGameBoard[i]=new JButton();	
			btnOnGameBoard[i].setFont(new Font("Tahoma", Font.BOLD, 40));
			btnOnGameBoard[i].setText("");
			btnOnGameBoard[i].addActionListener(this);
			btnOnGameBoard[i].setBackground(new Color(32,22,63));
			gameBoardPannel.add(btnOnGameBoard[i]);			
		}
		checkPlayer=0;

	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		invalidMove.setVisible(false);
		String token;
		JButton checkClick=(JButton) e.getSource();
		if(checkClick.getText()== ""){
		for (int i = 0; i < 9 ; i++) {
			if(checkClick==btnOnGameBoard[i] && checkPlayer < 9 ){
				if(checkPlayer % 2 == 0){
					btnOnGameBoard[i].setForeground(new Color(247,247,242));
					btnOnGameBoard[i].setText(mark1);
					btnValue[i] = mark1;
					token = mark1;
				}
				else{
					btnOnGameBoard[i].setForeground(new Color(246,31,74));
					btnOnGameBoard[i].setText(mark2);
					btnValue[i] = mark2;
					token = mark2;
				}
				changePlayerTurn();
				checkPlayer++;
				Controller.checkStatus(btnValue, token, checkPlayer);
			}
		}
		}
		else {
			invalidMove.setVisible(true);
		}
	}
	
	private void setPlayers(){
		name1 = Controller.getPlayer1Name();
		name2 = Controller.getPlayer2Name();
		mark1 = Controller.getPlayer1Mark();
		mark2 = Controller.getPlayer2Mark();
		turn = name1;
		mark = mark1;
		totalRound = Controller.getTotalRound();
	}
	
	private static void changePlayerTurn() {
		if(checkPlayer % 2 == 0) {
		turn = name2;
		mark=mark2;
		} 
		else {
		turn = name1;
		mark=mark1;
		}

		ImageIcon imageIcon = new ImageIcon (new ImageIcon("src/" + mark + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(imageIcon);
	}
	
	private void exitGame(){
		int res = JOptionPane.showConfirmDialog(null, "There is a game in progress. Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION)
			System.exit(1);
	}
	
	private static void resetBoard(){
		for(int i = 0 ; i < 9 ; i++){
		btnOnGameBoard[i].setText("");
		btnOnGameBoard[i].setEnabled(true);
		btnOnGameBoard[i].setBackground(new Color(32,22,63));
		}
		turn = name1;
		mark = mark1;
		ImageIcon imageIcon = new ImageIcon (new ImageIcon("src/" + mark + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(imageIcon);
		checkPlayer = 0;
	}
	private void startNextRound(){
		changePlayerTurn(); 
		resetBoard(); 
		Arrays.fill(btnValue, null);
		currentRound++;
        nextRound.setVisible(false);
        lblPlayerMove.setVisible(true);
	}
	public static void roundWon(int[] line){
		for (int i = 0; i < 9 ; i++) {
					btnOnGameBoard[i].setEnabled(false);
		}
		for (int j = 0; j < 3 ; j++) {
					btnOnGameBoard[line[j]].setBackground(Color.blue);
		}
		//TODO:Label instead of message , turn wins round #
		JOptionPane.showMessageDialog(null, turn + "wins this round!.\nClick OK to continue.");
        nextRound.setVisible(true);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
	}
	
	public static void roundTie(){
		//TODO:Label instead of message , round # is a tie
		JOptionPane.showMessageDialog(null,  "It's a tie!\nClick OK to continue.");
        nextRound.setVisible(true);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
	}
	public static void gameWon(String result){
		//TODO: new game button, disable game board, display scores
		JOptionPane.showMessageDialog(null, result);
        nextRound.setVisible(false);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
	}
	public static void updateScoreboard(int score1, int score2, int score3){
		lblPlayer2Score.setText(name2 + " : " + score2);
		lblPlayer1Score.setText(name1 + " : " + score1);
		lblTiesScore.setText("Ties : " + score3);
	}

}

