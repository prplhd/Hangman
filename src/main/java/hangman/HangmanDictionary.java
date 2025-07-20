package main.java.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HangmanDictionary {
    public static final String PATH_TO_DICTIONARY = "dictionary.txt";
    private final List<String> masterDictionary;
    private List<String> gameDictionary;

    public HangmanDictionary() throws IOException {
        this.masterDictionary = loadDictionary();
        this.gameDictionary = new ArrayList<>(masterDictionary);

    }

    public List<String> loadDictionary() throws IOException {
        List<String> dictionary = new ArrayList<>();

        try (BufferedReader reader = new BufferedReader(new FileReader(PATH_TO_DICTIONARY))) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
        }
        return dictionary;
    }

    public String pollRandomWord() {
        if (gameDictionary.isEmpty()) {
            gameDictionary = new ArrayList<>(masterDictionary);
        }

        int randomIndex = new Random().nextInt(gameDictionary.size());

        return gameDictionary.remove(randomIndex);
    }
}