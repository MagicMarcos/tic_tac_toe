package com.squareup.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.squareup.Board;
import com.squareup.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/**
 * Controller class
 */
public class SquareUpApp {
    private String x = "\033[96m" + "X" + "\033[0m";
    private String o = "\033[95m" + "O" + "\033[0m";
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
        prompter.prompt("\033[32mPress [enter] to start... \033[0m\n");
        promptForUsername();
        run();
    }

    private void welcome() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/welcome-banner.txt"));
        prompter.info(banner);
        Console.blankLines(1);
    }

    private void howToPlay() {
        prompter.info("\033[4;31m¶ How to play:\033[0m\n" +
                "   • The game is played on a 3x3 grid.\n" +
                "   • Players take turns putting their marks (X or O) in empty squares.\n" +
                "   • The first player to get 3 of her marks in a row (up, down, across, " +
                "or diagonally) is the winner.\n" +
                "   • If all 9 squares are full, the game is over.\n");
    }

    private void promptForUsername() {
        this.playerX = new Player(prompter.prompt("Player X name: "));
        this.playerO = new Player(prompter.prompt("Player O name: "));
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

    private void showBoard() {
        Console.blankLines(1);
        board.show();
    }

    private void validateInput() throws IOException {
        Console.blankLines(1);
        String player = currentTurn();
        int squareNum = Integer.parseInt(prompter.prompt(player,
                "1|2|3|4|5|6|7|8|9", "\033[31mPlease enter a valid input. [1-9]\033[0m"));

        if (!board.claimSquare(squareNum)) {
            Console.clear();
            prompter.info("\033[31mThat square is already claimed! Choose another!\033[0m");
            run();
        }
    }

    private String currentTurn() {
        String player = getX().equals(board.getCurrentPlayer()) ? playerX.toString() : playerO.toString();
        return player + ", it is your turn: ";
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

    private void nextTurn() {
        board.changePlayers();
    }

    private void announceWinner() {
        updateScore();
        Console.clear();
        Console.blankLines(1);
        String player = getX().equals(board.getCurrentPlayer()) ? playerX.getName() : playerO.getName();
        String result = "draw".equals(board.getWinner()) ? "\033[33mIt's a draw!\033[0m" : "\033[32m" + player + " wins!\033[0m";
        prompter.info(result);
    }

    private void updateScore() {
        if (getX().equals(board.checkResult())) {
            playerX.setWin();
            playerO.setLoss();
        } else if (getO().equals(board.checkResult())) {
            playerO.setWin();
            playerX.setLoss();
        } else {
            playerX.setDraw();
            playerO.setDraw();
        }
    }

    private void playAgain() throws IOException {
        Console.blankLines(1);
        String playAgain = prompter.prompt("Would you like to play again? \033[32m[Y]es\033[0m/\033[93m[R]ematch\033[0m/\033[31m[N]o\033[0m ",
                "(?i)Y|N|R", "\033[31mPlease enter 'Y', 'R', or 'N'\033[0m");

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

    // ACCESSOR METHODS
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public String getX() {
        return x;
    }

    public String getO() {
        return o;
    }
}