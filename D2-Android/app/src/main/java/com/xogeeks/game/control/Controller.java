package com.xogeeks.game.control;

import android.app.Activity;
import android.content.Intent;

import com.xogeeks.game.model.Player;
import com.xogeeks.game.tictactoe.GameBoard;

/**
 * This class is overall controller of the game like check status of game, changing turn and calculate results of the round.
 * @version 2.0
 */
public class Controller extends Activity {
    static Player player1;
    static Player player2;
    private static Player currentPlayer;
    private static int tie;
    private static int turn;
    private static int totalRound;
    private static int currentRound;

    /**
     * Constructor of class to initialize all parameters.
     * @param name1 Player 1 name.
     * @param name2 Player 2 name.
     * @param mark1 Player 1 mark.
     * @param mark2 Player 2 mark.
     * @param totalRound Rounds which player selected.
     */
    public Controller(String name1, String name2, String mark1, String mark2, int totalRound){
        currentRound = 1;
        tie = 0;
        Controller.totalRound = totalRound;
        player1 = new Player(name1, mark1);
        player2 = new Player(name2, mark2);
        turn = 1;
        currentPlayer = player1;
    }

    /**
     * Getters property to get player 1's name.
     * @return Player 1 name
     */
    public static String getPlayer1Name(){
        return player1.getName();
    }
    /**
     * Getters property to get player 2's name.
     * @return Player 2 name
     */
    public static String getPlayer2Name(){
        return player2.getName();
    }
    /**
     * Getters property to get number of rounds.
     * @return Total rounds
     */
    public static int getTotalRound(){
        return totalRound;
    }
    /**
     * Getters property to get current player's name.
     * @return Name of Current player.
     */
    public static String getCurrentPlayerName(){
        return currentPlayer.getName();
    }
    /**
     * Getters property to get current player's mark.
     * @return Mark of Current player.
     */
    public static String getCurrentPlayerMark(){
        return currentPlayer.getToken();
    }

    /**
     * This method switches turn between players.
     */
    public static void changeTurn(){
        if (turn == 1)
        {
            turn =2;
            currentPlayer = player2;
        }
        else
        {
            turn =1;
            currentPlayer = player1;
        }
    }

    /**
     * This method resets turn
     */
    public static void resetTurn(){
        turn = 1;
        currentPlayer = player1;
    }

    /**
     * This method checks status of the round.
     * @param btnValue Cells where values of 3*3 are stored.
     * @param token X or O value
     * @param checkPlayer Number of moves.
     */
    public static void checkStatus(String btnValue[], String token, int checkPlayer){
        boolean won = GameLogic.isWon(btnValue, token);
        if(won){
            if (player1.getToken() == token)
                player1.incrementScore();
            else
                player2.incrementScore();
            int[] line = new int [3];
            line = GameLogic.getLine();
            GameBoard.roundWon(line,token);
            currentRound++;
        }
        else if (GameLogic.isTie(checkPlayer)){
            tie++;
            GameBoard.roundTie();
            currentRound++;
        }
        if(currentRound > totalRound){
            String result = checkResult();
            GameBoard.gameWon(result);
        }
        GameBoard.updateScoreboard(player1.getScore(), player2.getScore(), tie);
    }

    /**
     * This method check result of the overall game.
     * @return Game result win or tie.
     */
    private static String checkResult() {

        int max = player1.getScore();
        String result = player1.getName() +  " is the winner with score of "+ player1.getScore()+" out of "+ totalRound;

        if(player2.getScore()> max){
            result = player2.getName() + " is the winner with score of "+ player2.getScore()+" out of "+ totalRound;
        }
        else if(player2.getScore()== max){
            result = player2.getName() + " and "+ player1.getName()+ " are tied each with a score of "+ player2.getScore()+" out of "+ totalRound;
        }
        return result;

    }
}
