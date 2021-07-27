public class Settings {
    int boardSize;
    int tagsToVictory;
    int pointsToVictory;


    public void setTagsToVictory() {
//        Sets how many fields in a row a player has to tag to win the round.
        while (true) {
            this.tagsToVictory = Integer.parseInt(Game.exitOrReturnInput());
            if (this.tagsToVictory < 3 || this.tagsToVictory > this.boardSize) {
                System.out.println("Wrong number! Please try again.");
            } else {
                break;
            }
        }
    }
}

