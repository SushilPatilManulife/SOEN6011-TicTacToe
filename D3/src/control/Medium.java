package control;

import java.util.Random;

public class Medium implements MoveStrategy{

	@Override
	public int selectMove(String[] btnValue) {
		int p =0 ;
		Random rand = new Random();
		int i = rand.nextInt(100)+1;
		if(i<50)
			p = (new Hard().selectMove(btnValue));		
		else
			p = (new Easy().selectMove(btnValue));
				
		return p;
	}

	
}
