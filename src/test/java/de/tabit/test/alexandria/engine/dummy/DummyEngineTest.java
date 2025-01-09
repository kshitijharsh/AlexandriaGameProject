package de.tabit.test.alexandria.engine.dummy;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * Unit tests for the DummyEngine class, which simulates the behavior of the game engine for the Alexandria board game.
 *
 * The tests validate that the DummyEngine class correctly handles the game logic such as player selection, movement,
 * and the game’s termination based on player positions.
 */
class DummyEngineTest {

    private DummyEngine engine;

    @BeforeEach
    void setUp() {
        engine = new DummyEngine();
    }

    /**
     * Tests the startGame method with a valid number of players. Ensures that:
     * - The correct number of players is initialized.
     * - The correct number of playing fields, bonus fields, and trap fields are created.
     */
    @Test
    void testStartGame_ValidPlayerCount() {
        String result = engine.startGame(3);
        assertNotNull(result);
        assertEquals(3, DummyEngine.players.size());
        assertEquals(30, DummyEngine.playingFields.size());
        assertEquals(5, DummyEngine.bonusFields.size());
        assertEquals(5, DummyEngine.trapFields.size());
    }

    /**
     * Tests the gameIsRunning method when the game ends. Ensures that:
     * - The game stops running when a player’s position exceeds 30.
     */
    @Test
    void testGameIsRunning_GameEnds() {
        engine.startGame(2);
        DummyEngine.players.get(0).setPosition(31);
        assertFalse(engine.gameIsRunning());
    }

    /**
     * Tests the nextPlayer method to ensure the correct player is selected for the next turn.
     */
    @Test
    void testNextPlayer_CorrectPlayerSelection() {
        engine.startGame(4);
        String playerTurn = engine.nextPlayer();
        assertNotNull(playerTurn);
        assertTrue(playerTurn.startsWith("Player"));
    }

    /**
     * Tests the game end condition. Ensures that:
     * - The game ends when a player moves beyond the 30th position after their turn.
     */
    @Test
    void testGameEndCondition() {
        engine.startGame(2);

        // Set the positions of both players such that one will cross 30
        DummyEngine.players.get(0).setPosition(30);
        DummyEngine.players.get(1).setPosition(30);
        DummyEngine.currentPlayer = DummyEngine.players.get(0);

        engine.nextPlayerTurn(2);
        assertFalse(engine.gameIsRunning());
    }

    /**
     * Tests the start of the game by Ensuring the correct message is returned upon starting the game.
     */
    @Test
    void testGameStart() {
        String result = engine.startGame(2);

        assertTrue(result.contains("Bonus fields are located at:"));
        assertTrue(result.contains("Traps fields are located at:"));
    }

}

