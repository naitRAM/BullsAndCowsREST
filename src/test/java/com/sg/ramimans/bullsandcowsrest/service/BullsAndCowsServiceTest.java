/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ramimans.bullsandcowsrest.service;

import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.time.LocalDateTime;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author rmans
 */
public class BullsAndCowsServiceTest {

    BullsAndCowsService service;

    public BullsAndCowsServiceTest() {
        service = new BullsAndCowsService(new GameDaoStubImpl(), new GuessDaoStubImpl());
    }

   
    @Test
    public void testAddGame() {
        for (int i = 0; i < 100; i++) {
            Game game = service.addGame();
            assertTrue(game.getStatus().equals("IN PROGRESS"));
            int answer = game.getAnswer();
            assertTrue(answer >= 1023);
            assertTrue(answer <= 9876);
            String answerStr = String.valueOf(game.getAnswer());
            assertTrue(answerStr.length() == 4);
            for (int j = 0; i < answerStr.length(); i++) {
                for (int k = 0; k < answerStr.length(); k++) {
                    if (j != k) {
                        if (answerStr.charAt(j) == answerStr.charAt(k)) {
                            fail("number does not contain unique digits");
                        }
                    }
                }
            }
        }

    }
   
    @Test
    public void testAddWinngingGuess() throws InvalidGuessException, InvalidGameException {
        Guess guess = new Guess();
        guess.setValue(6347);
        guess.setGameID(1);
        guess.setCount(3);
        guess.setTime(LocalDateTime.now());
        guess = service.addGuess(guess);
        assertEquals(guess.getResult(), "e:4:p:0");
    }
    
    @Test 
    public void testAddPartialGuess() throws InvalidGuessException, InvalidGameException {
        
    }
    
    @Test
    public void testAddBadGuess() throws InvalidGuessException, InvalidGameException {
        
    }
    
    @Test
    public void testInvalidGameException() throws InvalidGameException {
        
    }


    
    

}
