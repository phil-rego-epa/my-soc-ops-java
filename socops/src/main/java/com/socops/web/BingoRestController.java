package com.socops.web;

import com.socops.model.BingoCell;
import com.socops.service.BoardAssembler;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/** 
 * Serves the game page and the board-generation REST endpoint.
 * Handles both the HTML game interface and the JSON API for fresh boards.
 */
@Controller
public class BingoRestController {

    /**
     * Serves the main game page.
     * @return the view name "game" which renders the Thymeleaf template
     */
    @GetMapping("/")
    public String serveLobbyPage() {
        return "game";
    }

    /**
     * Provides a fresh bingo board with shuffled prompts.
     * @return a list of 25 BingoCell objects representing the new board
     */
    @GetMapping("/api/bingo/fresh-board")
    @ResponseBody
    public List<BingoCell> dispenseFreshBoard() {
        return BoardAssembler.assembleNewBoard();
    }
}
