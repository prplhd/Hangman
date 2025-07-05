package main.java.hangman;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static main.java.hangman.HangmanGameEngine.getUsedLetters;

public class SecretWordManager {
    static final char HIDDEN_LETTER_SYMBOL = '▯';
    private final String secretWord = chooseRandomSecretWord();
    private final List<Character> secretWordMask = createMask();

    public List<Character> getSecretWordMask() {
        return secretWordMask;
    }

    List<String> loadDictionary() {
        List<String> dictionary = new ArrayList<>();
        String pathToDictionary = "dictionary.txt";
        try (BufferedReader reader = new BufferedReader(new FileReader(pathToDictionary));) {
            String line;
            while ((line = reader.readLine()) != null) {
                dictionary.add(line);
            }
        } catch (IOException e) {
            System.out.println("Ошибка! Проверьте доступность словаря в корневой папке проекта для начала игры:");
            System.out.println("../Hangman/*здесь должен лежать dictionary.txt*");
            System.exit(0);
        }

        return dictionary;
    }

    String chooseRandomSecretWord() {
        List<String> wordsPool = loadDictionary();
        int randomIndex = new Random().nextInt(wordsPool.size());
        return wordsPool.get(randomIndex);
    }

    List<Character> createMask() {
        List<Character> wordMask = new ArrayList<>();
        for (int i = 0; i < secretWord.length(); i++) {
            wordMask.add(HIDDEN_LETTER_SYMBOL);
        }
        return wordMask;
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

    void processLetterGuess(Character inputLetter) {
        boolean isOpenedLetter = secretWordMask.contains(inputLetter);
        boolean isUsedLetter = getUsedLetters().contains(inputLetter);

        if (isOpenedLetter || isUsedLetter) {
            System.out.println("\nВы уже вводили эту букву, попробуйте другую");
        } else if (containsLetter(inputLetter)) {
            System.out.println("\nВерно! Эта буква есть в слове");
            openLetter(inputLetter);
        } else {
            processIncorrectGuess(inputLetter);
        }
    }

    void processIncorrectGuess(char inputLetter) {
            HangmanGameEngine.increaseMistakesCount();
            System.out.println("\nВы допустили ошибку!");
            getUsedLetters().add(inputLetter);
    }

    void openLetter(Character letter) {
        for (int i = 0; i < secretWordMask.size(); i++) {
            if (secretWord.charAt(i) == letter) {
                secretWordMask.set(i, letter);

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

    boolean isGuessedLetterValid(String s) {
        return s != null && s.length() == 1 && isRussianLetter(s);
    }

    boolean isRussianLetter(String s) {
        char letter = s.toLowerCase().charAt(0);
        return (letter >= 'а' && letter <= 'я') || letter == 'ё';
    }

    char normalizeLetter(char letter) {
        return letter == 'ё' ? 'е' : letter;
    }
}