package main.java.hangman;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.hangman.HangmanGameEngine.mistakesCount;

public class SecretWordManager {
    private String secretWord;
    ArrayList<Character> secretWordMask = new ArrayList<>();

    List<String> loadDictionary() {
        try {
            Path dictionaryPath = Paths.get(SecretWordManager.class.getResource("/hangman/dictionary.txt").toURI());
            return Files.readAllLines(dictionaryPath);
        } catch (Exception e) {
            return List.of();
        }
    }

    String chooseRandomSecretWord() {
        List<String> wordsPool = loadDictionary();
        int randomIndex = new Random().nextInt(wordsPool.size());
        return wordsPool.get(randomIndex);
    }

    void setSecretWord() {
        secretWord = chooseRandomSecretWord();
        System.out.println(secretWord);
    }

    void createMask() {
        for (int i = 0; i < secretWord.length(); i++) {
            secretWordMask.add('▯');
        }
        System.out.println(secretWordMask);
    }

    public void openLetter(Character inputLetter) {
        for (int i = 0; i < secretWordMask.size(); i++) {
            if (secretWord.charAt(i) == inputLetter) {
                secretWordMask.set(i, inputLetter);

            }
        }
    }

    void isLetterGuessed(Character inputLetter) {
        if (containsLetter(inputLetter)) {
            openLetter(inputLetter);
        } else {
            mistakesCount++;
            System.out.println("Вы допустили ошибку!");
        }
    }

    boolean containsLetter (Character inputLetter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (inputLetter == secretWord.charAt(i)) {
                return true;
            }
        }
        return false;
    }
}