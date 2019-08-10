import java.security.InvalidParameterException;

class BowlingGame {
    private int totalPins = 10;
    private int score;
    private int totalFrames = 10;
    private int frame = 1;
    private int rollNumber = 0;
    private int framePinsDown = 0;
    private int spareBonusRounds = 1;
    private int spareBonusRemainingRounds = 0;
    private int strikeBonusRounds = 2;
    private int strikeBonusRemainingRounds = 0;

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
        framePinsDown += pins;
        if (framePinsDown > totalPins || frame > totalFrames) {
            throw new InvalidParameterException();
        }

        rollNumber++;

        // the last extra roll is counted once
        if (rollNumber <= 2) {
            score += pins;
        }

        addBonuses(pins);

        if (framePinsDown == totalPins || rollNumber > 1) {
            checkBonuses(pins);

            // if is spare or strike in the last frame play another time
            if (frame != totalFrames || framePinsDown != totalPins || rollNumber > 2) {
                frame++;
                rollNumber = 0;
            }
            framePinsDown = 0;
        }

    }

    private void checkBonuses(int pins) {
        if (pins == totalPins) {
            strikeBonusRemainingRounds = strikeBonusRounds;
            // last frame accommodation
            rollNumber++;
        }

        if (framePinsDown == totalPins) {
            spareBonusRemainingRounds = spareBonusRounds;
        }
    }

    private void addBonuses(int pins) {
        if (spareBonusRemainingRounds > 0) {
            score += pins;
            spareBonusRemainingRounds--;
        }

        if (strikeBonusRemainingRounds > 0) {
            score += pins;
            strikeBonusRemainingRounds--;
        }
    }

    int getFrame() {
        return frame;
    }
}
