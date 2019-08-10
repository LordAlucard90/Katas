import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingGameTest {

    @Test
    void WhenGameStarts_ThenScoreIsNull() {
        BowlingGame game = new BowlingGame();
        assertEquals(0, game.score());
    }

    @Test
    void WhenFirstRoll_ThenScoreEqualToPinsDown() {
        BowlingGame game = new BowlingGame();
        int pinsDown = 1;
        game.roll(pinsDown);
        assertEquals(pinsDown, game.score());
    }


}