import java.security.InvalidParameterException;

class BowlingGame {
    private int score;
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
        if (framePinsDown > 10) {
            throw new InvalidParameterException();
        }

        rollNumber++;
        score += pins;

        addBonuses(pins);

        if (pins == 10) {
            strikeBonusRemainingRounds = strikeBonusRounds;
        }
        if (framePinsDown == 10 || rollNumber == 2) {
            spareBonusRemainingRounds = spareBonusRounds;
            frame++;
            rollNumber = 0;
            framePinsDown = 0;
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
