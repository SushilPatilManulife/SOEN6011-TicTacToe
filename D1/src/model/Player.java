package model;
/**
 *  This is a POJO class for players 
 * @version 1.0
 * */
public class Player {
	protected String name;
	protected String token;
	protected int score = 0;
	
	/**
	 * Constructor for Player class 
	 * @param name - Current player name 
	 * @param token - mark of current player
	 * */
	public Player(String name, String token){
		this.name = name;
		this.token = token;
	}
	
	/**
	 *  Getter for property name.
	 * @return String name of current player. 
	 * */
	public String getName(){ return name; }
	
	/**
	 *  Getter for property mark.
	 * @return String token of current player. 
	 * */
	public String getToken(){ return token; }
	
	/**
	 *  Getter for property score.
	 * @return Integer score of current player. 
	 * */
	public int getScore(){ return score; }
	
	/**
	 *  Setter for property score. 
	 * */
	public void resetScore(){ this.score = 0;}
}
