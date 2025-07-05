package main.java.hangman;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static main.java.hangman.SecretWordManager.HIDDEN_LETTER_SYMBOL;
import static main.java.hangman.HangmanStages.*;

public class HangmanGameEngine {
    private static final int MAX_MISTAKES = 8;
    private static int mistakesCount = 0;
    private static final String START = "да";
    private static final String QUIT = "нет";
    private static SecretWordManager secretWordManager;
    private static final List<Character> usedLetters = new ArrayList<>();

    public static void welcomeUserToTheGame() {
        System.out.println("\nДобро пожаловать в Виселицу!");
        System.out.println("-----------------------------");
        System.out.println("Вам предстоит угадать загаданное слово и");
        System.out.println("спаси человечка, вводя одну букву за другой");
        System.out.println("У вас будет " + MAX_MISTAKES + " попыток!");
        System.out.println("-----------------------------");
        startNewGame();
    }

    public static void startNewGame() {
        while (true) {
            String choice = "";
            System.out.println("\nНачать новую игру? (" + START + "/" + QUIT + ")");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals(START)) {
                mistakesCount = 0;
                runGame();
                continue;
            }

            if (choice.equals(QUIT)) {
                System.out.println("\nЖаль, ведь человечку больше некому");
                System.out.println("помочь... До свидания (ಥ﹏ಥ)");
                break;
            }

            System.out.print("\nНекорректный выбор. Пожалуйста, введите '" + START + "' или '" + QUIT + "'\n");
        }
    }

    static void runGame() {
        secretWordManager = new SecretWordManager();

        while (!isGameOver()) {
            if (isWin(secretWordManager.getSecretWordMask())) {
                displayWinMessage();
                return;
            }
            processNextGuess();
        }

        displayLossMessage();
    }

    static void processNextGuess() {
        displayGameProcess();
        System.out.println(".................................");
        System.out.print("\nВведите букву: ");
        secretWordManager.processLetterGuess(getGuessedLetter());
    }

    static void displayGameProcess() {
        System.out.println(getHangmanStage(mistakesCount));
        System.out.println("Угадываемое слово:");
        secretWordManager.displaySecretWordMask();
        if (!usedLetters.isEmpty()) {
            System.out.println("\nОшибочные буквы:");
            displayUsedLetters();
        }
    }

    static void displayUsedLetters() {
        System.out.println(usedLetters);
    }

    static List<Character> getUsedLetters() {
        return usedLetters;
    }

    static boolean isGameOver() {
        return mistakesCount >= MAX_MISTAKES;
    }

    static void displayLossMessage() {
        System.out.println("К сожалению, Вам не удалось спасти человечка..");
        System.out.print("Загаданное слово: ");
        secretWordManager.displaySecretWord();
        System.out.println(getHangmanStage(mistakesCount));
    }

    static boolean isWin(List<Character> secretWordMask) {
        return !secretWordMask.contains(HIDDEN_LETTER_SYMBOL);
    }

    static void displayWinMessage() {
        System.out.println("\nУра! Вы спасли человечка!");
        System.out.print("Загаданное слово: ");
        secretWordManager.displaySecretWord();
        System.out.println(getSavedHangman());
    }

    static Character getGuessedLetter() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String guessedLetter = scanner.next().trim().toLowerCase();

            if (secretWordManager.isGuessedLetterValid(guessedLetter)) {
                return secretWordManager.normalizeLetter(guessedLetter.charAt(0));
            }

            System.out.println("Некорректный ввод! Пожалуйста, введите одну букву русского алфавита.");
        }
    }



    static void increaseMistakesCount() {
        mistakesCount++;
    }
}