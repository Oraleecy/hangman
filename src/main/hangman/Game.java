package main.hangman;

import java.io.IOException;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

class Game {
    private final static int MAX_ERRORS = 6;

    private final Scanner scanner;
    private final CLI cli;
    private final Word word;

    private char[] currentWord;
    private static char[] displayWord;
    private int countClosedLetters;
    private int countErrors;
    private Set<Character> usedLetters;
    private char curChar;


    private HangmanEnum hangmanEnum;

    Game(CLI cli, Word word, Scanner scanner, HangmanEnum hangmanEnum) {
        this.cli = cli;
        this.word = word;
        this.scanner = scanner;
        this.hangmanEnum = hangmanEnum;
        currentWord = new char[word.getWord().length()];
        displayWord = new char[word.getWord().length()];
        countClosedLetters = word.getWord().length();

        usedLetters = new HashSet<>();
    }

    void startGame() throws IOException {
        startMenuLoop();

        for (int i = 0; i < word.getWord().length(); i++) {
            currentWord[i] = word.getWord().charAt(i);
        }
        Arrays.fill(displayWord, '-');
        boolean winStatus = gameLoop();
        cli.endGameOutput(winStatus, hangmanEnum.getFilename());
    }

    void startMenuLoop() {
        char inputCh = 0;
        while (inputCh != 'С') {
            cli.startOutputMenu();
            String line = scanner.nextLine();
            if (line.length() > 1) {
                cli.incorrectInput(0);
                continue;
            }
            inputCh = line.charAt(0);
            if (inputCh == 'В') {
                System.exit(0);
            }
        }
    }

    /*
    return true if player win;
     */
    boolean gameLoop() throws IOException {
        String line;
        while (countErrors < MAX_ERRORS && countClosedLetters > 0) {
            cli.outputCurrentStage(hangmanEnum.getFilename());
            cli.outputCurrentWord(Arrays.toString(displayWord).toString());
            line = editInput(scanner.nextLine());
            if(!checkCorrectInput(line)) {
                cli.incorrectInput(2);
                continue;
            }

            char ch = line.charAt(0);
            if(usedLetters.contains(ch)) {
                cli.incorrectInput(1);
                continue;
            }
            if(!openLetter(ch)) {
                countErrors++;
                hangmanEnum = hangmanEnum.getNextStage();
            }
            usedLetters.add(ch);
        }
        return countErrors != MAX_ERRORS;
    }

    boolean checkCorrectInput(String line) {
        return line.length() == 1 &&
                ((line.charAt(0) >= 'А' && line.charAt(0) <= 'Я') || (line.charAt(0) >= 'а' && line.charAt(0) <= 'я'));
    }

    private String editInput(String input) {
        return input.toUpperCase();
    }

    private boolean openLetter(char letter) {
        boolean opened = false;
        for (int i = 0; i < currentWord.length; i++) {
            if (currentWord[i] == letter) {
                opened = true;
                countClosedLetters--;
                displayWord[i] = letter;
            }
        }
        return opened;
    }

    public char[] getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(char[] currentWord) {
        this.currentWord = currentWord;
    }

    public char[] getDisplayWord() {
        return displayWord;
    }

    public void setDisplayWord(char[] displayWord) {
        this.displayWord = displayWord;
    }


    public int getCountErrors() {
        return countErrors;
    }

    public void setCountErrors(int countErrors) {
        this.countErrors = countErrors;
    }


    public Set<Character> getUsedLetters() {
        return usedLetters;
    }

    public void setUsedLetters(Set<Character> usedLetters) {
        this.usedLetters = usedLetters;
    }

    public char getCurChar() {
        return curChar;
    }

    public void setCurChar(char curChar) {
        this.curChar = curChar;
    }
}
