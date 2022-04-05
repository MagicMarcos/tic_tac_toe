package com.squareup.app;

import com.squareup.Board;
import java.util.Scanner;

public class SquareUpApp {

    private final Board board = Board.getInstance();


    public void execute() {
        // welcome banner
        welcome();
        // show board
        showBoard();
        // nextTurn
        board.run();
        // showboard
        showBoard();
    }

    private void showBoard(){
        board.printGameBoard();
    }

    private void welcome() {
        System.out.println("\n");
        System.out.println("W E L C O M E T O T H E S Q U A R E U P A P P L I C A T I O N");
        System.out.println("\n\n");
    }
}