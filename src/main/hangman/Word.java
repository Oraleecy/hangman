package main.hangman;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Random;

public class Word {
    private final static String RESOURCES_PATH = "src/main/resources/";
    private final static String DICTIONARY_FILENAME = RESOURCES_PATH + "words.txt";
    private final static int WORDS_IN_DICTIONARY = 75;
    private final static Random random = new Random();

    private String word;

    Word() {}

    public void setWord() throws IOException {
        int wordAtLine = random.nextInt(1, WORDS_IN_DICTIONARY);

        try (BufferedReader br = new BufferedReader(new FileReader(DICTIONARY_FILENAME))) {
            String word = "";
            int countLine = 0;
            while (countLine != wordAtLine) {
                word = br.readLine();
                countLine++;
            }
            this.word = word;
        } catch (IOException e) {
            throw new FileNotFoundException("Файл не найден.");
        }
    }

    public String getWord() {
        return word;
    }
}
