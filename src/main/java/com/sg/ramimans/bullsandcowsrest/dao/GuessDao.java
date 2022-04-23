/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ramimans.bullsandcowsrest.dao;

import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.util.List;

/**
 *
 * @author rmans
 */
public interface GuessDao {
    public List<Guess> getGuessesByGameID(int id);
    public Guess addGuess(Guess guess);
}
