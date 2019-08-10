import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class BowlingGameTest {

    @Test
    void WhenGameStarts_ThenScoreIsNull() {
        BowlingGame game = new BowlingGame();
        assertEquals(0, game.score());
    }

    @Test
    void WhenGameStarts_ThenFrameIsFirst() {
        BowlingGame game = new BowlingGame();
        assertEquals(1, game.getFrame());
    }

    @Test
    void WhenFirstRoll_ThenScoreEqualToPinsDown() {
        BowlingGame game = new BowlingGame();
        int pinsDown = 1;
        game.roll(pinsDown);
        assertEquals(pinsDown, game.score());
    }

    @Test
    void WhenOnFirstRollAllPinsDown_ThenFrameIncrements() {
        BowlingGame game = new BowlingGame();
        game.roll(10);
        assertEquals(2, game.getFrame());
    }

    @Test
    void WhenTwoRolls_ThenFrameIncrements() {
        BowlingGame game = new BowlingGame();
        game.roll(0);
        game.roll(0);
        assertEquals(2, game.getFrame());
    }

    @Test
    void WhenTwoRollsExceedTen_ThenThrowsException() {
        BowlingGame game = new BowlingGame();
        game.roll(5);
        assertThrows(InvalidParameterException.class, () -> game.roll(6));
    }

    @Test
    void WhenThirdRollAfterTwoRollsWithNoSpare_ThenScoreIsTheSumOfPins() {
        BowlingGame game = new BowlingGame();
        int rolls = 3;
        int pinsDown = 1;
        for (int i = 0; i <= rolls; i++) {
            game.roll(pinsDown);
        }
        assertEquals(pinsDown * rolls, game.getFrame());
    }
}