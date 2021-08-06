import java.util.Arrays;

public class Board {
    private String[][] board;
    private final Settings settings;

    public Board(Settings settings) {
//        Create board of blank fields.
        this.settings = settings;
        this.board = new String[this.settings.getBoardSize()+1][this.settings.getBoardSize()+1];


        for (String[] row : this.board) {
            Arrays.fill(row, " ");
        }
        for (int i = 1; i <= this.settings.getBoardSize(); i++) {
            String j = String.valueOf(i);
            this.board[0][i] = (" " + j + " ");
        }
    }

    public void setSizeOfTheBoard() {
//        Sets size of the game board if data is correct.
        boolean isValueCorrect = false;
        while (!isValueCorrect) {
            try {
                settings.setBoardSize(Integer.parseInt(Run.exitOrReturnInput()));
                isValueCorrect = true;
            } catch (NumberFormatException e) {
                System.out.println("Wrong number! Please try again.");
            }
        }
    }

    public void displayBoard() {
//        Displaying whole board.
        System.out.print("# ");
        for (int i = 0; i < this.board.length; i++) {
            if (i>0) {
                System.out.print(i + " ");
            }
            displayFields(i);
            System.out.println();
        }
    }

    public void displayFields(int i) {
        for (int j = 1; j < this.board[i].length; j++) {
            if (i==0) {
                System.out.print(this.board[i][j]);
            } else {
                System.out.print("[" + this.board[i][j] + "]");
            }
        }
    }

    public boolean checkIfVictory() {
//        Checks if any line of tags on the board gives victory.
        boolean isVictory = false;
        String tag;
        for (int i = 1; i < this.settings.getBoardSize(); i++) {
            for (int j = 1; j < this.settings.getBoardSize(); j++) {
                String field = this.board[i][j];
                if (!field.equals(" ")) {
                    tag = field;
                    if (j <= this.board.length - settings.getTagsToVictory()
                            && checkIfHorizontalTags(i, j, tag)) {
                        isVictory = true;
                    } else if (j <= this.board.length - settings.getTagsToVictory()
                            && i <= this.board.length - settings.getTagsToVictory()
                            && checkIfBackslashTags(i, j, tag)) {
                        isVictory = true;
                    } else if (i <= this.board.length - settings.getTagsToVictory()
                            && checkIfVerticalTags(i, j, tag)) {
                        isVictory = true;
                    } else if (j >= settings.getTagsToVictory()
                            && i <= this.board.length - settings.getTagsToVictory()
                            && checkIfSlashTags(i, j, tag)) {
                        isVictory = true;
                    }
                }
            }
        } return isVictory;
    }

    public boolean checkIfHorizontalTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.getTagsToVictory(); c++) {
            if (this.board[i][j + c].equals(tag)) {
                counter += 1;
            } else break;
        }
        return counter >= settings.getTagsToVictory();
    }

    public boolean checkIfVerticalTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.getTagsToVictory(); c++) {
            if (this.board[i + c][j].equals(tag)) {
                counter += 1;
            }
        }
        return counter >= settings.getTagsToVictory();
    }

    public boolean checkIfSlashTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.getTagsToVictory(); c++) {
            if (this.board[i + c][j - c].equals(tag)) {
                counter += 1;
            }
        }
        return counter >= settings.getTagsToVictory();
    }

    public boolean checkIfBackslashTags(int i, int j, String tag) {
        int counter = 1;
        for (int c = 1; c < settings.getTagsToVictory(); c++) {
            if (this.board[i + c][j + c].equals(tag)) {
                counter += 1;
            }
        }
        return counter >= settings.getTagsToVictory();
    }

    public String[][] getBoard() {
        return board;
    }

}

