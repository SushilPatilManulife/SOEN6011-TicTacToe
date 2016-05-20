package model;
/**
<<<<<<< HEAD
 * This is a POJO class for players which includes following methods
 * getName();
 * getToken(); 
 * getScore();
 * resetScore();
=======
 *  This is a POJO class for players 
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
 * @version 1.0
 * */
public class Player {
	protected String name;
	protected String token;
	protected int score = 0;
	
<<<<<<< HEAD
	/** 
	 * Constructor - which sets current player name and mark. 
	 * @param name Current player name 
	 * @param token Current player mark
=======
	/**
	 * Constructor for Player class 
	 * @param name - Current player name 
	 * @param token - mark of current player
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
	 * */
	public Player(String name, String token){
		this.name = name;
		this.token = token;
	}
	
	/**
	 *  Getter for property name.
<<<<<<< HEAD
	 * @return String Name of current player. 
=======
	 * @return String name of current player. 
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
	 * */
	public String getName(){ return name; }
	
	/**
	 *  Getter for property mark.
<<<<<<< HEAD
	 * @return String Name of current player. 
=======
	 * @return String token of current player. 
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
	 * */
	public String getToken(){ return token; }
	
	/**
<<<<<<< HEAD
	 * Getter for property score.
	 * @return String Name of current player. 
	 * */
	public int getScore(){ return score; }
	
	/** 
	 * Setter for property score. 
=======
	 *  Getter for property score.
	 * @return Integer score of current player. 
	 * */
	public int getScore(){ return score; }
	
	/**
	 *  Setter for property score. 
>>>>>>> 90c82befe8495ae71366f17e46fee36c8b43f895
	 * */
	public void resetScore(){ this.score = 0;}
}
