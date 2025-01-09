package de.tabit.test.alexandria.engine.dummy;

import de.tabit.test.alexandria.engine.api.IAlexandriaGameEngine;
import de.tabit.test.alexandria.entity.Player;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import java.util.concurrent.atomic.AtomicInteger;

import static java.lang.String.format;

/**
 * The DummyEngine class implements the IAlexandriaGameEngine interface, simulating a game engine
 * for the Alexandria game. It manages the game state, player turns, and interactions with game fields,
 * such as bonus and trap fields. The engine allows for the initialization of players, the simulation
 * of game turns, and determining when the game ends.
 *
 */
public class DummyEngine implements IAlexandriaGameEngine {

    private static final AtomicInteger TURNS = new AtomicInteger(0);
    private final Random random = new Random();
    static ArrayList<Player> players = new ArrayList<>();
    static Player currentPlayer;
    static ArrayList<Integer> playingFields = new ArrayList<>();
    static ArrayList<Integer> bonusFields = new ArrayList<>();
    static ArrayList<Integer> trapFields = new ArrayList<>();

    /**
     * Initializes the game by creating the specified number of players and setting up the game board.
     * It populates the playing fields, bonus fields, and trap fields, and then shuffles the playing fields.
     * The bonus and trap fields are randomly selected from the first 10 shuffled positions.
     *
     * @param numberOfPlayers The number of players to initialize in the game.
     * @return A message indicating the positions of the bonus and trap fields.
     */
    @Override
    public String startGame(Integer numberOfPlayers) {

        // Create a list to store the players
        for (int i = 1; i <= numberOfPlayers; i++) {
            players.add(new Player(i, 0));
        }

        //Create All-in-all 30 playing fields
        for (int i = 1; i <= 30; i++) {
            playingFields.add(i);
        }

        // Shuffle the ArrayList to randomize the order
        Collections.shuffle(playingFields);

        // Select the first 5 values for the "bonus" ArrayList and the next 5 for "trap"
        bonusFields.addAll(playingFields.subList(0, 5));
        trapFields.addAll(playingFields.subList(5, 10));

        // Convert lists to string format and return the final message
        return "Bonus fields are located at: " + bonusFields.toString().replaceAll("[\\[\\]]", "") +
                ". Traps fields are located at: " + trapFields.toString().replaceAll("[\\[\\]]", "");
    }

    /**
     * Checks if the game is still running. The game ends when a player moves beyond the 30th playing field.
     *
     * @return true if the game is still running, false otherwise.
     */
    @Override
    public boolean gameIsRunning() {
        // Game should run until one player crosses the 30th Playing field
        boolean hasPersonWithPositionMoreThan30 = players.stream()
                .anyMatch(player -> player.getPosition() > 30);
        return !hasPersonWithPositionMoreThan30;
    }

    /**
     * Selects the next player randomly from the list of players. If the selected player is marked to skip their round,
     * it selects another player.
     *
     * @return A string indicating which player’s turn it is.
     */
    @Override
    public String nextPlayer() {
        currentPlayer = players.get(random.nextInt(players.size()));
        if(currentPlayer.isSkipRound()){
            currentPlayer.setSkipRound(false);
            currentPlayer = players.get(random.nextInt(players.size()));
        }
        return format("Player %d", currentPlayer.getPlayerNumber());
    }

    /**
     * Simulates the movement of the current player based on the dice roll (input). If the player lands on a trap or bonus field,
     * it triggers the corresponding action (e.g., moving the player, affecting other players, or activating a joker).
     *
     * @param input The number of fields the current player moves, based on the dice roll.
     * @return A message describing the player's new position or the action triggered by the bonus/trap field.
     */
    @Override
    public String nextPlayerTurn(Integer input) {
        currentPlayer.setPosition(currentPlayer.getPosition() + input);
        if(trapFields.contains(currentPlayer.getPosition())) {
            if (currentPlayer.isJoker()) {
                currentPlayer.setJoker(false);
                return "Joker activated! Trap avoided.";
            }
            return trapFieldsAction(random.nextInt(3) + 1);
        }
        else if (bonusFields.contains(currentPlayer.getPosition())) {
            return bonusFieldsAction(random.nextInt(3) + 1);
        }
        return String.format("Current position for player %d is %d",
                currentPlayer.getPlayerNumber(), currentPlayer.getPosition());
    }

    /**
     * Handles the actions triggered when the current player lands on a trap field. There are three possible actions:
     * 1. Move the player back 2 fields.
     * 2. Move all other players forward 2 fields.
     * 3. Skip the player's next round.
     *
     * @param type The type of trap field action (1, 2, or 3).
     * @return A message describing the result of the trap action.
     */
    private String trapFieldsAction(int type) {
        switch (type) {
            case 1:
                currentPlayer.setPosition(currentPlayer.getPosition() - 2);
                return String.format("Current position for player %d is %d",
                        currentPlayer.getPlayerNumber(), currentPlayer.getPosition());

            case 2:
                StringBuilder result = new StringBuilder("All other players move forward 2 fields:\n");
                for (Player player : players) {
                    if (player.getPlayerNumber() != currentPlayer.getPlayerNumber()) {
                        player.setPosition(player.getPosition() + 2);
                        result.append(String.format("Current position for player %d is %d\n",
                                player.getPlayerNumber(), player.getPosition()));
                    }
                }
                return result.toString();

            case 3:
                currentPlayer.setSkipRound(true);
                return String.format("Player %d will skip the next round", currentPlayer.getPlayerNumber());

            default:
                return "Invalid trap type!";
        }
    }


    /**
     * Handles the actions triggered when the current player lands on a bonus field. There are three possible actions:
     * 1. Move the player forward 2 fields.
     * 2. Move all other players backward 2 fields.
     * 3. Activate the player's joker.
     *
     * @param type The type of bonus field action (1, 2, or 3).
     * @return A message describing the result of the bonus action.
     */
    private String bonusFieldsAction(int type){
        switch (type){
            case 1:
                currentPlayer.setPosition(currentPlayer.getPosition() + 2);
                return String.format("Current position for player %d is %d",
                        currentPlayer.getPlayerNumber(), currentPlayer.getPosition());
            case 2:
                StringBuilder result = new StringBuilder("All other players move backward 2 fields:\n");
                for (Player player : players) {
                    if (player.getPlayerNumber() != currentPlayer.getPlayerNumber()) {
                        player.setPosition(player.getPosition() - 2);
                        result.append(String.format("Current position for player %d is %d\n",
                                player.getPlayerNumber(), player.getPosition()));
                    }
                }
                return result.toString();
            case 3:
                currentPlayer.setJoker(true);
                return String.format("Player %d has activated the joker.", currentPlayer.getPlayerNumber());

            default:
                return "Invalid trap type!";
        }
    }
}
