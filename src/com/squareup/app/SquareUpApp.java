package com.squareup.app;

import com.squareup.Board;
import java.util.Scanner;

public class SquareUpApp {

    private final Board board = Board.getInstance();
    private Scanner input = new Scanner(System.in);

    public void execute() {
        // welcome banner
        welcome();
        //show board
        showBoard();
        // nextTurn
        //showboard
        showBoard();
    }

    private void showBoard(){
        board.printGameBoard();

    }

    private void welcome() {
        System.out.println("\n");
        System.out.println("\033[31mW E L C O M E  T O  T H E  S Q U A R E U P  A P P L I C A T I O N!!!\033[0m");
        System.out.println("\n\n");
    }
}