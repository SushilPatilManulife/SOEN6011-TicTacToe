package Test;

import java.awt.Color;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;

import junit.framework.TestCase;
import ui.GameBoard;

/**
 * The class <code>GameBoardTest</code> contains tests for the class {@link
 * <code>GameBoard</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 5/24/16 7:33 PM
 *
 * @author shidokht
 *
 * @version $Revision$
 */
public class GameBoardTest extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public GameBoardTest(String name) {
		super(name);
	}

	public void testAddMove(){
		JButton checkClick = new JButton();
		checkClick.setText("X");
		boolean b = GameBoard.addMove (checkClick);
		assertEquals(false, b);
	}
	public void testUpdateIcon(){
		String description = "X symbol";
		ImageIcon result;
		result = GameBoard.updateIcon();
		assertEquals(description, result.getDescription());
		
	}
/*	public void testUpdateButton(){

		btnOnGameBoard [5].addActionListener(new ActionListener(){
			public void actionPerformed(ActionEvent e){
				JButton Btn = (JButton) e.getSource();
				String token = GameBoard.updateBoard(Btn);
				assertEquals(mark1, token);
			}
		});

		btnOnGameBoard [5].doClick();
	
		
	}*/
}

