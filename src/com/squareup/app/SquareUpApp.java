package com.squareup.app;

import com.squareup.Board;

public class SquareUpApp {
    private final Board board = Board.getInstance();
    public void execute() {
        board.printGameBoard();
    }

}