import java.util.Arrays;

public class Board {
    String[][] board;
    private final int boardSize;
    Settings settings = Game.settings;

    public Board(int sideSize) {
//        Create board of blank fields.
        this.boardSize = sideSize + 1;
        this.board = new String[boardSize][boardSize];

        for (String[] row : this.board) {
            Arrays.fill(row, " ");
        }
        for (int i = 1; i <= sideSize; i++) {
            String j = String.valueOf(i);
            this.board[0][i] = (" " + j + " ");
        }
    }

    public void displayBoard() {
//        Displaying whole board.
        System.out.print("# ");
        for (int i = 0; i < this.board.length; i++) {
            if (i>0) {
                System.out.print(i + " ");
            }
            for (int j = 1; j < this.board[i].length; j++) {
                if (i==0) {
                    System.out.print(this.board[i][j]);
                } else {
                    System.out.print("[" + this.board[i][j] + "]");
                }
            }
            System.out.println();
        }
    }

    public boolean checkIfVictory() {
//      ************
        boolean isVictory = false;
        String tag;
        for (int i = 1; i < boardSize; i++) {
            for (int j = 1; j < boardSize; j++) {
                String field = this.board[i][j];
                if (!field.equals(" ")) {
                    tag = field;
                    if (j <= boardSize - settings.tagsToVictory && checkIfHorizontalTags(i, j, tag)) {
                        isVictory = true;
                    } else if (j <= boardSize - settings.tagsToVictory && i <= boardSize - settings.tagsToVictory
                            && checkIfBackslashTags(i, j, tag)) {
                        isVictory = true;
                    } else if (i <= boardSize - settings.tagsToVictory && checkIfVerticalTags(i, j, tag)) {
                        isVictory = true;
                    } else if (j >= settings.tagsToVictory && i <= boardSize - settings.tagsToVictory
                            && checkIfSlashTags(i, j, tag)) {
                        isVictory = true;
                    }
                }
            }
        }return isVictory;
    }

    public boolean checkIfHorizontalTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.tagsToVictory; c++) {
            if (this.board[i][j + c].equals(tag)) {
                counter += 1;
            } else break;
        }
        return counter >= settings.tagsToVictory;
    }

    public boolean checkIfVerticalTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.tagsToVictory; c++) {
            if (this.board[i + c][j].equals(tag)) {
                counter += 1;
            }
        }
        return counter >= settings.tagsToVictory;
    }

    public boolean checkIfSlashTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.tagsToVictory; c++) {
            if (this.board[i + c][j - c].equals(tag)) {
                counter += 1;
            }
        }
        return counter >= settings.tagsToVictory;
    }

    public boolean checkIfBackslashTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.tagsToVictory; c++) {
            if (this.board[i + c][j + c].equals(tag)) {
                counter += 1;
            }
        }
        return counter >= settings.tagsToVictory;
    }

}

