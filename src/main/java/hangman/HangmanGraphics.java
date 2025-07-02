package main.java.hangman;

public class HangmanGraphics {
    static String[] hangmanImages = new String[]{
            "",

            """
                     
                     |//
                     ||
                     ||
                     ||
                     ||
                     ||
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||
                     ||
                     ||
                     ||
                     ||
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||       (×_×)
                     ||
                     ||
                     ||
                     ||
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||       (×_×)
                     ||         |
                     ||         |
                     ||
                     ||
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||       (×_×)
                     ||        /|
                     ||       / |
                     ||
                     ||
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||       (×_×)
                     ||        /|\\
                     ||       / | \\
                     ||
                     ||
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||       (×_×)
                     ||        /|\\
                     ||       / | \\
                     ||        /
                     ||       /
                     ||
                   __||__
                   """,

            """
                   
                     ============
                     |//        |
                     ||       (×_×)
                     ||        /|\\
                     ||       / | \\
                     ||        / \\
                     ||       /   \\
                     ||
                   __||__
                   """
    };

    static String savedHangman = """
                   
                   ‾\\ (◕‿◕) /‾
                     ‾‾‾|‾‾‾
                        |
                       / \\
                      /   \\""";

    static void displayHangmanStage(int mistakesCount) {
        System.out.println(hangmanImages[mistakesCount]);
    }

    static void displaySavedHangman() {
        System.out.println(savedHangman);
    }
}
