package main.hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;

public class CLI {
    private final static String RESOURCES_PATH = "src/main/resources/hangman_stages/";

    CLI() {
    }


    public void startOutputMenu() {
        System.out.println("Введите С для старта или В для выхода.");
    }

    public void incorrectInput(int status) {
        System.out.println("Неккоректный ввод.");
        if(status == 0) {
            startOutputMenu();
        } else if(status == 1) {
            System.out.println("Эта буква уже была.");
        } else {
            System.out.println("Введите букву из кириллицы");
        }
    }

    public void outputCurrentWord(String word) {
        System.out.println(word);
    }

    public void endGameOutput(boolean win, String filenameHangman) throws IOException {
        if(win) {
            System.out.println("Вы выиграли!");
        } else {
            outputCurrentStage(filenameHangman);
            System.out.println("Вы проиграли");
        }
    }

    public void outputCurrentStage(String filename) throws IOException {
        try (BufferedReader br = new BufferedReader(new FileReader(RESOURCES_PATH + filename))) {
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
            }
        } catch (IOException e) {
            throw new FileNotFoundException("Файл стадий виселицы не найден.");
        }
    }
}
