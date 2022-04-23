package com.sg.ramimans.bullsandcowsrest.controller;

import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import com.sg.ramimans.bullsandcowsrest.service.BullsAndCowsService;
import com.sg.ramimans.bullsandcowsrest.service.InvalidGameException;
import com.sg.ramimans.bullsandcowsrest.service.InvalidGuessException;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

/**
 *
 * @author Rami Mansieh email: rmansieh@gmail.com data: Apr. 22, 2022 purpose:
 */
@RestController
public class BullsAndCowsController {

    @Autowired
    private BullsAndCowsService service;

    @PostMapping("/game")
    public Game newGame() {
        return obscureAnswer(service.addGame());

    }

    @GetMapping("/game")
    public List<Game> sendGames() {
        List<Game> games = service.getGames();
        for (Game game : games) {
            obscureAnswer(game);
        }
        return games;
    }

    @GetMapping("/game/{id}")
    public ResponseEntity<Game> sendGame(@PathVariable int id) {
        try {
        return ResponseEntity.ok(obscureAnswer(service.getGame(id)));
        } catch (InvalidGameException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @GetMapping("/guesses/{gameID}")
    public ResponseEntity<List<Guess>> sendGuesses(@PathVariable int gameID) {
        try {
            return ResponseEntity.ok(service.getGuessesForGame(gameID));
        } catch (InvalidGameException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    @PostMapping("/guess")
    public ResponseEntity<Guess> create(@RequestBody Guess guess) throws InvalidGameException {
        guess.setTime(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS));
        try {
            return ResponseEntity.ok(service.addGuess(guess));
        } catch (InvalidGuessException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_ACCEPTABLE);
        } catch (InvalidGameException e) {
            return new ResponseEntity(e.getMessage(), HttpStatus.NOT_FOUND);
        }
    }

    private Game obscureAnswer(Game game) {
        if (game.getStatus().equals("IN PROGRESS")) {
            game.setAnswer(0);
        }
        return game;
    }
}
