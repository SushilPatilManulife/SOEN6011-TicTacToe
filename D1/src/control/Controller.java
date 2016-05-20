package control;
import javax.swing.SwingUtilities;

import model.Player;
import ui.GameBoard;
import ui.PlayerOptionMenu;
/** <h1>Tic Tac Toe</h1> 
 * Deliverable 1 is a stand-alone Java application that is able to show 3*3 board of tic tac toe game.
 * Functional requirements Completed are  <ol><li> Display 3*3 Board. </li>
 *  									  <li> Player can choose the "X" or "O" mark.</li>
 * 										  <li> Player can reset the game.</li>
 * 										  <li> Players can set name.</li>
 * 										  <li> Draw an “X” or an “O” on cell where the user clicks only if it's empty.</li>
 * 										  <li> switch user turns.</li>
 * 										  <li> Player can exit the game. Confirmation is needed when game is in progress </li>
 * 										  <li> Display help</li></ol>
 * <br>Controller.java - This class is main controller class to start a application.
 * @author Shidokht Hejazi Sepehr
 * @author Sushil Patil
 * @author Beerpreet Singh Guliani
 * @author Amir Hakim
 * @version 1.0
 * @since 2016-05-10
 */
public class Controller {

	/**
	 * player 1
	 */
	static Player player1;
	/**
	 * player 2
	 */
	static Player player2;

	
	/** Constructor - for initializing the new player entity with some unique name and mark.
	 * @param name1 Name of Player 1
	 * @param name2 Name of Player 2
	 * @param mark1 Mark for Player 1
	 * @param mark2 Mark for Player 2
	 */
	public Controller(String name1, String name2, String mark1, String mark2){
		player1 = new Player(name1, mark1);
		player2 = new Player(name2, mark2);
		GameBoard.main();
	}
	
	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable(){
			public void run(){
				new PlayerOptionMenu().setVisible(true);
			}
		});
	}
	/**
	 * This method returns player 1's name
	 * @return String player 1 name.
	 */
	public static String getPlayer1Name(){
		return player1.getName();
	}
	
	/**
	 * This method returns player 2's name
	 * @return String player 2 name.
	 */
	public static String getPlayer2Name(){
		return player2.getName();
	}
	
	/**
	 * This method returns player 1's mark
	 * @return String Mark for player 1.
	 */
	public static String getPlayer1Mark(){
		return player1.getToken();
	}
	/**
	 * This method returns player 2's mark
	 * @return String Mark for player 2.
	 */
	public static String getPlayer2Mark(){
		return player2.getToken();
	}
}
