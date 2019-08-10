import org.junit.jupiter.api.Test;

import java.security.InvalidParameterException;

import static org.junit.jupiter.api.Assertions.*;

class BowlingGameTest {
    private int totalPins = 10;
    private int totalFrames = 10;

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
        for (int i = 0; i < rolls; i++) {
            game.roll(pinsDown);
        }
        assertEquals(pinsDown * rolls, game.score());
    }

    @Test
    void WhenThirdRollAfterSpare_ThenScoreIsTenPlusDoubleLastRoll() {
        BowlingGame game = new BowlingGame();
        int lastRollPinsDown = 5;
        game.roll(4);
        game.roll(6);
        game.roll(lastRollPinsDown);
        assertEquals(totalPins + 2 * lastRollPinsDown, game.score());
    }

    @Test
    void WhenThirdRollAfterSpareIsNull_ThenScoreIsTen() {
        BowlingGame game = new BowlingGame();
        game.roll(4);
        game.roll(6);
        game.roll(0);
        assertEquals(totalPins, game.score());
    }

    @Test
    void WhenThirdRollAfterStrikeAndSecondRollIsNull_ThenScoreIsTenPlusDoubleLastRoll() {
        BowlingGame game = new BowlingGame();
        int lastRollPinsDown = 5;
        game.roll(totalPins);
        game.roll(0);
        game.roll(lastRollPinsDown);
        assertEquals(totalPins + 2 * lastRollPinsDown, game.score());
    }

    @Test
    void WhenLastFrameSpare_ThenExtraRollInLastFrame() {
        BowlingGame game = new BowlingGame();
        for (int f = 0; f < totalFrames - 1; f++) {
            game.roll(0);
            game.roll(0);
        }
        assertEquals(totalFrames, game.getFrame());
        game.roll(5);
        game.roll(5);
        assertEquals(totalFrames, game.getFrame());
        assertDoesNotThrow(() -> game.roll(6));
    }

    @Test
    void WhenLastFrameStrike_ThenExtraRollInLastFrame() {
        BowlingGame game = new BowlingGame();
        for (int f = 0; f < totalFrames - 1; f++) {
            game.roll(0);
            game.roll(0);
        }
        assertEquals(totalFrames, game.getFrame());
        game.roll(totalPins);
        assertEquals(totalFrames, game.getFrame());
        assertDoesNotThrow(() -> game.roll(6));
    }

    @Test
    void WhenLastFrameSpare_ThenOnlyOneExtraRoll() {
        BowlingGame game = new BowlingGame();
        for (int f = 0; f < totalFrames; f++) {
            game.roll(5);
            game.roll(5);
        }
        game.roll(5);
        assertThrows(InvalidParameterException.class, () -> game.roll(6));
    }

    @Test
    void WhenLastFrameStrike_ThenOnlyOneExtraRoll() {
        BowlingGame game = new BowlingGame();
        for (int f = 0; f < totalFrames; f++) {
            game.roll(10);
        }
        game.roll(10);
        assertThrows(InvalidParameterException.class, () -> game.roll(6));
    }

    @Test
    void WhenLastFrameNoSpareOrStrike_ThenNoExtraRoll() {
        BowlingGame game = new BowlingGame();
        for (int f = 0; f < totalFrames; f++) {
            game.roll(0);
            game.roll(0);
        }
        assertThrows(InvalidParameterException.class, () -> game.roll(6));
    }

    @Test
    void WhenPerfectGame_ThenScoreIs300() {
        BowlingGame game = new BowlingGame();
        for (int f = 0; f < totalFrames + 1; f++) {
            game.roll(10);
        }
        assertEquals(300, game.score());
    }


}
