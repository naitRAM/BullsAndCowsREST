package com.sg.ramimans.bullsandcowsrest.service;

import com.sg.ramimans.bullsandcowsrest.dao.GuessDao;
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

public class GuessDaoStubImpl implements GuessDao{

    private Guess guess1 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS), 5472, "e:0:p:2", 1);
    private Guess guess2 = new Guess(LocalDateTime.now().truncatedTo(ChronoUnit.HOURS), 8470, "e:2:p:0", 1);
    
    @Override
    public List<Guess> getGuessesByGameID(int id) {
        if (id == 1) {
        List<Guess> guesses  = new ArrayList<>();
        guesses.add(guess1);
        guesses.add(guess2);
        return guesses;
        } 
        return new ArrayList<>();
    }

    @Override
    public Guess addGuess(Guess guess) {
        return guess;
    }

}
