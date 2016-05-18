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

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;

import control.Controller;
public class GameBoard extends JFrame implements ActionListener{
	
	private JPanel contentPane;
	JButton btnOnGameBoard[] = new JButton[9];
	
	JPanel gameBoardPannel = new JPanel(),
	scoreBoardPannel = new JPanel(),
	playerTurnPannel = new JPanel();
	JLabel lblPlayerMove = new JLabel("");
	//lblIcon = new JLabel();
	
	JMenuBar gameMenu = new JMenuBar();
	JMenu file = new JMenu("File");
	JMenu help = new JMenu("Help");
	JMenuItem mnNewGame = new JMenuItem("New Game"),
	exit = new JMenuItem("Exit"),
	viewHelp = new JMenuItem("View Help");

	
	/**
	 * checkPlayer counts number of moves and is used to set turn
	 */
	int checkPlayer=0;
	String turn, name1, name2, mark1, mark2, mark, markColor;
	
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
		setLocationRelativeTo(null);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		gameBoardPannel.setBounds(25, 25, 314, 191);
		contentPane.add(gameBoardPannel);
		gameBoardPannel.setLayout(new GridLayout(0, 3, 0, 0));
		//TODO: Remove/include in later deliverables
		JPanel resetPnl = new JPanel();
		JButton reset = new JButton("Reset Board");
		reset.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
					resetBoard();
			}
		});
		resetPnl.setBounds(363, 25, 125, 83);
		resetPnl.add(reset);
		resetPnl.setVisible(true);
		contentPane.add(resetPnl);
		/////////////////////////////////////////
		

		Help helpPnl = new Help();
		new Help();

		gameMenu.add(file);
		gameMenu.add(help);
		file.add(mnNewGame);
		file.add(exit);
		help.add(viewHelp);
		this.setJMenuBar(gameMenu);
		viewHelp.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				Help.getHelp();
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
		
		setPlayers();
		ImageIcon imageIcon = new ImageIcon (new ImageIcon("src/" + mark + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(imageIcon);
		playerTurnPannel.setBounds(363, 119, 250, 95);
		playerTurnPannel.setLayout(null);
		lblPlayerMove.setBounds(10, 11, 250, 60);
		
		playerTurnPannel.add(lblPlayerMove);
		contentPane.add(playerTurnPannel);
		//pack();
		/*lblIcon.setFont(new Font("Tahoma", Font.BOLD, 40));
		lblIcon.setBounds(97, 43, 61, 41);
		playerTurnPannel.add(lblIcon);
		lblIcon.setText(mark);
		lblIcon.setForeground(Color.BLACK);*/
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
		Object checkClick=e.getSource();
		for (int i = 0; i < 9 ; i++) {
			if(checkClick==btnOnGameBoard[i] && checkPlayer < 9 ){
				if(checkPlayer % 2 == 0){
					UIManager.getDefaults().put("Button.disabledText",Color.WHITE);
					btnOnGameBoard[i].setText(mark1);
					btnOnGameBoard[i].setEnabled(false);
				}
				else{
					//btnOnGameBoard[i].setForeground(new Color(255,0,0)); //???????????????????????????
					UIManager.getDefaults().put("Button.disabledText",Color.RED);
					btnOnGameBoard[i].setText(mark2);	
					btnOnGameBoard[i].setEnabled(false);
				}
				changePlayerTurn();
				checkPlayer++;
			}
		}
		
	}
	
	public void setPlayers(){
		name1 = Controller.getPlayer1Name();
		name2 = Controller.getPlayer2Name();
		mark1 = Controller.getPlayer1Mark();
		mark2 = Controller.getPlayer2Mark();
		turn = name1;
		mark = mark1;
	}
	
	public void changePlayerTurn() {
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
		/*lblIcon.setText(mark);
		if(mark.equals(mark2))
			lblIcon.setForeground(Color.RED);
		else 
			lblIcon.setForeground(Color.BLACK);*/
	}
	
	public void exitGame(){
		int res = JOptionPane.showConfirmDialog(null, "There is a game in progress. Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION)
			System.exit(1);
	}
	
	public void resetBoard(){
		for(int i = 0 ; i < 9 ; i++){
		btnOnGameBoard[i].setText("");
		btnOnGameBoard[i].setEnabled(true);
		}
		turn = name1;
		mark = mark1;
		ImageIcon imageIcon = new ImageIcon (new ImageIcon("src/" + mark + ".png").getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(imageIcon);
/*		lblIcon.setText(mark);
		lblIcon.setForeground(Color.BLACK);*/
		checkPlayer = 0;
	}

}