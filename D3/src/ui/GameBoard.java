package ui;
import control.PlayMusic;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.HeadlessException;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

import model.XmlIO;
import sun.audio.AudioData;
import sun.audio.AudioPlayer;
import sun.audio.AudioStream;
import sun.audio.ContinuousAudioDataStream;
import control.Controller;
/**
 * This class to display GUI for 3*3 board where players can play game.
 * Other than game board players can see scores, current round and players turn with mark.
 * If player has selected 3/5 rounds mode, then after each round players are able to see start new round button.
 * After each round winner of that round is displayed and according to mode 3/5, best of Game will be displayed after finishing all rounds.
 * Winner will get some Gift!!!
 * @version 3.0
 */
public class GameBoard extends GUIParent {
	

	private JPanel contentPane;
	JPanel scoreBoardPannel = new JPanel(),
			roundLbLPnl = new JPanel(),
	playerTurnPannel = new JPanel();
	static JLabel lblPlayerMove = new JLabel(""),
	roundLbl = new JLabel (""),
	lblPlayer2Score = new JLabel(""),
	lblPlayer1Score = new JLabel(""),
	lblTiesScore	= new JLabel(""),
	resultLbl	= new JLabel(""),
	lblScoreBoard = new JLabel("Score Board"),
	invalidMove = new JLabel("Invalid move! Choose an empty square.");
	private static GameBoard gameBoard=null; 
	static JButton nextRound = new JButton("Start next round"); 
	static URL turnImage, roundImage, resultImage;
	static Color color1;
	static Color color2;
	 public static int mode;
	 public static int playOption;
	 public static String music;
	/*
	 * checkPlayer counts number of moves and is used to set turn
	 */
	 
	static int checkPlayer=0;
	static String turn;
	static String name1, name2;
	static String mark = "X";
	static String btnValue[] = new String[9];
	static boolean boardEnable = true;
	static int totalRound;
	static PlayMusic ls;
	public static void main() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					if(mode==1){
						music="background_computer.wav";
					}
					else{
						music="background_player.wav";
					}
					ls=new PlayMusic(music);
					ls.play();
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
	 * @throws IOException 
	 * @throws HeadlessException 
	 */
	public GameBoard() throws HeadlessException, IOException {
		gameBoard=GameBoard.this;
		initialize();
		setPlayers();
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		contentPane = new JPanel(){	 
		public void paintComponent (Graphics g)
		{
			super.paintComponent(g);
			 try {
				g.drawImage(ImageIO.read(getClass().getResource ("/bg2.jpg")), 0, 0, null);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		};
		
		playerTurnPannel.setOpaque(false);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
			
		
		contentPane.add(new Board());
		
		
		JPanel validateMove = new JPanel();
		validateMove.setOpaque(false);
		validateMove.setBounds(25, 230, 314, 55);
		validateMove.add(invalidMove);
		validateMove.add(resultLbl);
		resultLbl.setVisible(false);
		//resultLbl.setBackground(Color.cyan);
		invalidMove.setVisible(false);
		invalidMove.setBackground(new Color(247,101,101));
		invalidMove.setForeground(Color.white);
		invalidMove.setOpaque(true);
		contentPane.add(validateMove);

		exit.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				exitGame();
			}
		});
		mnNewGame.setVisible(true);
		for(ActionListener act : mnNewGame.getActionListeners()) {
			mnNewGame.removeActionListener(act);
		}
		mnNewGame.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){

				resetBoard();
				String[] args = {};
				ls.stop();
				
				Controller.main(args);
				dispose();
				
			}
		});
		addWindowListener(new WindowAdapter(){
			@Override
			public void windowClosing(WindowEvent we){
				exitGame();
			}
		});
	
		roundLbLPnl.setBorder(new LineBorder(new Color(0, 0, 0)));
		roundLbLPnl.setBounds(373, 25, 131, 40);
		contentPane.add(roundLbLPnl);
		roundLbl.setText("Round 1 / "+ totalRound);
		roundLbl.setIcon(getRoundIcon());
		roundLbLPnl.add(roundLbl);
		
		scoreBoardPannel.setBorder(new LineBorder(new Color(0, 0, 0)));
		scoreBoardPannel.setBounds(373, 80, 131, 80);
		contentPane.add(scoreBoardPannel);
		scoreBoardPannel.setLayout(null);
		
		name1 = Controller.getPlayer1Name();
		name2 = Controller.getPlayer2Name();
		
		lblPlayer2Score.setBounds(23, 33, 108, 14);
		scoreBoardPannel.add(lblPlayer2Score);
		lblPlayer2Score.setText(name2 + " : 0");
		
		lblPlayer1Score.setBounds(23, 8, 108, 14);
		scoreBoardPannel.add(lblPlayer1Score);
		lblPlayer1Score.setText(name1 + " : 0");
		
		lblTiesScore.setBounds(23, 58, 108, 14);
		scoreBoardPannel.add(lblTiesScore);
		lblTiesScore.setText("Ties : 0");
		
		lblPlayerMove.setText(name1 + "'s turn" );
		lblPlayerMove.setIcon(updateIcon());
		lblPlayerMove.setVisible(true);
		playerTurnPannel.setBounds(363, 160, 250, 95);
		playerTurnPannel.setLayout(null);
		lblPlayerMove.setBounds(0, 11, 250, 60);
		playerTurnPannel.add(lblPlayerMove);
		
		nextRound.setBounds(0, 11, 155, 30);
		playerTurnPannel.add(nextRound);
		nextRound.setVisible(false);
		nextRound.addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				try {
					startNextRound();
				} catch (HeadlessException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		});
		contentPane.add(playerTurnPannel);
		if(mode == 1)
			setTitle("Tic Tac Toe - " + Controller.getLevel()+ " level");
		checkPlayer=0;
		if( mode == 1 && turn == "Computer"){
			Controller.notifyComputer(btnValue);
			
		}
	}

	/**
	 * This method is called when a button is clicked 
	 * to perform appropriate actions based on mode and turn
	 * @param checkClick Represents clicked button
	 */
	public static void cellClicked(JButton checkClick) throws HeadlessException, IOException {
		invalidMove.setVisible(false);
		if( mode == 1 && turn == "Computer")
			return;
		if(!addMove(checkClick))
			invalidMove.setVisible(true);
		if( mode == 1 && turn == "Computer" && boardEnable){
			Controller.notifyComputer(btnValue);
		}
		
	}
	/**
	 * This method calls checks the cell if it is empty and updates the board
	 * @param checkClick This represents the button that is clicked
	 * @return Returns if its added or not
	 */
	public static boolean addMove(JButton checkClick) throws HeadlessException, IOException{
		if(checkClick.getText()== ""){
		    updateBoard(checkClick);
			Controller.checkStatus(btnValue, mark, checkPlayer);
			changePlayerTurn();
			checkPlayer++;
			return true;
	}
		else
			return false;
	}
	/**
	 * Based on the players turn it gets the player icon and returns
	 * @return It returns the icon for the players turn
	 */
	public static ImageIcon updateIcon()
	{
		turnImage = GameBoard.class.getResource("/"+mark+".png");
		ImageIcon imageIcon = new ImageIcon (new ImageIcon(turnImage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		imageIcon.setDescription(mark+" symbol");
		return imageIcon;
	}
	/**
	 * Sets the icon for number of rounds and returns the icon
	 * @return Returns the icon of player for selected number of rounds
	 */
	public static ImageIcon getRoundIcon()
	{
		roundImage = GameBoard.class.getResource("/"+totalRound+".jpg");
		ImageIcon imageIcon = new ImageIcon (new ImageIcon(roundImage).getImage().getScaledInstance(30, 30, Image.SCALE_DEFAULT));
		return imageIcon;
	}
	/**
	 * This method sets the round result icon
	 * @return Returns the icon for displaying the round result
	 */
	public static ImageIcon getResultIcon()
	{
		resultImage = GameBoard.class.getResource("/result.png");
		ImageIcon imageIcon = new ImageIcon (new ImageIcon(resultImage).getImage().getScaledInstance(45, 45, Image.SCALE_DEFAULT));
		return imageIcon;
	}
	/**
	 * This method updates the board and array that stores the values for each cell on the board
	 * @param checkClick gets the button that is clicked
	 */
	public static void updateBoard(JButton checkClick){
			if(checkPlayer < 9 ){
				if(checkPlayer % 2 == 0){
					int i = Board.update(checkClick, color1, mark);
					btnValue[i] = mark;
				}
				else{
					int i = Board.update(checkClick, color2, mark);
					btnValue[i] = mark;
				}
			}
	}
	/**
     * This method is used to set current player properties such as name, mark, color and total rounds.
     */
	private void setPlayers(){
		mark = Controller.getCurrentPlayerMark();
		color1 = new Color(247,247,242);
		color2 = new Color(246,31,74);
		if(mark == "O"){
			color1 = new Color(246,31,74);
			color2 = new Color(247,247,242);
		}
		turn = Controller.getCurrentPlayerName();
		totalRound = Controller.getTotalRound();
	}
	/**
     * This method used for changing players turn after player places mark on board.
     */
	private static void changePlayerTurn() {
		Controller.changeTurn();
		
		turn = Controller.getCurrentPlayerName();
		mark = Controller.getCurrentPlayerMark();

		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(updateIcon());

	}
	/**
	 * This method is called when player wants to exit the game
	 */
	private void exitGame(){
		int res = JOptionPane.showConfirmDialog(null, "There is a game in progress. Are you sure you want to exit?", "Exit", JOptionPane.YES_NO_OPTION);
		if (res == JOptionPane.YES_OPTION)
			System.exit(1);
	}
	/**
     * Reset Board while starting new game.
     */
	private static void resetBoard(){
		Board.reset();
		
		for(int i = 0 ; i < 9 ; i++)
		btnValue[i]=null;
		
		Controller.resetTurn();
		turn = Controller.getCurrentPlayerName();
		mark = Controller.getCurrentPlayerMark();
		lblPlayerMove.setText(turn + "'s turn" );
		lblPlayerMove.setIcon(updateIcon());
		checkPlayer = 0;
		boardEnable = true;

		}
	/**
     * This method starts new round.
	 * @throws IOException 
	 * @throws HeadlessException 
     */
	private void startNextRound() throws HeadlessException, IOException{
		changePlayerTurn(); 
		resetBoard(); 
		Arrays.fill(btnValue, null);
		int currentRound = Controller.getCurrentRound();
        nextRound.setVisible(false);
        resultLbl.setVisible(false);
        lblPlayerMove.setVisible(true);
        roundLbl.setText("Round "+ currentRound+" / "+ totalRound);
		if( mode == 1 && turn == "Computer"){
			Controller.notifyComputer(btnValue);
		}

	}
	/**
     * This method displays winner of round.
     * @param line To highlight wining position on 3*3 board.
     * @param token Mark is either X or O.
     */
	public static void roundWon(int[] line){
		Board.displayRoundResult(line);
		resultLbl.setText(turn + " wins this round!");
		resultLbl.setIcon(getResultIcon());
        Font f = resultLbl.getFont();
        resultLbl.setFont(new Font(f.getFontName(), Font.BOLD, 14));
		resultLbl.setOpaque(true);
        resultLbl.setVisible(true);
        resultLbl.setBackground(new Color(201,214,230));
        nextRound.setVisible(true);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
        boardEnable = false;
	}
	/**
     * Displayed when round is tie.
     */
	public static void roundTie(){
		nextRound.setVisible(true);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
        resultLbl.setText("It's a tie!");
        resultLbl.setIcon(getResultIcon());
        resultLbl.setBackground(new Color(201,214,230));
        Font f = resultLbl.getFont();
        resultLbl.setFont(new Font(f.getFontName(), Font.BOLD, 14));
        resultLbl.setOpaque(true);
        resultLbl.setVisible(true);
        boardEnable = false;
	}
	/**
     * Displayed when player wins the Game.
     * @param result Final result of all rounds.
	 * @throws IOException 
	 * @throws HeadlessException 
     */
	public static void gameWon(String result) throws HeadlessException, IOException{

		nextRound.setVisible(false);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
        boardEnable = false;
        String state = Controller.getState();
        if(mode==1 && result.substring(0).contains("Computer"))
        	JOptionPane.showMessageDialog(null, result ,"Game Result",JOptionPane.INFORMATION_MESSAGE);
        else if (state == "tie")
        	JOptionPane.showMessageDialog(null, result ,"Game Result",JOptionPane.INFORMATION_MESSAGE);
        else{
        	JOptionPane.showMessageDialog(null, gameBoard.getPanel(result,"/winner_gift.gif"),"Game Result",JOptionPane.INFORMATION_MESSAGE);

        }
	}
	/**
     * This method updates score on score board.
     * @param score1 Player 1 score.
     * @param score2 Player 2 score.
     * @param score3 Tie score.
     */
	public static void updateScoreboard(int score1, int score2, int score3){
		lblPlayer2Score.setText(name2 + " : " + score2);
		lblPlayer1Score.setText(name1 + " : " + score1);
		lblTiesScore.setText("Ties : " + score3);
	}

	/**
	 * This method is creating Message box
	 * @param result Result of the game to be displayed using message box.
	 * @return Modified message box.
	 * @throws IOException 
	 */
	private JPanel getPanel(String result, String winnerImage) throws IOException {
        JPanel msgBoxPanel = new JPanel();
        JLabel msgBoxAnimationLabel = new JLabel(""),
        msgBoxResultLabel=new JLabel(result);
        
        msgBoxResultLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 18));
        msgBoxResultLabel.setForeground(new Color(23,36,110));
        ImageIcon image = null;
        image = new ImageIcon(this.getClass().getResource(winnerImage));
        msgBoxAnimationLabel.setIcon(image);
        msgBoxPanel.add(msgBoxAnimationLabel);
        msgBoxPanel.add(msgBoxResultLabel);

        return msgBoxPanel;
    }
}

