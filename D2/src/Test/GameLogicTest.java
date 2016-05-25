package Test;

import org.junit.Test;

import control.GameLogic;
import junit.framework.TestCase;

/**
 * The class <code>GameLogicTest</code> contains tests for the class {@link
 * <code>GameLogic</code>}
 *
 * @pattern JUnit Test Case
 *
 * @generatedBy CodePro at 5/25/16 4:24 PM
 *
 * @author shidokht
 *
 * @version $Revision$
 */
public class GameLogicTest extends TestCase {

	/**
	 * Construct new test instance
	 *
	 * @param name the test name
	 */
	public GameLogicTest(String name) {
		super(name);
	}

	/**
	 * Run the boolean isWon(String[], String) method test
	 */
	@Test
	public void testIsWon() {
		//100% branch coverage
		
		//horizontal
		String[] btnValue1 = {"X","X","X","O","O",null,null,null,null};
		String token = "X";
		boolean result = GameLogic.isWon(btnValue1, token);
		assertEquals(true, result);
		//diagonal
		String[] btnValue2 = {"X","O","O","O","X",null,null,null,"X"};
		result = GameLogic.isWon(btnValue2, token);
		assertEquals(true, result);
		//diagonal
		String[] btnValue5 = {"X","O","O","O","X",null,null,null,null};
		result = GameLogic.isWon(btnValue5, token);
		assertEquals(false, result);
		//vertical
		String[] btnValue3 = {"X","O","O","X","X",null,"X",null,"O"};
		result = GameLogic.isWon(btnValue3, token);
		assertEquals(true, result);
		//vertical
		String[] btnValue4 = {"X","O","O","X","X",null,null,null,"O"};
		result = GameLogic.isWon(btnValue4, token);
		assertEquals(false, result);
		//diagonal
		String[] btnValue6 = {null,"O","X",null,"X",null,"X",null,"O"};
		result = GameLogic.isWon(btnValue6, token);
		assertEquals(true, result);
		//diagonal
		String[] btnValue7 = {null,"O","X",null,null ,null,"X",null,"O"};
		result = GameLogic.isWon(btnValue7, token);
		assertEquals(false, result);
		//diagonal
		String[] btnValue9 = {null,"O","X",null,"X" ,null,null,null,"O"};
		result = GameLogic.isWon(btnValue9, token);
		assertEquals(false, result);
		//diagonal
		String[] btnValue8 = {"X","O","X",null,null ,null,"X",null,"O"};
		result = GameLogic.isWon(btnValue8, token);
		assertEquals(false, result);
	}
}

