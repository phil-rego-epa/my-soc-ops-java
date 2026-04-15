package com.socops.data;

import java.util.List;

/**
 * Chaotic tech and side projects icebreaker prompts for the bingo game.
 * Contains exactly 24 prompts to fill a 5x5 grid minus the center free cell.
 */
public final class ChaoticTechPrompts {

    /** 
     * Complete list of chaotic tech icebreaker prompts for the bingo game.
     * Contains exactly 24 prompts to fill a 5x5 grid minus the center free cell.
     */
    public static final List<String> ALL_PROMPTS = List.of(
            "Show your most cursed browser tab",
            "Has a side project older than 3 years",
            "Built something only they ever used",
            "Secretly liked a bug they shipped",
            "Keeps TODOs in a weird place (share)",
            "Has a domain with no project",
            "Demo a 5‑second editor shortcut",
            "Reenact \"waiting for CI\" pose",
            "Share a side‑project fail they love",
            "Named a repo something unpronounceable",
            "Uses more than 3 package managers",
            "Pitch the worst app idea in 15 seconds",
            "Show a doodle for an app idea",
            "Plays rock–paper–scissors; loser shares a tech tip",
            "Has rebuilt the same side project twice",
            "Shares their nerdiest notification sound",
            "Teach a 5‑second CLI or browser trick",
            "Once rage‑quit a tutorial (now laughs)",
            "Uses a language they lovingly call \"chaotic\"",
            "Show the oldest project in their Git history",
            "Has shipped a \"temporary\" hack that lived",
            "Can describe a dream sci‑fi feature",
            "Shares a surprisingly wholesome use of tech",
            "Has contributed even a tiny fix to OSS"
    );

    private ChaoticTechPrompts() {
        /* catalogue only — no instances */
    }
}