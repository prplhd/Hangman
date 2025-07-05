package main.java.hangman;

public class HangmanStages {
    private static final String[] hangmanImages = new String[]{
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

    private static final String savedHangman = """
                   
                   ‾\\ (◕‿◕) /‾
                     ‾‾‾|‾‾‾
                        |
                       / \\
                      /   \\""";

    static String getHangmanStage(int mistakesCount) {
        return hangmanImages[mistakesCount];
    }

    static String getSavedHangman() {
        return savedHangman;
    }
}
