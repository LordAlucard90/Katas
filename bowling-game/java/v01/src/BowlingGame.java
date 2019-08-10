import java.security.InvalidParameterException;

class BowlingGame {
    private int score;
    private int frame = 1;
    private int rollNumber = 0;
    private int framePinsDown = 0;

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
        rollNumber++;
        score += pins;
        framePinsDown += pins;
        if (framePinsDown > 10) {
            throw new InvalidParameterException();
        }
        if (pins == 10 || rollNumber == 2) {
            frame++;
            rollNumber = 0;
            framePinsDown = 0;
        }

    }

    int getFrame() {
        return frame;
    }
}
