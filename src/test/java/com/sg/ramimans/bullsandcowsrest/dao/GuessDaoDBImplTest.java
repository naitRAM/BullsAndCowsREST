/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ramimans.bullsandcowsrest.dao;

import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 *
 * @author rmans
 */
@RunWith(SpringRunner.class)
@SpringBootTest

public class GuessDaoDBImplTest {
    
    @Autowired
    GameDao gameDao;
    
    @Autowired
    GuessDao guessDao;
    
    
    
    @Before
    public void setUp() {
        List<Game> games = gameDao.getGames();
        for (Game game : games) {
            gameDao.deleteGameByID(game.getGameID());
        }
        
    }

   
    @Test
    public void testAddGetGuessesByGameID() {
        Game game1 = new Game(6374);
        Game game2 = new Game(1529);
        game1.setStatus("IN PROGRESS");
        game2.setStatus("IN PROGRESS");
        game1 = gameDao.addGame(game1);
        game2 = gameDao.addGame(game2);
        Guess guess1 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), 5948, "e:0:p:1", game1.getGameID());
        Guess guess2 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), 1693, "e:1:p:1", game2.getGameID());
        Guess guess3 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.SECONDS), 6475, "e:2:p:0", game1.getGameID());
        guessDao.addGuess(guess1);
        guessDao.addGuess(guess2);
        guessDao.addGuess(guess3);
        List<Guess> game1Guesses = new ArrayList<>();
        List<Guess> game2Guesses = new ArrayList<>();
        guess1.setCount(1);
        guess2.setCount(1);
        guess3.setCount(2);
        game1Guesses.add(guess1);
        game2Guesses.add(guess2);
        game1Guesses.add(guess3);
        game1.setGuesses(game1Guesses);
        game2.setGuesses(game2Guesses);
        
        List<Guess> guessesFromDao = guessDao.getGuessesByGameID(game1.getGameID());
        assertEquals(guessesFromDao, game1Guesses);
        guessesFromDao = guessDao.getGuessesByGameID(game2.getGameID());
        assertEquals(guessesFromDao, game2Guesses);
    }

    
    
}
