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
    private final Board board = Board.getInstance();
    private boolean gameOver;
    private String player1;
    private String player2;

    private Scanner input = new Scanner(System.in);


    public SquareUpApp(Prompter prompter) {
        this.prompter = prompter;
    }


    public void execute() throws IOException {


    // welcome banner
        welcome();

        promptForUsername();

        showBoard();


        while (!gameOver) {

//            String squareNumber = input.nextLine();
            validateInput();

            Console.clear();

            showBoard();
            checkGameStatus();
            updateGame();
            nextTurn();
            showBoard();
        }

        announceWinner();
    }

    private String currentTurn() {

        String player = "X".equals(board.getCurrentPlayer()) ? player1 : player2;

        return player + ", it is your turn: ";
    }

    private void promptForUsername() {
        this.player1 = prompter.prompt("Player X name: ");
        this.player2 = prompter.prompt("Player O name: ");
    }

    private void showBoard() {
        Console.blankLines(1);
        board.show();
    }

    private void nextTurn() {
        board.changePlayers();
    }

    private void checkGameStatus() {
        board.checkResult();
    }

    public void updateGame() {
        if (checkWinner() != null) {
            setGameOver(true);
        }
    }

    private String checkWinner() {
        return board.getWinner();
    }

    private void announceWinner() {
        Console.clear();
        board.printWinner();
    }

    private void welcome() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/welcome_banner.txt"));
        prompter.info(banner);
        Console.blankLines(1);
    }

    private void validateInput() {
            Console.blankLines(1);
            String player = currentTurn();
            int squareNum = Integer.parseInt(prompter.prompt(player, "1|2|3|4|5|6|7|8|9", "Please enter a valid input. [1-9]" ));
            board.claimSquare(squareNum);

    }

// ACCESSOR METHODS
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}