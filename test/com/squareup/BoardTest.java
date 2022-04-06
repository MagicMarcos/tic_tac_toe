package com.squareup;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class BoardTest {
    private Board board1;

    @Before
    public void setUp() {
        board1 = Board.getInstance();
    }

    @Test
    public void checkResult_shouldReturnX_whenXWins() {
        board1.claimSquare(3);
        board1.claimSquare(5);
        board1.claimSquare(7);
        assertEquals(board1.checkResult(), "X");
    }

    @Test
    public void checkResult_shouldReturnO_whenOWins() {
        board1.setCurrentPlayer("O");
        board1.claimSquare(3);
        board1.claimSquare(5);
        board1.claimSquare(7);
        assertEquals(board1.checkResult(), "O");
    }

    @Test
    public void checkResult_shouldReturnDraw_whenGameTied() {
        board1.claimSquare(1);
        board1.claimSquare(2);
        board1.claimSquare(6);
        board1.claimSquare(7);
        board1.claimSquare(9);
        board1.setCurrentPlayer("O");
        board1.claimSquare(3);
        board1.claimSquare(5);
        board1.claimSquare(4);
        board1.claimSquare(8);
        assertEquals(board1.checkResult(), "draw");
    }

    @Test
    public void checkResult_shouldReturnNull_whenGameIsOngoing() {
        assertEquals(board1.checkResult(), null);
    }

    @Test
    public void claimSquare_shouldClaimSquare_whenSquareNotClaimed() {
        board1.claimSquare(3);
        assertEquals(board1.getBoard()[2], "X");
    }

    @Test
    public void claimSquare_shouldReturnTrue_whenSquareNotClaimed() {
        assertTrue(board1.claimSquare(3));
    }

    @Test
    public void claimSquare_shouldReturnFalse_whenSquareClaimed() {
        board1.claimSquare(3);
        assertFalse(board1.claimSquare(3));
    }

    @Test
    public void changePlayers_shouldSetPlayerToO_whenCurrentPlayerIsX() {
        board1.changePlayers();
        assertEquals(board1.getCurrentPlayer(), "O");
    }

    @Test
    public void changePlayers_shouldSetPlayerToX_whenCurrentPlayerIsO() {
        board1.setCurrentPlayer("O");
        board1.changePlayers();
        assertEquals(board1.getCurrentPlayer(), "X");
    }

    @Test
    public void eraseBoard_shouldSetPlayerToX_whenBoardIsReset() {
        board1.setCurrentPlayer("O");
        board1.eraseBoard();
        assertEquals(board1.getCurrentPlayer(), "X");
    }

    @Test
    public void eraseBoard_shouldUpdateBoardValuesToDefault_whenBoardIsReset() {
        String[] board = {"1", "2", "3", "4", "5", "6", "7", "8", "9"};
        board1.claimSquare(5);
        board1.claimSquare(4);
        board1.claimSquare(8);
        board1.eraseBoard();
        assertArrayEquals(board1.getBoard(), board);
    }

    @Test
    public void eraseBoard_shouldSetPlayerToNull_whenBoardIsReset() {
        board1.setWinner("X");
        board1.eraseBoard();
        assertEquals(board1.getWinner(), null);
    }
}