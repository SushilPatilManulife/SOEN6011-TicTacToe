package com.xogeeks.game.model;

/**
 * This is a POJO class for players which includes following methods
 * getName();
 * getToken();
 * getScore();
 * resetScore();
 * @version 2.0
 * */
public class Player {
    protected String name;
    protected String token;
    protected int score = 0;
    public Player(String name, String token){
        this.name = name;
        this.token = token;
    }
    public String getName(){ return name; }
    public String getToken(){ return token; }
    public int getScore(){ return score; }
    public void resetScore(){ this.score = 0;}
    public void incrementScore(){ this.score++;}
}
