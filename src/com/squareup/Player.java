package com.squareup;

public class Player {
    private String name;

    public Player(String name) {
        this.name = name;
    }

    //    int played = 0; // Marcos (2-3-4)
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
        //       W            L             D
        return getName() + " (" + getWins() + "|" + getLosses() + "|" + getDraws() + ")";
    }

}


