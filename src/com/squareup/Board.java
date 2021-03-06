package com.squareup;
import static com.squareup.Colors.*;

import java.util.Arrays;

public class Board {

    private String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
    private String winner = null;

    private String x = CYAN + "X" + RESET;
    private String o = MAGENTA + "O" + RESET;
    private String xWin = getX() + getX() + getX();
    private String oWin = getO() + getO() + getO();

    private String currentPlayer = getX();

    public static Board getInstance() {
        return new Board();
    }

    /*
     * Runs algorithm to determine result of the game.
     *  -> sets and returns "winner" (X,O,draw,null)
     */
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
            if (line.equals(getXWin())) {
                setWinner(getX());
                break;
            }
            // O winner
            else if (line.equals(getOWin())) {
                setWinner(getO());
                break;
            }
        }

        // draw check
        for (int idx = 0; idx < 9; idx++) {
            if (Arrays.asList(board).contains(
                    String.valueOf(idx + 1))) {
                break;
            } else if (idx == 8) {
                setWinner("draw");
            }
        }
        return getWinner();
    }

    // Reassigns value in board array, base on player selection IF that value is not "claimed", e.g. X or O
    public boolean claimSquare(int squareNum) {
        boolean isValidClaim = false;

        if (!board[squareNum - 1].equals(getX()) && !board[squareNum - 1].equals(getO())) {
            board[squareNum - 1] = getCurrentPlayer();
            isValidClaim = true;
        }

        return isValidClaim;
    }

    // Swaps value of current player
    public void changePlayers() {
        String player = getCurrentPlayer().equals(getX()) ? getO() : getX();
        setCurrentPlayer(player);
    }

    public void show() {
        System.out.println("||=====||=====||=====||");
        System.out.println("||  " + board[0] + "  ||  "
                + board[1] + "  ||  " + board[2]
                + "  ||");
        System.out.println("||=====||=====||=====||");
        System.out.println("||  " + board[3] + "  ||  "
                + board[4] + "  ||  " + board[5]
                + "  ||");
        System.out.println("||=====||=====||=====||");
        System.out.println("||  " + board[6] + "  ||  "
                + board[7] + "  ||  " + board[8]
                + "  ||");
        System.out.println("||=====||=====||=====||");
    }

    // resets board array to initial values
    public void eraseBoard() {
        setWinner(null);
        setCurrentPlayer(getX());
        for (int i = 0; i < board.length; i++) {
            board[i] = String.valueOf(i + 1);
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

    public String[] getBoard() {
        return board;
    }

    public String getX() {
        return this.x;
    }

    public String getO() {
        return this.o;
    }

    public String getXWin() {
        return xWin;
    }

    public String getOWin() {
        return oWin;
    }
}