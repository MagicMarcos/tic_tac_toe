package com.squareup;


import java.util.Arrays;


public class Board {
    private final String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String winner = null;
    private String currentPlayer = "X";


    public static Board getInstance() {
        return new Board();
    }


    // check result of game
    public void checkResult() {
        for (int idx = 0; idx < 8; idx++) {
            String line = null;


            switch (idx) {
                case 0:
                    line = board[0] + board[1] + board[2];
                    break;
                case 1:
                    line = board[3] + board[4] + board[5];
                    break;
                case 2:
                    line = board[6] + board[7] + board[8];
                    break;
                case 3:
                    line = board[0] + board[3] + board[6];
                    break;
                case 4:
                    line = board[1] + board[4] + board[7];
                    break;
                case 5:
                    line = board[2] + board[5] + board[8];
                    break;
                case 6:
                    line = board[0] + board[4] + board[8];
                    break;
                case 7:
                    line = board[2] + board[4] + board[6];
                    break;
            }
//X winner
            if (line.equals("XXX")) {
                setWinner("X");
                return;
            }


// O winner
            else if (line.equals("OOO")) {
                setWinner("O");
                return;
            }
        }


// draw check
        for (int idx = 0; idx < 9; idx++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(idx + 1))) {
                break;
            } else if (idx == 8) {
                setWinner("draw");
                return;
            }
        }


    }


    public void claimSquare(int squareNum) {
        if (board[squareNum - 1].equals("X") || board[squareNum - 1].equals("O")) {
            System.out.println("This square is already claimed. Try again.");
        } else {
            board[squareNum - 1] = getCurrentPlayer();
        }
    }


    public void changePlayers() {
        if (currentPlayer.equals("X")) {
            setCurrentPlayer("O");
        } else {
            setCurrentPlayer("X");
        }
    }


    public void show() {
// printing the game board
        System.out.println("|-----|-----|-----|");
        System.out.println("|  " + board[0] + "  |  "
                + board[1] + "  |  " + board[2]
                + "  |");
        System.out.println("|-----|-----|-----|");
        System.out.println("|  " + board[3] + "  |  "
                + board[4] + "  |  " + board[5]
                + "  |");
        System.out.println("|-----|-----|-----|");
        System.out.println("|  " + board[6] + "  |  "
                + board[7] + "  |  " + board[8]
                + "  |");
        System.out.println("|-----|-----|-----|");
    }


    public void printWinner() {
        if (winner.equals("draw")) {
            System.out.println("It's a draw...");
        } else {
            System.out.println("Player " + winner + " wins!");
        }
    }


    // ACCESSOR METHODS
    public String getCurrentPlayer() {
        return currentPlayer;
    }


    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }


    public String getWinner() {
        return winner;
    }


    public void setWinner(String winner) {
        this.winner = winner;
    }
}