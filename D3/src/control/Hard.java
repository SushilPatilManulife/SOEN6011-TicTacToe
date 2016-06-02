package control;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;
import java.util.Random;
/**
 * This is the concrete class implementing the hard level of the MoveStrategy
 */
public class Hard implements MoveStrategy {
	
	static String[] btnVal = new String [9];
	static Map<Integer, Integer> pointScore;
	/**
	 * 
	 * @param btnValue
	 * This methods gets board value and sets to the local array
	 */
	public static void setBtnValue(String btnValue[]){
		for(int i=0;i<9;i++)
		btnVal[i]=btnValue[i];
	}
	/**
	 * This methods gets empty cells and adds to the array
	 * @return 
	 * emptyVal is returning empty cell
	 */
	private static ArrayList<Integer> getEmptyCells(){
		ArrayList<Integer> emptyVal = new ArrayList <Integer>();
		for (int i=0;i<btnVal.length;i++){
			if (btnVal[i]==null){
				emptyVal.add(i);
			}
		}
		return emptyVal;
	}
	/**
	 * This method implements the game theory for minimizing loss for maximum loss scenario
	 * @param depth
	 * This parameter contains the number of levels in minimax tree
	 * @param playerMark
	 * Represents the current player mark
	 * @param computerMark
	 * Represents the computer player mark
	 * @param humanMark
	 * Represents the human player mark
	 * @return
	 * Represents the score at that depth level of the tree
	 */
	public static int minimax(int depth, String playerMark, String computerMark, String humanMark){
		ArrayList<Integer>  emptyCell = new ArrayList <>();
		int score;
		String opponentMark = (playerMark == "X") ? "O" : "X";
		/*
		 * keep score list for children
		 * different states will have different scores
		 * need to find max or min and return
		 */
		ArrayList<Integer> scoreList = new ArrayList<>();
		//assign human and computer(X) tokens
		if(GameLogic.isWon(btnVal, computerMark))
			return 1;
		if(GameLogic.isWon(btnVal, humanMark))
			return -1;
		emptyCell = getEmptyCells();
		if(emptyCell.isEmpty())
			return 0;
		
		for(int i=0; i<emptyCell.size();i++){
			btnVal[emptyCell.get(i)]=playerMark;
			score = minimax(depth+1, opponentMark, computerMark, humanMark);
			scoreList.add(score);
			
			if( depth == 0) {
				pointScore.put(emptyCell.get(i), score);
			}
		    for(int j=0;j<emptyCell.size();j++)
				btnVal[emptyCell.get(j)]=null;
				}
		return playerMark == computerMark? max(scoreList):min(scoreList);
			/*if(playerMark == "X"){
				btnVal[emptyCell.get(i)]="X";
				score = minimax(depth+1, "O");
				scoreList.add(score);
				
				
				 * top node, add children result values to pick the best move
				 
				//
				if( depth == 0) {
					pointScore.put(emptyCell.get(i), score);
				}
			}
			else if(playerMark =="O"){
				btnVal[emptyCell.get(i)]="O";
				score = minimax(depth+1, "X");
				scoreList.add(score);
		}
        for(int j=0;j<emptyCell.size();j++)
		btnVal[emptyCell.get(j)]=null;
		}
		return playerMark == "X"? max(scoreList):min(scoreList);*/
		
	}
	/**
	 * This method picks maximum value
	 * @param score
	 * This represents the array of scores returned from minimax algorithm
	 * @return
	 * It returns maximum value from the array of scores
	 */
	public static int max (List<Integer> score)
	{
		int max = Integer.MIN_VALUE;
		for (int i=0; i<score.size(); i++){
			if (max<score.get(i)){
				max = score.get(i);
			}
			
		}
		return max;
	}
	/**
	 * This method picks the minimum value
	 * @param score
	 * This represents the array of scores returned from minimax algorithm
	 * @return
	 * Returns minimum value from the array of scores
	 */
	public static int min (List<Integer> score)
	{
		int min = Integer.MAX_VALUE;
		for (int i=0; i<score.size(); i++){
			if (min>score.get(i)){
				min = score.get(i);
			}	
		}
		return min;
	}
	/**
	 * This methods goes though all maximum values from minimax scores
	 * and picks the board cells that can are applicable for these values, 
	 * selects one cell and returns selected cell number.
	 * @return
	 * Returns selected cell position
	 */
	public static int bestMove(){
		int max = Integer.MIN_VALUE;
		ArrayList <Integer> positions = new ArrayList<Integer>();
		for (int i:pointScore.keySet()){
			if (max<pointScore.get(i)){
				max = pointScore.get(i);
			}
		}
		for (int i:pointScore.keySet()){
			if(pointScore.get(i) == max){
				positions.add(i);
			}
		}
		Random rand = new Random();
		int p =  rand.nextInt(positions.size());
		return positions.get(p);
	}
	/**
	 * This method is used to pick the best move
	 * @param
	 * btnValue - represents the board value
	 * @return
	 * It return the cell of best move
	 */
	@Override
	public int selectMove(String[] btnValue) {
		pointScore = new Hashtable<Integer, Integer>();
		setBtnValue(btnValue);
		String computerToken = Controller.getCurrentPlayerMark();
	    String humanMark = (computerToken == "X") ? "O" : "X";
		minimax(0, computerToken, computerToken, humanMark);
		return bestMove();
	}
}
