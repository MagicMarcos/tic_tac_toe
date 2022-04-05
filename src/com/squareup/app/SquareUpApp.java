package com.squareup.app;


import com.apps.util.Console;
import com.apps.util.Prompter;
import com.apps.util.SplashApp;
import com.squareup.Board;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.Scanner;

// does ALL prompting (for game and names) -- two private player objs if you have classes
public class SquareUpApp {
    private Console console;
    private Prompter prompter;
    private SplashApp splashApp;
    private String username;
    private final Board board = Board.getInstance();
    private boolean gameOver;

    private Scanner input = new Scanner(System.in);

    public SquareUpApp(Prompter prompter) {
        this.prompter = prompter;
    }

    public void execute() throws IOException {

        // welcome banner
        welcome();

        promptForUsername();
        Console.clear();
        // show board
        showBoard();

        while (!gameOver) {
            String squareNumber = input.nextLine();
            validateInput(squareNumber);
            Console.clear();
            showBoard();
            checkGameStatus();
            updateGame();
            nextTurn();
        }

        announceWinner();
    }

    private void promptForUsername() {
        String username = prompter.prompt("Please enter your name: ");
        this.username = username;
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

    public void updateGame() {
        if(checkWinner() != null) {
            setGameOver(true);
        }
    }

    private String checkWinner() {
        return board.getWinner();
    }

    private void announceWinner() {
        board.printWinner();
    }

    private void welcome() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/welcome_banner.txt"));
        prompter.info(banner);
        Console.blankLines(2);
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

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

}