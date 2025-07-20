package main.java.hangman;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import static main.java.hangman.HangmanStages.getHangmanStage;
import static main.java.hangman.HangmanStages.getSavedHangman;
import static main.java.hangman.SecretWordManager.HIDDEN_LETTER_SYMBOL;

public class HangmanGameEngine {
    private static final int MAX_MISTAKES = 8;
    private static final String START = "да";
    private static final String QUIT = "нет";
    private static final List<Character> wrongLetters = new ArrayList<>();
    private static int mistakesCount = 0;
    private static SecretWordManager secretWordManager;
    private static HangmanDictionary hangmanDictionary;

    public static void printWelcomeMessage() {
        System.out.println("\nДобро пожаловать в Виселицу!");
        System.out.println("-----------------------------");
        System.out.println("Вам предстоит угадать загаданное слово и");
        System.out.println("спаси человечка, вводя одну букву за другой");
        System.out.println("У вас будет " + MAX_MISTAKES + " попыток!");
        System.out.println("-----------------------------");
    }

    public static void runGameLifecycle() {
        printWelcomeMessage();

        while (true) {
            String choice;
            System.out.println("\nНачать новую игру? (" + START + "/" + QUIT + ")");
            Scanner scanner = new Scanner(System.in);
            choice = scanner.nextLine().trim().toLowerCase();

            if (choice.equals(START)) {
                mistakesCount = 0;
                wrongLetters.clear();
                try {
                    runGame();
                } catch (IOException _) {
                    printDictionaryLoadErrorMessage();
                    return;
                }
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

    public static void runGame() throws IOException {
        ensureDictionaryLoaded();
        secretWordManager = new SecretWordManager(hangmanDictionary);

        while (!isGameOver()) {
            printGameStatus();
            processPlayerTurn();

            if (isWin(secretWordManager.getSecretWordMask())) {
                displayWinMessage();
            } else if (isLose()) {
                printLossMessage();
            }
        }
    }

    public static void ensureDictionaryLoaded() throws IOException {
        if (hangmanDictionary == null) {
            hangmanDictionary = new HangmanDictionary();
        }
    }

    public static void printDictionaryLoadErrorMessage() {
        System.out.println("Ошибка чтения словаря! Проверьте доступность файла в корневой папке проекта:");
        System.out.println("../Hangman/*здесь должен лежать dictionary.txt*");
        System.out.println("После чего начните новую игру");
    }


    public static void printGameStatus() {
        System.out.println(getHangmanStage(mistakesCount));

        System.out.println("Угадываемое слово:");
        secretWordManager.printSecretWordMask();

        if (!wrongLetters.isEmpty()) {
            System.out.println("\nОшибочные буквы:");
            System.out.println(wrongLetters);
        }
    }

    public static void processPlayerTurn() {
        System.out.println(".................................");
        System.out.print("\nВведите букву: ");
        char letter = getValidInput();

        if (isUsedLetter(letter)) {
            System.out.println("\nВы уже вводили эту букву, попробуйте другую");
            return;
        }
        if (secretWordManager.isSecretWordLetter(letter)) {
            System.out.println("\nВерно! Эта буква есть в слове");
            secretWordManager.openLetterInMask(letter);
            return;
        }
        System.out.println("\nВы допустили ошибку! Этой буквы нет в слове");
        mistakesCount++;
        wrongLetters.add(letter);
    }

    public static char getValidInput() {
        Scanner scanner = new Scanner(System.in);
        while (true) {
            String input = scanner.next().trim().toLowerCase();
            if (input.length() == 1 && isRussianLetter(input)) {
                return unifyRussianE(input.charAt(0));
            }
            System.out.println("Некорректный ввод! Пожалуйста, введите одну букву русского алфавита.");
        }
    }

    public static boolean isRussianLetter(String s) {
        char letter = s.toLowerCase().charAt(0);
        return (letter >= 'а' && letter <= 'я') || letter == 'ё';
    }

    public static char unifyRussianE(char letter) {
        return letter == 'ё' ? 'е' : letter;
    }

    public static boolean isUsedLetter(char letter) {
        boolean isOpenedLetter = secretWordManager.getSecretWordMask().contains(letter);
        boolean isWrongLetter = wrongLetters.contains(letter);
        return isOpenedLetter || isWrongLetter;
    }

    public static boolean isGameOver() {
        return isWin(secretWordManager.getSecretWordMask()) || isLose();
    }

    public static boolean isWin(List<Character> secretWordMask) {
        return !secretWordMask.contains(HIDDEN_LETTER_SYMBOL);
    }

    public static void displayWinMessage() {
        System.out.println("\nУра! Вы спасли человечка!");
        System.out.print("Загаданное слово: ");
        secretWordManager.printSecretWord();
        System.out.println(getSavedHangman());
    }

    public static boolean isLose() {
        return mistakesCount >= MAX_MISTAKES;
    }

    public static void printLossMessage() {
        System.out.println("К сожалению, Вам не удалось спасти человечка..");
        System.out.print("Загаданное слово: ");
        secretWordManager.printSecretWord();
        System.out.println(getHangmanStage(mistakesCount));
    }
}