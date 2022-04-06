package com.squareup;

import java.util.ArrayList;

class Player {
    Player player1 = new Player();
    Player player2 = new Player();

    int played = 0; // Marcos (2-3-4)
    int won = 0;
    int lost = 0;
    int draw = 0;
    int totalGames = 0;

    private ArrayList<Player> player = new ArrayList<>();

    public void getWins(Player player, int p1Score, int p2Score) {

        if (p1Score > p2Score) {

            won++;

        } else if (p1Score == p2Score) {

            draw++;

        } else {

            lost++;

        }

        totalGames++;

//        if (p2Score != null) {
//            p2Score.getWins(null, p2Score, p1Score);
//        }

    }
}


