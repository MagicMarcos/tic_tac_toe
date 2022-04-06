package com.squareup.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.squareup.Board;
import com.squareup.Player;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public class SquareUpApp {
    private Prompter prompter;
    private final Board board = Board.getInstance();
    private boolean gameOver;
    private Player playerX;
    private Player playerO;

    public SquareUpApp(Prompter prompter) {
        this.prompter = prompter;
    }

    public void execute() throws IOException {

        welcome();
        howToPlay();
        // TODO: make enter green - or whole sentence if not.
        prompter.prompt("Press [enter] to start\n");
        promptForUsername();
        run();
    }

    private void run() throws IOException {

        showBoard();
        while (!gameOver) {
            validateInput();

            Console.clear();

            checkGameStatus();
            updateGame();
            if (!gameOver) {
                nextTurn();
            }
            showBoard();
        }

        announceWinner();

        playAgain();
    }

    private void validateInput() throws IOException {
        Console.blankLines(1);
        String player = currentTurn();
        int squareNum = Integer.parseInt(prompter.prompt(player,
                "1|2|3|4|5|6|7|8|9", "Please enter a valid input. [1-9]"));

        if (!board.claimSquare(squareNum)) {
            Console.clear();
            prompter.info("That square is already claimed! Choose another!");
            run();
        }

    }

    private void welcome() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/welcome-banner.txt"));
        prompter.info(banner);
        Console.blankLines(1);
    }

    private String currentTurn() {

        String player = "X".equals(board.getCurrentPlayer()) ? playerX.toString() : playerO.toString();

        return player + ", it is your turn: ";
    }

    private void promptForUsername() {
        this.playerX = new Player(prompter.prompt("Player X name: "));
        this.playerO = new Player(prompter.prompt("Player O name: "));
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
        updateScore();
        Console.clear();
        String player = "X".equals(board.getCurrentPlayer()) ? playerX.getName() : playerO.getName();
        String result = "draw".equals(board.getWinner()) ? "It's a draw!" : player + " wins!";
        prompter.info(result);
    }

    private void updateScore() {
        if ("X".equals(board.checkResult())) {
            playerX.setWin();
            playerO.setLoss();
        } else if ("O".equals(board.checkResult())) {
            playerO.setWin();
            playerX.setLoss();
        } else {
            playerX.setDraw();
            playerO.setDraw();
        }
    }

    private void playAgain () throws IOException {
        String playAgain = prompter.prompt("Would you like to play again? [Y]es/[N]o/[R]ematch ",
                "(?i)Y|N|R", "Please enter 'Y', 'R', or 'N'");

        if ("Y".equalsIgnoreCase(playAgain)) {
            gameOver = false;
            board.eraseBoard();
            execute();
        } else if ("R".equalsIgnoreCase(playAgain)) {
            gameOver = false;
            board.eraseBoard();
            Console.clear();
            run();
        } else {
            gameOver();

        }
    }

    private void gameOver() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/thankyou.txt"));
        prompter.info(banner);
        Console.blankLines(1);
    }

    private void howToPlay () {
        prompter.info("¶ How to play:\n" +
                "   • The game is played on a 3x3 grid.\n" +
                "   • Players take turns putting their marks (X or O) in empty squares.\n" +
                "   • The first player to get 3 of her marks in a row (up, down, across, or diagonally) is the winner.\n" +
                "   • If all 9 squares are full, the game is over.\n");
    }

    // ACCESSOR METHODS
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}