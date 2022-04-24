/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ramimans.bullsandcowsrest.service;

import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.util.List;

/**
 *
 * @author rmans
 */
public interface BullsAndCowsService {
    public Game addGame() throws InvalidGameException, InvalidGuessException;
    public List<Game> getGames();
    public Game getGame(int gameID) throws InvalidGameException;
    public Guess addGuess(Guess guess) throws InvalidGameException, InvalidGuessException;
    public List<Guess> getGuessesForGame(int gameID) throws InvalidGameException;
}
