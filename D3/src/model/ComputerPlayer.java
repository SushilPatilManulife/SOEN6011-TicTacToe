package model;

import java.awt.HeadlessException;
import java.io.IOException;

import control.Controller;
import control.MoveStrategyContext;
import ui.GameBoard;
/**
 * This is a POJO class for computer player which includes following methods
 * getName();
 * getToken();
 * getScore();
 * resetScore();
 * incrementScore();
 * @version 3.0
 * */
public class ComputerPlayer extends Player{
    /**
     * Represents the constructor for this class
     * @param mark Represents the mark chosen by the computer
     */
	public ComputerPlayer( String mark){
		this.token = mark;
		this.name = "Computer";
	}
    /**
     * This method is used to notify that computer player turn to play, 
     * and give the next best move to the computer and make the move
     * @param btnValue Represents the board value
     * @param ctx Represents the strategy that is difficulty level
     */
	public static void notifyTurn(String btnValue[], MoveStrategyContext ctx) throws HeadlessException, IOException{
		int p = ctx.getNextMove(btnValue);
		Controller.makeMove(p);
	}

}
