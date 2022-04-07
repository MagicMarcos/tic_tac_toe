package com.squareup.app;

import com.apps.util.Prompter;
import com.squareup.Board;
import com.squareup.Player;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class SquareUpAppTest {
    private Player playerX;
    private Player playerO;
    private Board board;
    private SquareUpApp app;

    @Before
    public void setUp() {
        playerX = new Player("P1");
        playerO = new Player("P2");
        board = board.getInstance();
        app = new SquareUpApp(new Prompter(new Scanner(System.in)));
    }



    @Test
    public void playAgain() {

    }
}