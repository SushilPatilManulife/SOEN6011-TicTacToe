package model;

public class Player {
	protected String name;
	protected String token;
	protected int score = 0;

	public String getName(){ return name; }
	public String getToken(){ return token; }
	public int getScore(){ return score; }
	public void resetScore(){ this.score = 0;}
	public void incrementScore(){ this.score++;}
}
