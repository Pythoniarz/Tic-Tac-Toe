import java.util.Scanner;

public class Run {
    public static void main(String[] args) {

        Game ticTacToe = new Game();

//        Initialization of game settings.
        System.out.println("\n# # # The TIC-TAC-TOE game # # #\n");
        System.out.println("(you can enter \"quit\" at any time during game to exit program)\n");

//        Creating players.
        System.out.println("Please enter number of players:");
        ticTacToe.setPlayers(ticTacToe.setAndGetNumberOfPlayers());
        ticTacToe.setPlayersNamesAndTags(ticTacToe.getPlayers());

//        Defining game rules and creating the game board.
        ticTacToe.defineBoardRules();
        ticTacToe.setGameBoard(new Board(ticTacToe.getSettings()));
        ticTacToe.setWonRoundsNeeded();

//        Game play.
        System.out.println("LET'S PLAY!");
        ticTacToe.getGameBoard().displayBoard();
        while (!Game.isVictory()) {
            ticTacToe.gamePlay(ticTacToe.getPlayers());
        }
    }

    public static String exitOrReturnInput() {
//        Exits program if input is 'quit' or returns input otherwise.
        Scanner in = new Scanner(System.in);
        var input = in.next();
        if (input.equals("quit")) {
            System.exit(0);
        }
        return input;
    }
}
