# Copilot Instructions for `my-soc-ops-java`

## Mandatory development checklist
- **Lint / typecheck:** `cd socops && ./mvnw -q compile` (fail on compile or type errors).
- **Build:** `cd socops && ./mvnw clean package` (or VS Code task `mvn: build`).
- **Test:** `cd socops && ./mvnw test` (or VS Code task `mvn: test`).

## Overview & architecture
- Java 21 / Spring Boot 3.4.2 app called **Soc Ops**, a social bingo game for in‑person mixers.
- Single Spring Boot backend that serves `game.html` at `/` and a single JSON API at `/api/bingo/fresh-board`.
- Main packages:
  - `com.socops` – app entry point (`SocOpsApplication`).
  - `com.socops.data` – prompt catalogues and constants (`IcebreakerPrompts`, `FREE_CELL_LABEL`).
  - `com.socops.model` – small, immutable value types (`BingoCell`, `WinningStreak`, `PlayPhase`).
  - `com.socops.service` – static, pure helpers (no Spring beans) such as `BoardAssembler`.
  - `com.socops.web` – thin MVC controllers (`BingoRestController`).
- Frontend is a single Thymeleaf template `src/main/resources/templates/game.html` plus CSS utilities in `static/css/app.css` and a small embedded JS game engine.

## Data flow
- Page load: `GET /` → `BingoRestController.serveLobbyPage()` → view name `"game"` → renders `game.html`.
- Board generation: `GET /api/bingo/fresh-board` → `BoardAssembler.assembleNewBoard()`:
  - Shuffles `IcebreakerPrompts.ALL_PROMPTS`, fills a 5×5 grid, centre index `12` is always `BingoCell.ofFreeCell`.
- Game loop (frontend): JS in `game.html` mirrors backend logic:
  - `flipTile` / `scanForStreak` / `lineComplete` match `BoardAssembler.flipCell` and `detectWinningStreak`.
  - State (`tiles`, `streak`, `phase`) persists in `localStorage` under `socops-bingo-snapshot`.

## Coding conventions
- **Services:** keep helpers static and framework‑free.
  - `BoardAssembler` is `final` with a private constructor and only `static` methods; follow this pattern for similar logic.
- **Models:** treat domain objects as records; extend via constructor/record fields, not mutable setters.
- **Controllers:** keep `@Controller` classes very thin: map URLs, call static service helpers, return view names or DTO/model types (avoid generic `Map`).
- **Frontend ↔ backend parity:** when changing board size, win rules, or cell fields in `BoardAssembler`, update the mirrored JS functions in `game.html` so behaviour stays in sync.
- **Styling:** use the project’s CSS utilities in `static/css/app.css` (see `.github/instructions/css-utilities.instructions.md`). Prefer utility classes like `flex`, `grid`, `bg-accent`, `text-gray-700`, `rounded-lg`; add new utilities to `app.css` instead of inline styles.

## Build, test, and run
- Use the Maven Wrapper from `socops/`:
  - Dev server: `cd socops && ./mvnw spring-boot:run`.
  - Build: `cd socops && ./mvnw clean package`.
  - Tests: `cd socops && ./mvnw test`.
- VS Code tasks in `.vscode/tasks.json` wrap these commands (`mvn: run`, `mvn: build`, `mvn: test`); prefer those labels when scripting workflows.
- Project targets **Java 21** (`<java.version>21</java.version>` in `pom.xml`); don’t downgrade or change the language level.

## Testing practices & safe extension
- Tests use JUnit 5 under `src/test/java`; `BoardAssemblerTests` is the reference for style (descriptive `@DisplayName`, happy paths plus invariants like free‑cell behaviour).
- When you modify board logic, add or adjust tests in `BoardAssemblerTests` first, then update the mirrored JS logic in `game.html`.
- For new features (alternate prompts, themes, victory conditions):
  - Extend `IcebreakerPrompts` or add new catalogues under `com.socops.data`.
  - Add pure helpers to `BoardAssembler` or sibling static service classes.
  - Keep `/api/bingo/fresh-board` response shape (list of `BingoCell` JSON objects) stable unless you also update the frontend consumer.
- For UI changes, edit `game.html` and `app.css`, following the existing layout and utility patterns; avoid introducing heavy JS frameworks and keep client logic in vanilla JS.
