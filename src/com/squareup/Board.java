package com.squareup;

public class Board {
    private final String[] board = new String[9];
    public static Board getInstance() {
        return new Board();
    }

    public void printGameBoard(){
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
}