package control;

import java.util.ArrayList;

/**
 * This is the concrete class implementing the easy level of the MoveStrategy
 */
import java.util.Random;
public class Easy implements MoveStrategy{

	static String[] btnVal = new String [9];
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
	 * This method picks next move for this level
	 * @param btnValue[]
	 * This parameter picks next move from empty cells
	 */
	public int selectMove(String btnValue[]){
		ArrayList<Integer> emptyVal = new ArrayList <Integer>();
		setBtnValue( btnValue);
		emptyVal = getEmptyCells();
		Random rand = new Random();
		int i=  rand.nextInt(emptyVal.size());
		return emptyVal.get(i);
	}
}
