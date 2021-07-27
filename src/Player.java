public class Player {
    String name;
    String tag;
    int points;

    public Player(String name, String tag) {
        this.name = name;
        this.tag = tag;
    }

    public static String playerTagSelection() {
//        Checks if tag is only one character in length.
        System.out.println("Enter your tag:\n(use one character only)");
        while (true) {
            String playerTag = Game.exitOrReturnInput();
            if (playerTag.length() > 1) {
                System.out.println("Wrong tag! Use one character only.");
            } else {
                return playerTag;
            }
        }
    }

}
