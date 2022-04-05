package com.squareup.app;

import com.squareup.Board;

import java.util.Scanner;

// does ALL prompting (for game and names) -- two private player objs if you have classes
public class SquareUpApp {

    private final Board board = Board.getInstance();
    private String gameOver = null;

    private Scanner input = new Scanner(System.in);

    public void execute() {

        // welcome banner
        welcome();

        // show board
        showBoard();

        while (gameOver == null) {
            String squareNumber = input.nextLine();
            validateInput(squareNumber);
            showBoard();
            checkGameStatus();
            setGameOver(checkWinner());
            nextTurn();
        }

        announceWinner();
    }

    private void showBoard() {
        board.show();
    }

    private void nextTurn() {
        board.changePlayers();
    }

    private void checkGameStatus() {
        board.checkResult();
    }

    private String checkWinner() {
        return board.getWinner();
    }

    private void announceWinner() {
        board.printWinner();
    }

    private void welcome() {
        System.out.println("\n");
        System.out.println("W E L C O M E T O T H E S Q U A R E U P A P P L I C A T I O N");
        System.out.println("\n\n");
    }

    private void validateInput(String input) {
        if (input.matches("1|2|3|4|5|6|7|8|9")) {// -> validates the input
            int squareNum = Integer.parseInt(input);
            board.claimSquare(squareNum);
        } else {
            System.out.println("Please enter a valid input. [1-9]");
        }
    }

    // ACCESSOR METHODS

    public void setGameOver(String gameOver) {
        this.gameOver = gameOver;
    }
}