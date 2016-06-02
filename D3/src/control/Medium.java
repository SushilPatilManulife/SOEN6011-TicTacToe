package control;

import java.util.Random;
/**
 * This is the concrete class implementing the medium level of the MoveStrategy
 */
public class Medium implements MoveStrategy{
    /**
     * This method is implemented to pick the next best move by the computer 
     * by stopping the human player if human player is able to make a win scenario, 
     * or if the computer is able to make the win scenario , then do it,
     * or pick the middle cell if empty,
     * otherwise pick any ramdom empty cell
     * @param
     * This method represents the board value
     * @return
     * It returns the cell position to make next best move
     */
	@Override
	public int selectMove(String[] btnValue) {
		String computerToken = Controller.getCurrentPlayerMark();
	    String humanMark = (computerToken == "X") ? "O" : "X";
	    
	if(btnValue[0]==(computerToken) && btnValue[1]==(computerToken) && btnValue[2]==(null))
		return 2;
	else if(btnValue[3]==(computerToken) && btnValue[4]==(computerToken) && btnValue[5]==(null))
		return 5;
	else if(btnValue[6]==(computerToken) && btnValue[7]==(computerToken) && btnValue[8]==(null))
		return 8;
	
	else if(btnValue[1]==(computerToken) && btnValue[2]==(computerToken) && btnValue[0]==(null))
		return 0;
	else if(btnValue[4]==(computerToken) && btnValue[5]==(computerToken) && btnValue[3]==(null))
		return 3;
	else if(btnValue[7]==(computerToken) && btnValue[8]==(computerToken) && btnValue[6]==(null))
		return 6;
	
	else if(btnValue[0]==(computerToken) && btnValue[2]==(computerToken) && btnValue[1]==(null))
		return 1;
	else if(btnValue[3]==(computerToken) && btnValue[5]==(computerToken) && btnValue[4]==(null))
		return 4;
	else if(btnValue[6]==(computerToken) && btnValue[8]==(computerToken) && btnValue[7]==(null))
		return 7;
	
	else if(btnValue[0]==(computerToken) && btnValue[3]==(computerToken) && btnValue[6]==(null))
		return 6;
	else if(btnValue[1]==(computerToken) && btnValue[4]==(computerToken) && btnValue[7]==(null))
		return 7;
	else if(btnValue[2]==(computerToken) && btnValue[5]==(computerToken) && btnValue[8]==(null))
		return 8;
	
	else if(btnValue[3]==(computerToken) && btnValue[6]==(computerToken) && btnValue[0]==(null))
		return 0;
	else if(btnValue[4]==(computerToken) && btnValue[7]==(computerToken) && btnValue[1]==(null))
		return 1;
	else if(btnValue[5]==(computerToken) && btnValue[8]==(computerToken) && btnValue[2]==(null))
		return 2;
	
	else if(btnValue[0]==(computerToken) && btnValue[6]==(computerToken) && btnValue[3]==(null))
		return 3;
	else if(btnValue[1]==(computerToken) && btnValue[7]==(computerToken) && btnValue[4]==(null))
		return 4;
	else if(btnValue[2]==(computerToken) && btnValue[8]==(computerToken) && btnValue[5]==(null))
		return 5;
	//diagonal
	else if(btnValue[0]==(computerToken) && btnValue[4]==(computerToken) && btnValue[8]==(null))
		return 8;
	else if(btnValue[4]==(computerToken) && btnValue[8]==(computerToken) && btnValue[0]==(null))
		return 0;
	else if(btnValue[0]==(computerToken) && btnValue[8]==(computerToken) && btnValue[4]==(null))
		return 4;
	//diagonal
	else if(btnValue[2]==(computerToken) && btnValue[4]==(computerToken) && btnValue[6]==(null))
		return 6;
	else if(btnValue[6]==(computerToken) && btnValue[4]==(computerToken) && btnValue[2]==(null))
		return 2;
	else if(btnValue[6]==(computerToken) && btnValue[2]==(computerToken) && btnValue[4]==(null))
		return 4;
	//horizontal opp
	else if(btnValue[0]==(humanMark) && btnValue[1]==(humanMark) && btnValue[2]==(null))
		return 2;
	else if(btnValue[3]==(humanMark) && btnValue[4]==(humanMark) && btnValue[5]==(null))
		return 5;
	else if(btnValue[6]==(humanMark) && btnValue[7]==(humanMark) && btnValue[8]==(null))
		return 8;
	//horizontal opp
	else if(btnValue[1]==(humanMark) && btnValue[2]==(humanMark) && btnValue[0]==(null))
		return 0;
	else if(btnValue[4]==(humanMark) && btnValue[5]==(humanMark) && btnValue[3]==(null))
		return 3;
	else if(btnValue[7]==(humanMark) && btnValue[8]==(humanMark) && btnValue[6]==(null))
		return 6;
	//horizontal opp
	else if(btnValue[0]==(humanMark) && btnValue[2]==(humanMark) && btnValue[1]==(null))
		return 1;
	else if(btnValue[3]==(humanMark) && btnValue[5]==(humanMark) && btnValue[4]==(null))
		return 4;
	else if(btnValue[6]==(humanMark) && btnValue[8]==(humanMark) && btnValue[7]==(null))
		return 7;
	//vertical opp
	else if(btnValue[0]==(humanMark) && btnValue[3]==(humanMark) && btnValue[6]==(null))
		return 6;
	else if(btnValue[1]==(humanMark) && btnValue[4]==(humanMark) && btnValue[7]==(null))
		return 7;
	else if(btnValue[2]==(humanMark) && btnValue[5]==(humanMark) && btnValue[8]==(null))
		return 8;
	//vertical opp
	else if(btnValue[3]==(humanMark) && btnValue[6]==(humanMark) && btnValue[0]==(null))
		return 0;
	else if(btnValue[4]==(humanMark) && btnValue[7]==(humanMark) && btnValue[1]==(null))
		return 1;
	else if(btnValue[5]==(humanMark) && btnValue[8]==(humanMark) && btnValue[2]==(null))
		return 2;
	//vertical opp
	else if(btnValue[0]==(humanMark) && btnValue[6]==(humanMark) && btnValue[3]==(null))
		return 3;
	else if(btnValue[1]==(humanMark) && btnValue[7]==(humanMark) && btnValue[4]==(null))
		return 4;
	else if(btnValue[2]==(humanMark) && btnValue[8]==(humanMark) && btnValue[5]==(null))
		return 5;
	//diagonal opp
	else if(btnValue[0]==(humanMark) && btnValue[4]==(humanMark) && btnValue[8]==(null))
		return 8;
	else if(btnValue[4]==(humanMark) && btnValue[8]==(humanMark) && btnValue[0]==(null))
		return 0;
	else if(btnValue[0]==(humanMark) && btnValue[8]==(humanMark) && btnValue[4]==(null))
		return 4;
	//diagonal opp
	else if(btnValue[2]==(humanMark) && btnValue[4]==(humanMark) && btnValue[6]==(null))
		return 6;
	else if(btnValue[6]==(humanMark) && btnValue[4]==(humanMark) && btnValue[2]==(null))
		return 2;
	else if(btnValue[6]==(humanMark) && btnValue[2]==(humanMark) && btnValue[4]==(null))
		return 4;
//	
//	else if(btnValue[0]==(humanMark) && btnValue[4]==(computerToken) && btnValue[8]==(humanMark))
//		return 5;
//		
//	else if(btnValue[2]==(humanMark) && btnValue[4]==(computerToken) && btnValue[6]==(humanMark)) 
//		return 3;
	
	else if(btnValue[4]==(null))
		return 4;
	else
		return new Easy().selectMove(btnValue);

}
/*	public int selectMove(String[] btnValue) {
		int p =0 ;
		Random rand = new Random();
		int i = rand.nextInt(100)+1;
		if(i<50)
			p = (new Hard().selectMove(btnValue));		
		else
			p = (new Easy().selectMove(btnValue));
				
		return p;
	}*/

	
}
