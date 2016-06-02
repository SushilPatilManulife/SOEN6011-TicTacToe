package model;
/**
 * This is a POJO class for human player which includes following methods
 * getName();
 * getToken();
 * getScore();
 * resetScore();
 * incrementScore();
 * @version 2.0
 * */
public class HumanPlayer extends Player{
    /**
     * Represents the constructor for this class
     * @param name
     * It is the name of human player
     * @param token
     * It is the icon chosen by the human player
     */
	public HumanPlayer(String name, String token){
		this.name = name;
		this.token = token;
	}
}
