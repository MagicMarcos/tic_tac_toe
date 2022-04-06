package com.squareup.app;


import com.apps.util.Console;
import com.apps.util.Prompter;
import com.squareup.Board;
import com.squareup.Player;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;


// does ALL prompting (for game and names) -- two private player objs if you have classes
public class SquareUpApp {
    private Prompter prompter;
    private final Board board = Board.getInstance();
    private boolean gameOver;

    // TODO: delete this garbage
    private int wins;
    private Player playerX;
    private Player playerO;

    public SquareUpApp(Prompter prompter) {
        this.prompter = prompter;
    }

    public void execute() throws IOException {

        // welcome banner
        welcome();

        // are you a new or returning player?
        // if new -> name -> add new ID (increment ID)
        // separate file with player cound/ IDs

        promptForUsername();
        // if returning -> playerId


        run();
    }

    private void run() throws IOException {
        showBoard();

        while (!gameOver) {

            validateInput();

            Console.clear();

            showBoard();

            checkGameStatus();

            updateGame();

            nextTurn();

            showBoard();
        }

        announceWinner();

        // TODO: add option for fresh new game and rematch
        String playAgain = prompter.prompt("Would you like to play again? [Y]es/[N]o/[R]ematch ",
                "(?i)Y|N|R", "Please enter 'Y', 'R', or 'N'");

        if ("Y".equalsIgnoreCase(playAgain)) {
            gameOver = false;
            board.eraseBoard();
            execute();
        } else if ("R".equalsIgnoreCase(playAgain)) {
            gameOver = false;
            board.eraseBoard();
            run();
        } else {
            gameOver();

        }
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
        board.printWinner();
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

    private void welcome() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/welcome-banner.txt"));
        prompter.info(banner);
        Console.blankLines(1);
    }

    private void gameOver() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/thankyou.txt"));
        prompter.info(banner);
        Console.blankLines(1);
    }

    private void validateInput() {
        Console.blankLines(1);
        String player = currentTurn();
        int squareNum = Integer.parseInt(prompter.prompt(player,
                "1|2|3|4|5|6|7|8|9", "Please enter a valid input. [1-9]"));
        board.claimSquare(squareNum);

    }

    // ACCESSOR METHODS
    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }
}