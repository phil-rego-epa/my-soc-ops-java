package com.socops.model;

/** Tracks which phase of the bingo session the player is in. */
public enum PlayPhase {
    /** Player is waiting in the lobby before the game starts. */
    LOBBY,
    /** Game is actively in progress, player can mark cells. */
    ACTIVE,
    /** Player has achieved a winning streak and the game is complete. */
    VICTORY
}
