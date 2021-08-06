public class Game {
    private Player[] players;
    private final Settings settings = new Settings();
    private Board gameBoard = new Board(settings);
    private static boolean victory = false;

    public void defineBoardRules() {
//        Inside this method user sets size of the board and tags needed to win the round.
        System.out.println("Enter size of the board sides:\n" +
                "(number in range from 3 to 9)");
        while (true) {
            gameBoard.setSizeOfTheBoard();
            if (settings.getBoardSize() < 3 || settings.getBoardSize() > 9) {
                System.out.println("Wrong number! Please try again.");
            } else if (settings.getBoardSize() > 3) {
                System.out.println("Choose how many tags in a row do player need to win the round:\n" +
                        "(number in range from 3 to " + settings.getBoardSize() + ")");
                settings.setTagsToVictory();
                break;
            } else {
                settings.setTagsToVictory(3);
                break;
            }
        }
    }

    public void setWonRoundsNeeded() {
//        Set number of won rounds needed to win the whole game.
        System.out.println("Please enter number of won rounds needed to achieve victory:");
        settings.setPointsToVictory(Integer.parseInt(Run.exitOrReturnInput()));
    }

    public void gamePlay(Player[] players) {

        for (Player player : players) {
            gameTurn(player.getName(), player.getTag());
            if (gameBoard.checkIfVictory()) {
                player.addPoints(1);
                System.out.println(player.getName() + " won this round!");
                for (Player p : players) {
                    System.out.println(p.getName() + " rounds won: " + p.getPoints());
                    gameBoard = new Board(settings);
                }
                if (player.getPoints() >= settings.getPointsToVictory()) {
                    System.out.println("Victory!\n" + player.getName() + " wins!");
                    setVictory(true);
                    break;
                } else {
                    System.out.println("\n<( It's next round ! )>");
                    gameBoard.displayBoard();
                }
            }
        }
    }

    public Player[] setAndGetNumberOfPlayers() {
//        Set number of players.
        while (true) {
            try {
                return new Player[Integer.parseInt(Run.exitOrReturnInput())];
            } catch (NumberFormatException e) {
                System.out.println("Wrong data! Please try again.");
            }
        }
    }

    public void setPlayersNamesAndTags(Player[] players) {
//        Set name and tag for all of players.
        for (int i = 0; i < players.length; i++) {
            System.out.println("Player nr " + (i + 1) + ", please enter your name:");
            players[i] = new Player(Run.exitOrReturnInput(), playerTagSelection());
            System.out.println(players[i].getName() + ", your symbol is " + players[i].getTag() + ".\n");
        }
    }

    public String playerTagSelection() {
//       Sets tag and if it is only one character in length.
        System.out.println("Enter your tag:\n(use one character only)");
        while (true) {
            String playerTag = Run.exitOrReturnInput();
            if (playerTag.length() > 1) {
                System.out.println("Wrong tag! Use one character only.");
            } else {
                return playerTag;
            }
        }
    }

    public void gameTurn(String playerName, String playerTag) {
//        Player tags specified field if it's not already tagged.
        System.out.println("\nIt's " + playerName + "'s turn!");
        while (true) {
            int row = checkIfCorrectCoordinate("row");
            int column = checkIfCorrectCoordinate("column");
            if (gameBoard.getBoard()[row][column].equals(" ")) {
                gameBoard.getBoard()[row][column] = playerTag;
                break;
            } else {
                System.out.println("This field is already taken! Choose another one.");
            }

        }
        gameBoard.displayBoard();
    }

    public int checkIfCorrectCoordinate(String rowOrColumn) {
//        Checking if field coordinate given is correct.
        int coordinate;
        while (true) {
            System.out.println("Enter " + rowOrColumn + " number:");
            coordinate = Integer.parseInt(Run.exitOrReturnInput());
            if (coordinate < 1 || coordinate > settings.getBoardSize()) {
                System.out.println("Incorrect number! Try again.");
            } else {
                break;
            }
        }
        return coordinate;
    }

    public Board getGameBoard() {
        return gameBoard;
    }

    public Settings getSettings() {
        return settings;
    }

    public static boolean isVictory() {
        return victory;
    }

    public void setGameBoard(Board gameBoard) {
        this.gameBoard = gameBoard;
    }

    public static void setVictory(boolean isVictory) {
        Game.victory = isVictory;
    }

    public Player[] getPlayers() {
        return players;
    }

    public void setPlayers(Player[] players) {
        this.players = players;
    }
}