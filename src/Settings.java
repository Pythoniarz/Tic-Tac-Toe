public class Settings {
    private int boardSize;
    private int tagsToVictory;
    private int pointsToVictory;


    public void setTagsToVictory() {
//        Sets how many fields in a row a player has to tag to win the round.
        while (true) {
            this.tagsToVictory = Integer.parseInt(Run.exitOrReturnInput());
            if (this.tagsToVictory < 3 || this.tagsToVictory > this.boardSize) {
                System.out.println("Wrong number! Please try again.");
            } else {
                break;
            }
        }
    }

    public void setTagsToVictory(int tagsToVictory) {

        this.tagsToVictory = tagsToVictory;
    }

    public void setPointsToVictory(int pointsToVictory) {

        this.pointsToVictory = pointsToVictory;
    }

    public void setBoardSize(int boardSize) {
        this.boardSize = boardSize;
    }

    public int getBoardSize() {
        return boardSize;
    }

    public int getTagsToVictory() {
        return tagsToVictory;
    }

    public int getPointsToVictory() {
        return pointsToVictory;
    }
}

