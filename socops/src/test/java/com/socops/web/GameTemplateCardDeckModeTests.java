package com.socops.web;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.ClassPathResource;
import org.springframework.test.web.servlet.MockMvc;

import java.nio.charset.StandardCharsets;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@AutoConfigureMockMvc
class GameTemplateCardDeckModeTests {

    @Autowired
    private MockMvc mockMvc;

    @Test
    @DisplayName("Lobby page offers Card Deck Shuffle as a playable mode")
    void lobbyPageOffersCardDeckShuffleMode() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("Card Deck Shuffle"),
                        "The lobby should advertise the card-deck mode by name"),
                () -> assertTrue(html.contains("value=\"deck\""),
                        "The mode selector should include the deck value"),
                () -> assertTrue(html.contains("Launch Card Deck"),
                        "The mode-aware CTA should advertise the deck launch action"),
                () -> assertTrue(html.contains("id=\"deckModeView\""),
                        "The active view should include a deck-mode container")
        );
    }

    @Test
    @DisplayName("Template script defines draw, shuffle, and deck persistence helpers")
    void templateDefinesDeckModeHelpers() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("var MODE_DECK = \"deck\""),
                        "The client script should define a deck gameplay mode constant"),
                () -> assertTrue(html.contains("var DECK_PERSIST_KEY = \"socops-card-deck-snapshot\""),
                        "The client script should define a dedicated deck persistence key"),
                () -> assertTrue(html.contains("function renderDeckMode()"),
                        "The client script should render the deck mode UI"),
                () -> assertTrue(html.contains("function drawNextCard()"),
                        "The client script should expose the card draw interaction"),
                () -> assertTrue(html.contains("window.startDeckMode = function ()"),
                        "The client script should expose a dedicated deck-mode launch helper"),
                () -> assertTrue(html.contains("function refreshDeckMode()"),
                        "The client script should support reshuffling the deck")
        );
    }

    @Test
    @DisplayName("Template exposes a stable reveal panel for drawn deck cards")
    void templateExposesDeckRevealPanel() throws Exception {
        String html = fetchGamePage();

        assertAll(
                () -> assertTrue(html.contains("id=\"deckRevealPanel\""),
                        "Deck mode should render a stable reveal panel container"),
                () -> assertTrue(html.contains("aria-controls=\"deckRevealPanel\""),
                        "The draw trigger should target the reveal panel explicitly"),
                () -> assertTrue(html.contains("class=\"draw-card-face draw-card-back\""),
                        "The template should include a dedicated back face for revealed prompts"),
                () -> assertTrue(html.contains("class=\"deck-question-text\" aria-live=\"polite\" aria-atomic=\"true\""),
                        "The revealed prompt should be present in a live region so updates are announced")
        );
    }

    @Test
    @DisplayName("Stylesheet gives deck mode a cyberpunk flip layout that keeps the reveal face visible")
    void stylesheetIncludesCyberpunkFlipCardStyles() throws Exception {
        String css = readCss();

        assertAll(
                () -> assertTrue(css.contains(".deck-mode"),
                        "The stylesheet should define deck-mode layout styling"),
                () -> assertTrue(css.contains(".draw-card.is-flipped"),
                        "The stylesheet should define the flipped card state"),
                () -> assertTrue(css.contains(".draw-card.is-drawing"),
                        "The stylesheet should define the draw charge-up animation state"),
                () -> assertTrue(css.contains(".draw-card {") && css.contains("display: grid;"),
                        "The deck card should stack its faces with grid so the reveal panel paints reliably"),
                () -> assertTrue(css.contains("grid-area: 1 / 1;"),
                        "Both deck faces should occupy the same layout slot during the flip"),
                () -> assertTrue(css.contains("transform-style: preserve-3d"),
                        "The card animation should preserve 3D transforms"),
                () -> assertTrue(css.contains("backface-visibility: hidden"),
                        "The flip faces should hide their backfaces"),
                () -> assertTrue(css.contains(".draw-card.is-flipped .draw-card-back {")
                                && css.contains("transform: rotateY(0deg);")
                                && css.contains("opacity: 1;"),
                        "The revealed back face should become visible after a draw"),
                () -> assertTrue(css.contains(".deck-trigger"),
                        "The stylesheet should define the cyberpunk deck trigger styling")
        );
    }

    private String fetchGamePage() throws Exception {
        return mockMvc.perform(get("/"))
                .andExpect(status().isOk())
                .andReturn()
                .getResponse()
                .getContentAsString();
    }

    private String readCss() throws Exception {
        ClassPathResource resource = new ClassPathResource("static/css/app.css");
        return new String(resource.getInputStream().readAllBytes(), StandardCharsets.UTF_8);
    }
}