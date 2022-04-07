package com.squareup;

public class Player {
    private String name;
    private int won = 0;
    private int lost = 0;
    private int draw = 0;

    // CTOR
    public Player(String name) {
        this.name = name;
    }

    // ACCESSOR METHODS
    public void setDraw() {
        this.draw++;
    }

    private int getDraws() {
        return this.draw;
    }

    public void setLoss() {
        this.lost++;
    }

    public int getLosses() {
        return this.lost;
    }

    public void setWin() {
        this.won++;
    }

    public int getWins() {
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


