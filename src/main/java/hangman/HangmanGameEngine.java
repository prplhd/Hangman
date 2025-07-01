package main.java.hangman;

import java.util.Scanner;

public class HangmanGameEngine {
    static int mistakesCount = 0;
    static SecretWordManager secretWordManager;

    public static void welcomeUserToTheGame() {
        System.out.println("\nДобро пожаловать в Виселицу!");
        System.out.println("-----------------------------");
        System.out.println("Вам предстоит угадать загаданное слово и");
        System.out.println("спаси человечка, вводя одну букву за другой");
        System.out.println("У вас будет 8 попыток!");
        System.out.println("-----------------------------");
        startNewGame();
    }

    public static void startNewGame() {
        System.out.println("\nНачать новую игру? (Да/Нет)");

        Scanner scanner = new Scanner(System.in);
        String choice = scanner.nextLine().trim().toLowerCase();

        if (choice.equals("нет")) {
            System.out.println("Мы понимаем, не все готовы взять на себя");
            System.out.println("такую ответственность... До свидания (ಥ﹏ಥ)");
            return;
        }

        if (choice.equals("да")) {
            mistakesCount = 0;
            runGame();
        }
    }

    public static void runGame() {
        secretWordManager = new SecretWordManager();
        secretWordManager.setSecretWord();
        secretWordManager.createMask();

        while (!isGameOut()) {
            displayGameProcess();
            secretWordManager.isLetterGuessed(getGuessedLetter());

        }
    }

    static boolean isGameOut() {
        return mistakesCount >= 8;
    }

    static void displayGameProcess() {
        System.out.println("Угадываемое слово:");
        System.out.println(secretWordManager.secretWordMask);
        System.out.println("Человечка повесят через " + (8 - mistakesCount) + " ошибок");
        System.out.print("Введите букву: ");
    }

    static Character getGuessedLetter() {
        Scanner scanner = new Scanner(System.in);
        return scanner.next().toLowerCase().charAt(0);
    }

}