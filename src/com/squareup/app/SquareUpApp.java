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
            String squareNumber = input.nextLine();
            validateInput(squareNumber);
            Console.clear();
            showBoard();
            checkGameStatus();
            updateGame();
            nextTurn();
            showBoard();
        }


        announceWinner();
    }


    private void promptForUsername() {
        System.out.println("Player 1, enter your name: ");
        String p1 = input.nextLine();
        //String player1 = prompter.prompt("Player 1 name: ");
        //this.player1 = player1;

        System.out.println("Player 2, enter your name: ");
        String p2 = input.nextLine();
        //String player2 = prompter.prompt("Player 2 name: ");
        //this.player1 = player2;

        // Keep track of next turn
        boolean isPlayer1 = true;

        // Keep track of player characters
        char symbol = ' ';
        if (isPlayer1) {
            symbol = 'X';
        } else {
            symbol = 'O';
        }

        if (isPlayer1) {
            System.out.println(p1 + "'s Turn (x): ");
        } else {
            System.out.println(p2 + "'s Turn (o): ");
        }

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
        if (checkWinner() != null) {
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
        Console.blankLines(1);
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