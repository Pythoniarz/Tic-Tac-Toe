import java.util.Scanner;

public class Game {
    static Board gameBoard;
    static Settings settings = new Settings();

    public static void main(String[] args) {

//        Initialization of game settings.

        System.out.println("\n# # # The TIC-TAC-TOE game # # #\n");
        System.out.println("(you can enter \"quit\" at any time during game to exit program)\n");
//        Creating players.
        System.out.println("Please enter number of players:");
        Player[] players = new Player[Integer.parseInt(exitOrReturnInput())];

        for (int i = 0; i < players.length; i++) {
            System.out.println("Player nr " + (i + 1) + ", please enter your name:");

            players[i] = new Player(exitOrReturnInput(), Player.playerTagSelection());
            System.out.println(players[i].name + ", your symbol is " + players[i].tag + ".\n");
        }

//        Defining, creating and displaying the game board.
        while (true) {
            System.out.println("Enter size of the board sides:\n" +
                    "(number in range from 3 to 9)");
            boolean isValueCorrect = false;
            while (!isValueCorrect) {
                try {
                    settings.boardSize = Integer.parseInt(exitOrReturnInput());
                    isValueCorrect = true;
                } catch (NumberFormatException e) {
                    System.out.println("Wrong number! Please try again.");
                }
            }

            if (settings.boardSize < 3 || settings.boardSize > 9) {
                System.out.println("Wrong number! Please try again.");
            } else if (settings.boardSize > 3) {
                System.out.println("Choose how many tags in a row do player need to win the round:\n" +
                        "(number in range from 3 to " + settings.boardSize + ")");
                settings.setTagsToVictory();
                break;
            } else {
                settings.tagsToVictory = 3;
                break;
            }
        } gameBoard = new Board(settings.boardSize);

        System.out.println("Please enter number of won rounds needed to achieve victory:");
        settings.pointsToVictory = Integer.parseInt(exitOrReturnInput());

        System.out.println("LET'S PLAY!");
        gameBoard.displayBoard();

//        Game play.

        boolean victory = false;
        while (!victory) {
            for (Player player : players) {
                gameTurn(player.name, player.tag);
                if (gameBoard.checkIfVictory()) {
                    ++player.points;
                    System.out.println(player.name + " won this round!");
                    for (Player p : players) {
                        System.out.println(p.name + " rounds won: " + p.points);
                        gameBoard = new Board(settings.boardSize);
                    }
                    if (player.points >= settings.pointsToVictory) {
                        System.out.println("Victory!\n" + player.name + " wins!");
                        victory = true;
                        break;
                    } else {
                        System.out.println("\n<( It's next round ! )>");
                        gameBoard.displayBoard();
                    }
                }
            }
        }
    }


    public static void gameTurn(String playerName, String playerTag) {
//        Player tags specified field if it's not already tagged.
        System.out.println("\nIt's " + playerName + "'s turn!");
        while (true) {
            int row = checkIfCorrectCoordinate("row");
            int column = checkIfCorrectCoordinate("column");
            if (gameBoard.board[row][column].equals(" ")) {
                gameBoard.board[row][column] = playerTag;
                break;
            } else {
                System.out.println("This field is already taken! Choose another one.");
            }

        }
        gameBoard.displayBoard();
    }

    public static int checkIfCorrectCoordinate(String rowOrColumn){
//        Checking if field coordinate given is correct.
        int coordinate;
        while (true) {
            System.out.println("Enter " + rowOrColumn + " number:");
            coordinate = Integer.parseInt(exitOrReturnInput());
            if (coordinate < 1 || coordinate > settings.boardSize) {
                System.out.println("Incorrect number! Try again.");
            } else {
                break;
            }
        }
        return coordinate;
    }

    public static String exitOrReturnInput() {
        Scanner in = new Scanner(System.in);
        var input = in.next();
        if (input.equals("quit")) {
            System.exit(0);
        }
        return input;
    }




}