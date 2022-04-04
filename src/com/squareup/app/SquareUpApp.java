package com.squareup.app;

import com.squareup.Board;
import java.util.Scanner;

public class SquareUpApp {

    private final Board board = Board.getInstance();

    public void execute() {
        board.printGameBoard();
    }
    // Create user input interface from console
    Scanner input = new Scanner(System.in);

}