package com.sg.ramimans.bullsandcowsrest.service;

import com.sg.ramimans.bullsandcowsrest.dao.GameDao;
import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rami Mansieh
 * email: rmansieh@gmail.com
 * data: Apr. 23, 2022
 * purpose: 
 */

public class GameDaoStubImpl implements GameDao {
    
    
    private Game daoGame;
   
   
    
    public GameDaoStubImpl () {
        this.daoGame = new Game(1, 6347, "IN PROGRESS");
        List<Guess> guesses = new ArrayList<>();
        Guess guess1 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS), 5472, "e:0:p:2", 1);
        Guess guess2 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS), 8470, "e:2:p:0", 1);
        guesses.add(guess1);
        guesses.add(guess2);
        this.daoGame.setGuesses(guesses);
    }

    @Override
    public Game getGameByID(int id) {
        if (id != 1) {
            return null;
        }
        return daoGame;
    }

    @Override
    public List<Game> getGames() {
        List<Game> games = new ArrayList<>();
        games.add(daoGame);
        return games;
    }

    @Override
    public Game addGame(Game game) {
        return game;
    }

    @Override
    public boolean updateGame(Game game) {
        return true;
    }

    @Override
    public boolean deleteGameByID(int id) {
        return true;
    }
    

}
