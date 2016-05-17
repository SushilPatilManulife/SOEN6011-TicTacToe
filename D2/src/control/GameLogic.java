package control;

public class GameLogic {
	//TODO
/*	public static boolean isTie(){
		return false;
	}*/
	public static boolean isWon(String btnValue[], String token){
		
		/**
		 * Horizontal
		 */
		for (int i=0; i<7 ; i+=3)
		if ((btnValue [i] == token) && (btnValue [i+1] == token) && (btnValue [i+2] == token))
			return true;
		/**
		 * Vertical
		 */
		for (int j=0; j<3 ; j++)
		if ((btnValue [j] == token) && (btnValue [j+3] == token) && (btnValue [j+6] == token))
			return true;
		/**
		 * Diagonal
		 */
		if ((btnValue [0] == token) && (btnValue [4] == token) && (btnValue [8] == token))
			return true;
		if ((btnValue [2] == token) && (btnValue [4] == token) && (btnValue [6] == token))
			return true;
		
		return false;
		
	}
}
