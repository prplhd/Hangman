package main.java.hangman;

import java.util.List;
import java.util.Scanner;

public class HangmanGameEngine {
    static final String START = "да";
    static final String QUIT = "нет";
    static final int MAX_MISTAKES = 8;

    static int mistakesCount = 0;
    static SecretWordManager secretWordManager;

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

            System.out.print("\nНекорректный выбор. Пожалуйста, введите \"" + START + "\" или \"" + QUIT + "\"\n");
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
        secretWordManager.checkGuessedLetter(getGuessedLetter());
    }

    static void displayGameProcess() {
        HangmanGraphics.displayHangmanStage(mistakesCount);
        System.out.println("Угадываемое слово:");
        secretWordManager.displaySecretWordMask();
        if (!secretWordManager.getUsedLetters().isEmpty()) {
            System.out.println("\nОшибочные буквы:");
            secretWordManager.displayUsedLetters();
        }
    }

    static boolean isGameOver() {
        return mistakesCount >= MAX_MISTAKES;
    }

    static void displayLossMessage() {
        System.out.println("К сожалению, Вам не удалось спасти человечка..");
        System.out.print("Загаданное слово: ");
        secretWordManager.displaySecretWord();
        HangmanGraphics.displayHangmanStage(mistakesCount);
    }

    static boolean isWin(List<Character> secretWordMask) {
        return !secretWordMask.contains('▯');
    }

    static void displayWinMessage() {
        System.out.println("\nУра! Вы спасли человечка!");
        System.out.print("Загаданное слово: ");
        secretWordManager.displaySecretWord();
        HangmanGraphics.displaySavedHangman();
    }

    static Character getGuessedLetter() {

        Scanner scanner = new Scanner(System.in);
        while (true) {
            String guessedLetter = scanner.next().trim().toLowerCase();

            if (secretWordManager.isGuessedLetterValid(guessedLetter)) {
                return secretWordManager.normalizeLetter(guessedLetter);
            }

            System.out.println("Некорректный ввод! Пожалуйста, введите одну букву русского алфавита.");
        }
    }



    static void increaseMistakesCount() {
        mistakesCount++;
    }
}