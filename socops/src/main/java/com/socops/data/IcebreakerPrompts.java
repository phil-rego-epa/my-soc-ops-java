package com.socops.data;

import java.util.List;

/**
 * Central catalogue of every icebreaker prompt that can appear on a board.
 * Exactly 24 entries — one fewer than the 25-cell grid, because the
 * centre cell is always the free space.
 */
public final class IcebreakerPrompts {

    /** The label displayed for the center free cell on the bingo board. */
    public static final String FREE_CELL_LABEL = "FREE SPACE";

    /** 
     * Complete list of icebreaker prompts for the bingo game.
     * Contains exactly 24 prompts to fill a 5x5 grid minus the center free cell.
     */
    public static final List<String> ALL_PROMPTS = List.of(
            "bikes to work",
            "has lived in another country",
            "has a pet",
            "prefers tea over coffee",
            "plays an instrument",
            "speaks more than 2 languages",
            "has run a marathon",
            "was born in a different state",
            "has met a celebrity",
            "can juggle",
            "has been skydiving",
            "loves cooking",
            "has a garden",
            "has traveled to Asia",
            "is left-handed",
            "has a twin",
            "plays video games",
            "does yoga",
            "has a hidden talent",
            "loves spicy food",
            "has been on TV",
            "collects something unique",
            "has read a book this month",
            "knows sign language"
    );

    private IcebreakerPrompts() {
        /* catalogue only — no instances */
    }
}
