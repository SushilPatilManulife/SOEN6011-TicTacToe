package ui;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.EmptyBorder;
import java.awt.Dimension;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
/**
 *Help.java
 *This class displays help for player. 
 */
public class Help extends JPanel{

	JPanel helpPnl; 
	JLabel content;
	static JDialog helpDg; 
	String helpPage = "<html><h1> <center>Introduction </center></h1>"+
"This is an application for playing the interesting Tic-Tac-Toe game. In the options menu:"+
"<br><ul><li> You have a feature of playing with a computer in single player mode</li>"+
"<br><li> Players can choose their name.</li>"+
"<br><li> Players can choose their marks: X or O.</li>" +
"<br><li> Players can choose who wants to go first.</li>"+
"<br><li> players can choose the number of rounds they wants to play in a game.</li></ul>"+
"<br> After the player presses the start game button:"
+ "<br><ul><li> The main screen with the game board will be opened.</li>"+ 
"<br><li> A scoreboard to keep record of the score of players for each round of the game will be displayed.</li></ul>"+
"<h2><center> Rules of the Game</center></h2>"+
"<br><ol><li> Player 1 moves first.</li>"+
"<br><li> A piece may be placed on any empty space.</li>"+
"<br><li> A player wins by being the first to connect a line of friendly pieces from one side or corner of the board to other.</li>"+
"<br><li> The game ends when either one player wins or it is not longer possible for a player to win a game "+
"( in this case the result is draw)</li></ol>"+
"</html>";
	/**
	 * Constructor for help class
	 */
	public Help(){
		helpPnl = new JPanel();
	    helpDg = new JDialog();
		content = new JLabel(helpPage);

		content.setBounds(28, 42, 660, 148);
		helpPnl.setBounds(100, 100, 698, 297);
		helpPnl.setLayout(null);

		helpPnl.setLayout(new BorderLayout());


		//content.setText(helpPage);
		helpPnl.add(content);
		helpDg.add(helpPnl);
		helpDg.setTitle("Help");
		//content.setEditable(false);
		helpPnl.setVisible(true);
		helpDg.pack();
	}
	/**
	 * method to display help
	 * @return the dialog with help content 
	 */
	public static void getHelp(){
		helpDg.setVisible(true);
	}
}
