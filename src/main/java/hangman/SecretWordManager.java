package main.java.hangman;

import java.util.ArrayList;
import java.util.List;

public class SecretWordManager {

    public static final char HIDDEN_LETTER_SYMBOL = '▯';
    private final String secretWord;
    private final List<Character> secretWordMask;

    public SecretWordManager(HangmanDictionary hangmanDictionary){
        this.secretWord = hangmanDictionary.pollRandomWord();
        this.secretWordMask = createMask();
    }

    public List<Character> getSecretWordMask() {
        return secretWordMask;
    }

    List<Character> createMask() {
        List<Character> wordMask = new ArrayList<>();
        for (int i = 0; i < secretWord.length(); i++) {
            wordMask.add(HIDDEN_LETTER_SYMBOL);
        }
        return wordMask;
    }

    public void printSecretWordMask() {
        StringBuilder result = new StringBuilder();
        for (char ch : secretWordMask) {
            result.append(ch);
        }
        System.out.println(result);
    }

    public void printSecretWord() {
        System.out.println(secretWord);
    }

    public boolean isSecretWordLetter (char inputLetter) {
        for (int i = 0; i < secretWord.length(); i++) {
            if (inputLetter == secretWord.charAt(i)) {
                return true;
            }
        }
        return false;
    }

    public void openLetterInMask(char letter) {
        for (int i = 0; i < secretWordMask.size(); i++) {
            if (secretWord.charAt(i) == letter) {
                secretWordMask.set(i, letter);

            }
        }
    }
}