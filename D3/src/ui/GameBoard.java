package ui;
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
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;

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
 * @version 2.0
 */
public class GameBoard extends GUIParent {
	

	private JPanel contentPane;
	//?//
	
	
	JPanel scoreBoardPannel = new JPanel(),
	playerTurnPannel = new JPanel();
	static JLabel lblPlayerMove = new JLabel(""),
	
	lblPlayer2Score = new JLabel(""),
	lblPlayer1Score = new JLabel(""),
	lblTiesScore	= new JLabel(""),
	lblScoreBoard = new JLabel("Score Board"),
	invalidMove = new JLabel("Invalid move! Choose an empty square.");
	private static GameBoard gameBoard=null; 
	static JButton nextRound = new JButton("Start next round"); 
	static URL turnImage;
	static Color color1;
	static Color color2;
	 public static int mode;
	/**
	 * checkPlayer counts number of moves and is used to set turn
	 */
	static int checkPlayer=0;
	static String turn;
	static String name1, name2;
	static String mark = "X";
	static String btnValue[] = new String[9];
	static boolean boardEnable = true;
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
		
		name1 = Controller.getPlayer1Name();
		name2 = Controller.getPlayer2Name();
		
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
		lblPlayerMove.setText(name1 + "'s turn" );
		lblPlayerMove.setIcon(updateIcon());
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
		
		checkPlayer=0;
		if( mode == 1 && turn == "Computer"){
			Controller.notifyComputer(btnValue);
		}
	}
	

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
	public static ImageIcon updateIcon()
	{
		turnImage = GameBoard.class.getResource("/"+mark+".png");
		ImageIcon imageIcon = new ImageIcon (new ImageIcon(turnImage).getImage().getScaledInstance(40, 40, Image.SCALE_DEFAULT));
		imageIcon.setDescription(mark+" symbol");
		return imageIcon;
	}
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
     * This method is used to get player properties such as name, mark and total rounds.
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
		if( mode == 1 && turn == "Computer"){
			Controller.notifyComputer(btnValue);
		}
		currentRound++;
        nextRound.setVisible(false);
        lblPlayerMove.setVisible(true);
	}
	/**
     * This method displays winner of round.
     * @param line To highlight wining position on 3*3 board.
     * @param token Mark is either X or O.
     */
	public static void roundWon(int[] line){
		//TODO: Remove this Comment to play the music // playMusic();
		Board.displayRoundResult(line);
		//TODO:Label instead of message , turn wins round #
		//ImageIcon ii = new ImageIcon(gameBoard.getClass().getResource("/fireworks.gif"));
		//System.out.println(ii);

		//JOptionPane.showMessageDialog(null,"", turn + " wins this round!.\nClick OK to continue.",JOptionPane.INFORMATION_MESSAGE,ii);

        nextRound.setVisible(true);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
        boardEnable = false;
	}
	/**
     * Displayed when round is tie.
     */
	public static void roundTie(){
		//TODO:Label instead of message , round # is a tie
		JOptionPane.showMessageDialog(null,  "It's a tie! \n Click OK to continue.");
        nextRound.setVisible(true);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
        boardEnable = false;
	}
	/**
     * Displayed when player wins the Game.
     * @param result Final result of all rounds.
	 * @throws IOException 
	 * @throws HeadlessException 
     */
	public static void gameWon(String result) throws HeadlessException, IOException{
		//TODO: new game button, disable game board, display 
		//playMusic();
		String[] option=new String[2];
		option[0]="New Game";
		option[1]="Cancel";
		int userChoise=JOptionPane.showOptionDialog(null, gameBoard.getPanel(result),"Game Result",0,JOptionPane.INFORMATION_MESSAGE,null,option,null);
		switch (userChoise) {
		case 0:
			//TODO: Add code for new game
			JOptionPane.showMessageDialog(null, "New Game!");
			break;

		case 1:
			//TODO: Add code for cancel 
			JOptionPane.showMessageDialog(null, "Cancel!");
			break;
		}
		nextRound.setVisible(false);
        lblPlayerMove.setVisible(false);
        invalidMove.setVisible(false);
        boardEnable = false;
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
	 * This method is used to run music.
	 */
	public static void playMusic() 
	{       
	    AudioPlayer MGP = AudioPlayer.player;
	    AudioStream BGM;
	    AudioData MD;
	    ContinuousAudioDataStream loop = null;

	    try
	    {
	        InputStream test = new FileInputStream("cheering.wav");
	        BGM = new AudioStream(test);
	        AudioPlayer.player.start(BGM);
	        
	    }
	    catch(FileNotFoundException e){
	        System.out.print(e.toString());
	    }
	    catch(IOException error)
	    {
	        System.out.print(error.toString());
	    }
	    MGP.start(loop);
	}
	/**
	 * This method is used to modify Message box
	 * @param result Result of the game to be displayed using message box.
	 * @return Modified message box.
	 * @throws IOException 
	 */
	private JPanel getPanel(String result) throws IOException {
        JPanel msgBoxPanel = new JPanel();
        JLabel msgBoxAnimationLabel = new JLabel(""),
        msgBoxResultLabel=new JLabel(result);
        
        msgBoxResultLabel.setFont(new Font("Lucida Calligraphy", Font.BOLD, 20));
        msgBoxResultLabel.setForeground(Color.RED);
        ImageIcon image = null;
        image = new ImageIcon(this.getClass().getResource("/winner.gif"));
        msgBoxAnimationLabel.setIcon(image);
        msgBoxPanel.add(msgBoxAnimationLabel);
        msgBoxPanel.add(msgBoxResultLabel);

        return msgBoxPanel;
    }

}

