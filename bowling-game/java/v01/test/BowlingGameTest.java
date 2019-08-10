import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class BowlingGameTest {

    @Test
    void WhenGameStarts_ScoreIsNull() {
        BowlingGame game = new BowlingGame();
        assertEquals(0, game.score());
    }


}