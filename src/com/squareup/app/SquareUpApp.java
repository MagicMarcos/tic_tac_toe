package com.squareup.app;

import com.apps.util.Console;
import com.apps.util.Prompter;
import com.squareup.Board;
import static com.squareup.Colors.*;
import com.squareup.Player;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

/*
 * Controller class - handles all prompting, runs game and calls game board methods
 */
public class SquareUpApp {
    private String x = CYAN + "X" + RESET;
    private String o = MAGENTA + "O" + RESET;

    private Prompter prompter;
    private final Board board = Board.getInstance();
    private boolean gameOver;
    private Player playerX;
    private Player playerO;

    public SquareUpApp(Prompter prompter) {
        this.prompter = prompter;
    }

    /*
     * Initial game execution:
     *  -> displays welcome banner, instructions and promps for player names
     */
    public void execute() throws IOException {
        welcome();
        howToPlay();
        prompter.prompt(GREEN + "Press [enter] to start..." + RESET + "");
        promptForUsername();
        run();
    }

    private void welcome() throws IOException {
        Console.clear();
        String banner = Files.readString(Path.of("resources/welcome_banner.txt"));
        prompter.info(banner);
    }

    private void howToPlay() {
        prompter.info(RED_UNDERLINED + "$How to play:" + RESET + "\n" +
                "   *  The game is played on a 3x3 grid.\n" +
                "   *  Players take turns putting their marks (X or O) in empty squares using [1-9].\n" +
                "   *  The first player to get 3 of her marks in a row (up, down, across, " +
                "or diagonally) is the winner.\n" +
                "   *  If all 9 squares are full, the game is over.\n");
    }

    // Prompts for usernames and creates new Player objects
    private void promptForUsername() {
        this.playerX = new Player(prompter.prompt("Player X name: "));
        this.playerO = new Player(prompter.prompt("Player O name: "));
    }

    /*
     * Runs the core of the game
     *  -> displays board and continuously loops running core game functions until game ends
     *  -> on game end, announces winner and prompts players with options
     */
    private void run() throws IOException {
        showBoard();

        while (!gameOver) {
            validateInput();
            Console.clear();
            checkGameStatus();
            updateGame();

            // prevents game from displaying wrong winner by not calling nextTurn if game ends
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

    /*
     * Validates user input
     *  -> ensuring players are selecting correct inputs [1-9] and warning if players try to claim a claimed square
     *  -> logic for claimed square is done in Board
     */
    private void validateInput() throws IOException {
        Console.blankLines(1);
        String player = currentTurn();
        int squareNum = Integer.parseInt(prompter.prompt(player,
                "1|2|3|4|5|6|7|8|9", RED + "Please enter a valid input. [1-9]" + RESET));

        if (!board.claimSquare(squareNum)) {
            Console.clear();
            prompter.info(RED + "That square is already claimed! Choose another!" + RESET);
            run();
        }
    }

    private String currentTurn() {
        String player = getX().equals(board.getCurrentPlayer()) ?
                CYAN + playerX.toString() + RESET : MAGENTA + playerO.toString() + RESET;

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
        String result = "draw".equals(board.getWinner()) ?
                DARK_YELLOW + "It's a draw!" + RESET :
                GREEN + player + " wins!" + RESET;

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

     /*
      * Presents gamers with several options for New Game, Rematch or Game Over
      *  -> prompter handles validation through case-insensitive regex
      */
    private void playAgain() throws IOException {
        Console.blankLines(1);
        String playAgain= prompter.prompt("Would you like to play again? " +
                        GREEN + "[N]ew Game" + RESET + "/" + YELLOW +
                        "[R]ematch" + RESET + "/" + RED + "[E]xit " + RESET,
                "(?i)E|N|R", RED + "Please enter 'E', 'R', or 'N'" + RESET);

        if ("N".equalsIgnoreCase(playAgain)) {
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
        try {
            Console.clear();
            Console.blankLines(1);
            String banner = Files.readString(Path.of("resources/thankyou.txt"));
            prompter.info(banner);
            Console.blankLines(1);
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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