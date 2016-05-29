package control;

import java.util.ArrayList;
import java.util.Random;
public class Easy implements MoveStrategy{

	static String[] btnVal = new String [9];
	
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
	public int selectMove(String btnValue[]){
		ArrayList<Integer> emptyVal = new ArrayList <Integer>();
		setBtnValue( btnValue);
		emptyVal = getEmptyCells();
		Random rand = new Random();
		int i=  rand.nextInt(emptyVal.size());
		return emptyVal.get(i);
	}
}
