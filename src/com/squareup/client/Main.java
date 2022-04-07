package com.squareup.client;

import com.apps.util.Prompter;
import com.squareup.app.SquareUpApp;

import java.io.IOException;
import java.util.Scanner;

class Main {

    public static void main(String[] args) throws IOException {
        SquareUpApp app = new SquareUpApp(new Prompter(new Scanner(System.in)));
        try {
            app.execute();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}