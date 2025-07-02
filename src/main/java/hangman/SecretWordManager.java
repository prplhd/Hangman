package main.java.hangman;

import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class SecretWordManager {
    private String secretWord;
    ArrayList<Character> secretWordMask = new ArrayList<>();
    ArrayList<Character> usedLetters = new ArrayList<>();

    List<String> loadDictionary() {
        try {
            Path dictionaryPath = Paths.get(SecretWordManager.class.getResource("/hangman/dictionary.txt").toURI());
            return Files.readAllLines(dictionaryPath);
        } catch (Exception e) {
            System.out.println("Ошибка! Проверьте доступность словаря для начала игры");
            System.exit(0);
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
    }

    void createMask() {
        for (int i = 0; i < secretWord.length(); i++) {
            secretWordMask.add('▯');
        }
    }

    void displaySecretWordMask() {
        StringBuilder result = new StringBuilder();
        for (char ch : secretWordMask) {
            result.append(ch);
        }
        System.out.println(result);
    }

    void displaySecretWord() {
        System.out.println(secretWord);
    }

    void displayUsedLetters() {
        System.out.println(usedLetters);
    }

    void checkGuessedLetter(Character inputLetter) {
        boolean isLetterOpened = secretWordMask.contains(inputLetter);
        boolean isLetterTried = usedLetters.contains(inputLetter);

        if (isLetterOpened || isLetterTried) {
            System.out.println("\nВы уже вводили эту букву, попробуйте другую");
        } else if (containsLetter(inputLetter)) {
            System.out.println("\nВерно! Эта буква есть в слове");
            openLetter(inputLetter);
        } else {
            letterIsNotGuessed(inputLetter);
        }
    }

    void letterIsNotGuessed(Character inputLetter) {
            HangmanGameEngine.increaseMistakesCount();
            System.out.println("\nВы допустили ошибку!");
            usedLetters.add(inputLetter);
    }

    void openLetter(Character inputLetter) {
        for (int i = 0; i < secretWordMask.size(); i++) {
            if (secretWord.charAt(i) == inputLetter) {
                secretWordMask.set(i, inputLetter);

            }
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

    boolean isGuessedLetterValid(String guessedLetter) {
        return guessedLetter != null && guessedLetter.length() == 1 && isRussianLetter(guessedLetter);
    }

    boolean isRussianLetter(String guessedLetter) {
        char russianLetter = guessedLetter.toLowerCase().charAt(0);
        return (russianLetter >= 'а' && russianLetter <= 'я') || russianLetter == 'ё';
    }

    char normalizeLetter(String guessedLetter) {
        return guessedLetter.charAt(0) == 'ё' ? 'е' : guessedLetter.charAt(0);
    }
}