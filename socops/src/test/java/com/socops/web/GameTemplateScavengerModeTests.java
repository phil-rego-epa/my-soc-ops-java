package com.socops.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameTemplateScavengerModeTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Lobby page promotes scavenger hunt as a dedicated start-screen mode")
    void lobbyPagePromotesScavengerHuntMode() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("Scavenger Hunt"),
                        "The start screen should mention Scavenger Hunt by name"),
                () -> assertTrue(html.contains("id=\"startPrimaryButton\""),
                        "The start screen should expose a single mode-aware primary action"),
                () -> assertTrue(html.contains("Start Scavenger Hunt"),
                        "The lobby CTA logic should include a scavenger-specific label")
        );
    }

    @Test
    @DisplayName("Scavenger mode renders an accessible checklist shell with progress semantics")
    void scavengerModeRendersChecklistShell() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("id=\"scavengerChecklist\""),
                        "Scavenger mode should render a checklist container with a stable id"),
                () -> assertTrue(html.contains("role=\"list\""),
                        "The checklist container should expose list semantics"),
                () -> assertTrue(html.contains("role=\"progressbar\""),
                        "Scavenger mode should expose progress through a semantic progressbar"),
                () -> assertTrue(html.contains("aria-valuemin=\"0\""),
                        "The progressbar should declare its minimum value"),
                () -> assertTrue(html.contains("aria-valuemax=\"24\""),
                        "The progressbar should declare the scavenger target count")
        );
    }

    @Test
    @DisplayName("Embedded script defines checklist interactions and progress calculation helpers")
    void templateDefinesChecklistInteractionHelpers() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("function renderChecklist(items)"),
                        "The template script should define checklist rendering for scavenger mode"),
                () -> assertTrue(html.contains("function toggleChecklistItem(itemId)"),
                        "The template script should define checkbox toggle behavior"),
                () -> assertTrue(html.contains("function calculateChecklistProgress(items)"),
                        "The template script should define a dedicated checklist progress helper")
        );
    }

    @Test
    @DisplayName("Scavenger mode persists and restores its own state snapshot")
    void scavengerModePersistsOwnStateSnapshot() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("var SCAVENGER_PERSIST_KEY = \"socops-scavenger-snapshot\""),
                        "The template script should define a dedicated scavenger persistence key"),
                () -> assertTrue(html.contains("var LOBBY_PREFS_KEY = \"socops-lobby-preferences\""),
                        "The template script should keep lobby preferences separate from gameplay snapshots"),
                () -> assertTrue(html.contains("function saveScavengerState()"),
                        "The template script should define scavenger-state persistence"),
                () -> assertTrue(html.contains("function restoreScavengerState()"),
                        "The template script should define scavenger-state restoration")
        );
    }

    private String fetchGamePage() throws Exception {
        return mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }
}