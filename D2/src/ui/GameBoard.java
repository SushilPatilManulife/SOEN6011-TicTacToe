package ui;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.Arrays;

import javax.swing.JButton;
import javax.swing.JDialog;
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

public class GameBoard extends JFrame implements ActionListener{
	//TODO : current round, total round;
	//TODO: start next round button
	//TODO: new game menu method
	private JPanel contentPane;
	//?//
	static JButton btnOnGameBoard[] = new JButton[9];
	
	JPanel gameBoardPannel = new JPanel(),
	scoreBoardPannel = new JPanel(),
	playerTurnPannel = new JPanel();
	static JLabel lblPlayerMove = new JLabel(""),
	
	lblPlayer2Score = new JLabel(""),
	lblPlayer1Score = new JLabel(""),
	lblScoreBoard = new JLabel("Score Board");
	 
	JMenuBar gameMenu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu help = new JMenu("Help");
	JMenuItem mnNewGame = new JMenuItem("New Game"),
	exit = new JMenuItem("Exit"),
	viewHelp = new JMenuItem("View Help");
	JDialog helpDg = new JDialog();
	
	/**
	 * checkPlayer counts number of moves and is used to set turn
	 */
	static int checkPlayer=0;
	static String turn;
	static String name1, name2;
	String mark1;
	String mark2;
	static String btnValue[] = new String[9];
	
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
		setTitle("Tic Tac Toe");
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setBounds(100, 100, 600, 304);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		gameBoardPannel.setBounds(25, 25, 314, 191);
		contentPane.add(gameBoardPannel);
		gameBoardPannel.setLayout(new GridLayout(0, 3, 0, 0));
		
		Help helpPnl = new Help();
		helpDg.add(helpPnl.getHelp(), null);
		helpDg.setBounds(100, 100, 450, 300);
		gameMenu.add(file);
		gameMenu.add(help);
		file.add(mnNewGame);
		file.add(exit);
		help.add(viewHelp);
		this.setJMenuBar(gameMenu);
		viewHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				helpDg.setVisible(true);
			}
		});
		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitGame();
			}
		});
		
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				exitGame();
			}
		});
	
		scoreBoardPannel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scoreBoardPannel.setBounds(373, 56, 125, 83);
		contentPane.add(scoreBoardPannel);
		scoreBoardPannel.setLayout(null);
				
		lblPlayer2Score.setBounds(23, 58, 108, 14);
		scoreBoardPannel.add(lblPlayer2Score);
		lblPlayer2Score.setText("Player 2 : 0");
		
		lblPlayer1Score.setBounds(23, 33, 108, 14);
		scoreBoardPannel.add(lblPlayer1Score);
		lblPlayer1Score.setText("Player 1 : 0");
		
		lblScoreBoard.setBounds(23, 8, 91, 14);
		scoreBoardPannel.add(lblScoreBoard);
		
		setPlayers();
		lblPlayerMove.setText("Current player: " + turn);
		playerTurnPannel.setBounds(373, 150, 250, 49);
		playerTurnPannel.setLayout(null);
		lblPlayerMove.setBounds(0, 11, 250, 14);
		playerTurnPannel.add(lblPlayerMove);
		contentPane.add(playerTurnPannel);
		
		/**
		 * create board				
		 */
		for(int i = 0 ; i < 9 ; i++){
			btnOnGameBoard[i]=new JButton();		
			btnOnGameBoard[i].setFont(new Font("Tahoma", Font.BOLD, 40));
			btnOnGameBoard[i].addActionListener(this);
			btnOnGameBoard[i].setBackground(new Color(0,0,0));
			btnOnGameBoard[i].setForeground(new Color(255,255,255));
			gameBoardPannel.add(btnOnGameBoard[i]);			
		}
			
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		String token;
		Object checkClick=e.getSource();
		for (int i = 0; i < 9 ; i++) {
			if(checkClick==btnOnGameBoard[i] && checkPlayer < 9 ){
				if(checkPlayer % 2 == 0){
					btnOnGameBoard[i].setText(mark1);
					btnValue[i] = mark1;
					token = mark1;
					btnOnGameBoard[i].setEnabled(false);
				}
				else{
					btnOnGameBoard[i].setForeground(new Color(255,0,0)); //???????????????????????????
					btnOnGameBoard[i].setText(mark2);
					btnValue[i] = mark2;
					token = mark2;
					btnOnGameBoard[i].setEnabled(false);
				}
				changePlayerTurn();
				checkPlayer++;
				Controller.checkStatus(btnValue, token, checkPlayer);
			}
		}
	}
	
	public void setPlayers(){
		name1 = Controller.getPlayer1Name();
		name2 = Controller.getPlayer2Name();
		mark1 = Controller.getPlayer1Mark();
		mark2 = Controller.getPlayer2Mark();
		turn = name1;
	}
	
	public static void changePlayerTurn() {
		if(checkPlayer % 2 == 0) {
		turn = name2;
		} 
		else {
		turn = name1;
		}
		lblPlayerMove.setText("Current player: " + turn);
	}
	
	public void exitGame(){
		int res = JOptionPane.showConfirmDialog(null, "There is a game in progress. Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION)
			System.exit(1);
	}
	
	public static void resetBoard(){
		for(int i = 0 ; i < 9 ; i++){
		btnOnGameBoard[i].setText("");
		btnOnGameBoard[i].setEnabled(true);
		}
		turn = name1;
		lblPlayerMove.setText("Current player: " + turn);
		checkPlayer = 0;
	}
	public static void roundWon(){
		changePlayerTurn();
		//TODO:Label instead of message , turn wins round #
		JOptionPane.showMessageDialog(null, turn + "wins this round!.\nClick OK to continue.");
		resetBoard();
		Arrays.fill(btnValue, null);
	}
	
	public static void roundTie(){
		changePlayerTurn();
		//TODO:Label instead of message , round # is a tie
		JOptionPane.showMessageDialog(null,  "It's a tie!\nClick OK to continue.");
		resetBoard();
		Arrays.fill(btnValue, null);
	}
	public static void gameWon(String result){
		//TODO: new game button, disable game board, display scores
		JOptionPane.showMessageDialog(null, result);
	}
	public static void updateScoreboard(int score1, int score2){
		lblPlayer2Score.setText("Player 2 : " + score2);
		lblPlayer1Score.setText("Player 1 : " + score1);
	}

}

