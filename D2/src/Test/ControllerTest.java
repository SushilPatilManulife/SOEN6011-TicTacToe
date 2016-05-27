package Test;

import org.junit.Test;

import control.Controller;
import junit.framework.TestCase;
import model.Player;

/**
 * The class <code>ControllerTest</code> contains tests for the class {@link
 * <code>Controller</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 5/24/16 4:41 PM
 *
 * @author shidokht
 *
 * @version $Revision$
 */
public class ControllerTest extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public ControllerTest(String name) {
		super(name);
	}
	@Test
	public void testController() {
		
		new Controller("player 1", "player 2", "X", "O", 3);
		assertEquals( "player 1", Controller.getPlayer1Name());
		assertEquals( "player 2", Controller.getPlayer2Name());
		assertEquals( 3, Controller.getTotalRound());
	}
	
	@Test
	public void testCheckResult(){
		new Controller("p1", "p2", "X", "O", 3);
		String result = Controller.checkResult();
		assertEquals ("p1 and p2 are tied each with a score of 0 out of 3", result);
	}
	@Test
	public void testgetCurrentPlayerName(){
		new Controller("p1", "p2", "X", "O", 3);
		String name = Controller.getCurrentPlayerName();
		assertEquals("p1",name);
		Controller.changeTurn();
		name = Controller.getCurrentPlayerName();
		assertEquals("p2",name);
		Controller.changeTurn();
		name = Controller.getCurrentPlayerName();
		assertEquals("p1",name);
	}
	@Test
	public void testgetCurrentPlayerMark(){
		new Controller("p1", "p2", "X", "O", 3);
		Controller.changeTurn();
		Controller.resetTurn();
		String mark = Controller.getCurrentPlayerMark();
		assertEquals("X",mark);
	}
}
