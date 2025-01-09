package de.tabit.test.alexandria.entity;

/**
 * The Player class represents a player in the game. Each player has a unique number, a position on the game board,
 * and flags to indicate whether they should skip the current round or if they have a joker.
 *
 * The class provides methods to get and set these attributes.
 */
public class Player {

    // The player's unique identifier or number
    private int playerNumber;

    // The player's position on the game board
    private int position;

    // Flag indicating whether the player should skip the current round (default is false)
    private boolean skipRound = false;

    // Flag indicating whether the player has a joker (default is false)
    private boolean joker = false;

    public Player(int playerNumber, int position) {
        this.playerNumber = playerNumber;
        this.position = position;
    }

    public int getPlayerNumber() {
        return playerNumber;
    }

    public void setPlayerNumber(int playerNumber) {
        this.playerNumber = playerNumber;
    }

    public int getPosition() {
        return position;
    }

    public void setPosition(int position) {
        this.position = position;
    }

    public boolean isSkipRound() {
        return skipRound;
    }

    public void setSkipRound(boolean skipRound) {
        this.skipRound = skipRound;
    }

    public boolean isJoker() {
        return joker;
    }

    public void setJoker(boolean joker) {
        this.joker = joker;
    }
}
