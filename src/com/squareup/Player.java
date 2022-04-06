package com.squareup;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    int won = 0;
    int lost = 0;
    int draw = 0;


    public void setDraw() {
        this.draw++;
    }

    private int getDraws() {
        return this.draw;
    }

    public void setLoss() {
        this.lost++;
    }

    private int getLosses() {
        return this.lost;
    }

    public void setWin() {
        this.won++;
    }

    private int getWins() {
        return this.won;
    }

    public String getName() {
        return this.name;
    }

    @Override
    public String toString() {
        return getName() + " (" + getWins() + "|" + getLosses() + "|" + getDraws() + ")";
    }

}


