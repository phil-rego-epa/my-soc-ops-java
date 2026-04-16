# Design Spec

## Card Deck Shuffle

- Add a third mode that turns the prompt set into a single-card draw experience instead of a board or checklist.
- Reuse the existing shuffled board payload so deck mode stays frontend-driven and inherits the current board-theme selection.
- Present the draw surface as a cyberpunk console: neon gradients, glassy chrome, and high-contrast monospace copy that fits the current space-station look.
- Use a 3D `rotateY` flip for the reveal so the interaction feels tactile rather than like a flat content swap.
- Persist the current revealed card and remaining prompt stack so reloading the page does not reset an in-progress deck session.
- Add a short charge-up state before the flip so the deck appears to arm itself, then snap into the reveal with a brighter holo frame.
- Style both card faces like a scanner terminal rather than a generic playing card: top status rail, capsule chip, scanline sheen, and neon footer copy.

## Lobby Refinement

- Use a single mode-aware primary CTA so Bingo, Scavenger Hunt, and Card Deck do not compete with conflicting start buttons.
- Preserve atmosphere while improving readability: keep the starfield, but lower its intensity behind text-heavy lobby panels.
- Treat mode and board selectors as proper grouped controls with clear focus states and stronger selected-state contrast.
- Keep lobby preferences separate from active game snapshots so reloading the page resumes the correct mode instead of leaking state across views.
- Respect reduced-motion preferences for the background warp, card charge-up, and other decorative transitions.