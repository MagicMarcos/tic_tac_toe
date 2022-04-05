package com.squareup;

import java.util.Arrays;
import java.util.Scanner;

public class Board {
    private static final String welcomeMsg = "welcome_banner.txt";
    private final String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private final String validInputs = "123456789";
    private String winner = null;

    private String currentPlayer = "X";

    public static Board getInstance() {
        return new Board();
    }

    private Scanner input = new Scanner(System.in);


    public void run() {

        // while it's null we keep the game running
        while(winner == null) {
            String squareNumber = input.nextLine();

            if (validInputs.contains(squareNumber)) {// -> validates the input
                int numInput = Integer.parseInt(squareNumber);

                // if square is already taken
                if (board[numInput - 1].equals("X") || board[numInput - 1].equals("O")) {
                    //  TODO: handle square is already taken message
                    System.out.println("NO!!!!");
                } else {
                    board[numInput - 1] = getCurrentPlayer();
                }

                printGameBoard();

                checkResult();

                nexTurn();
            } else {
                // TODO: handle the message for wrong input
                System.out.println("BRUH!");
            }
        }

        // put this into it's own game end method?
        // announce winner
        if (winner.equals("draw")) {
            System.out.println("It's a draw!");
        } else {
            System.out.println(winner + " wins!");
        }

        // TODO: game restart method.
    }

    // check result of game
    // if check result returns null, then go to nextTurn -> MAY NOT NEED TO RETURN ANYTHING
    public String checkResult() {
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
                // TODO: Delete this
                System.out.println("winnnneererrrr");
                winner = "X";
                return "X";
            }

            // O winner
            else if (line.equals("OOO")) {
                winner = "O";
                return "O";
            }
        }

        // draw check
        for (int idx = 0; idx < 9; idx++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(idx + 1))) {
                break;
            }
            else if (idx == 8) {
                winner = "draw";
                return "draw";
            }
        }

        return null;
    }

    public void nexTurn () {
        if (currentPlayer.equals("X")) {
            currentPlayer = "O";
        } else {
            currentPlayer = "X";
        }
    }

    public void printGameBoard() {
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

    // ACCESSOR METHODS
    public String getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(String currentPlayer) {
        this.currentPlayer = currentPlayer;
    }
}