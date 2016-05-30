package control;

import java.util.ArrayList;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.List;
import java.util.Map;

public class Hard implements MoveStrategy {
	
	static String[] btnVal = new String [9];
	static Map<Integer, Integer> pointScore;
	
	public static void setBtnValue(String btnValue[]){
		for(int i=0;i<9;i++)
		btnVal[i]=btnValue[i];
	}
	private static ArrayList<Integer> getEmptyCells(){
		ArrayList<Integer> emptyVal = new ArrayList <Integer>();
		for (int i=0;i<btnVal.length;i++){
			if (btnVal[i]==null){
				emptyVal.add(i);
			}
		}
		return emptyVal;
	}
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
	public static int bestMove(){
		int max = Integer.MIN_VALUE;
		int point = 0;
		for (int i:pointScore.keySet()){
			if (max<pointScore.get(i)){
				max = pointScore.get(i);
				point=i;
			}
			
		}
		return point;
	}
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
