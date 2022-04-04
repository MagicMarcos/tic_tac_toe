package com.squareup;

public class Board {
    private final Integer[] board = {1, 2, 3, 4, 5, 6, 7, 8, 9};


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