package com.xogeeks.game.control;

/**
 * This class is backend to check the results.
 * @version 2.0
 */

public class GameLogic {
    private static int[] line = new int[3];
    public static boolean isWon(String btnValue[], String token){
        int n=0;
        /**
         * Horizontal
         */
        for (int i=0; i<7 ; i+=3)
            if ((btnValue [i] == token) && (btnValue [i+1] == token) && (btnValue [i+2] == token)){
                line[n++]= i;
                line[n++] = i+1;
                line[n] = i+2;
                return true;
            }
        /**
         * Vertical
         */
        for (int j=0; j<3 ; j++)
            if ((btnValue [j] == token) && (btnValue [j+3] == token) && (btnValue [j+6] == token)){
                line[n++]= j;
                line[n++] = j+3;
                line[n] = j+6;
                return true;
            }
        /**
         * Diagonal
         */
        if ((btnValue [0] == token) && (btnValue [4] == token) && (btnValue [8] == token)){
            line[n++]= 0;
            line[n++] = 4;
            line[n] = 8;
            return true;
        }

        if ((btnValue [2] == token) && (btnValue [4] == token) && (btnValue [6] == token)){
            line[n++]= 2;
            line[n++] = 4;
            line[n] = 6;
            return true;
        }
        return false;
    }

    /**
     * This method is used to check whether its tie or not.
     * @param checkPlayer
     * @return true if its tie otherwise false.
     */
    public static boolean isTie(int checkPlayer){
        if (checkPlayer==8)
            return true;
        else return false;
    }

    /**
     * This method returns winning position of the board.
     * @return array of 3 winning cells.
     */
    public static int[] getLine(){
        return line;
    }
}
