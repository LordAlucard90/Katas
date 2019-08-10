class BowlingGame {
    private int score;
    private int frame = 1;

    /**
     * Returns the total score for that game.
     *
     * @return total score
     */
    int score() {
        return score;
    }

    /**
     * Is called each time the player rolls a ball..
     *
     * @param pins number of pins knocked down
     */
    void roll(int pins) {
        score = pins;
        if (pins == 10) {
            frame++;
        }

    }

    int getFrame() {
        return frame;
    }
}
