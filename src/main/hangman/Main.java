package main.hangman;

import java.io.IOException;
import java.util.Scanner;


public class Main {
    public static void main(String[] args) throws IOException {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            HangmanEnum hangmanEnum = HangmanEnum.BASE;
            CLI cli = new CLI();
            Word word = new Word();
            word.setWord();
            Game game = new Game(cli, word, scanner, hangmanEnum);
            game.startGame();
        }
    }
}
