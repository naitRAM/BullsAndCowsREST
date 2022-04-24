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
public class BullsAndCowsServiceImplTest {

    BullsAndCowsServiceImpl service;

    public BullsAndCowsServiceImplTest() {
        service = new BullsAndCowsServiceImpl(new GameDaoStubImpl(), new GuessDaoStubImpl());
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
        Guess guess = new Guess();
        guess.setValue(7436);
        guess.setGameID(1);
        guess.setCount(3);
        guess.setTime(LocalDateTime.now());
        guess = service.addGuess(guess);
        assertEquals(guess.getResult(), "e:0:p:4");
    }

    @Test
    public void testAddBadGuess() throws InvalidGuessException, InvalidGameException  {
        Guess guess = new Guess();
        guess.setValue(2981);
        guess.setGameID(1);
        guess.setCount(3);
        guess.setTime(LocalDateTime.now());

        guess = service.addGuess(guess);
        assertEquals(guess.getResult(), "e:0:p:0");
    }

    @Test
    public void testInvalidGameException()  {
        Guess guess = new Guess();
        int invalidGameID = 2;
        int validValue = 6498;
        guess.setGameID(invalidGameID);
        guess.setValue(validValue);
        
        try {
            service.addGuess(guess);
            fail("InvalidGameException should have been thrown");
        } catch (InvalidGameException ex) {
            return;
        } catch (InvalidGuessException ex) {
            fail("Incorrect Exception thrown");
        }
    }
    
    @Test 
    public void testInvalidGuessException() {
        Guess guess = new Guess();
        int invalidValue = 694;
        int validGameID = 1;
        guess.setGameID(validGameID);
        guess.setValue(invalidValue);
        try {
            service.addGuess(guess);
            fail("InvalidGuessException should have been thrown");
        } catch (InvalidGuessException ex) {
            return;
        } catch (InvalidGameException ex) {
            fail("Incorrect Exception thrown");
        }
        
    }

}
