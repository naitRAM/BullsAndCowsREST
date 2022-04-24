package com.sg.ramimans.bullsandcowsrest.service;

import com.sg.ramimans.bullsandcowsrest.dao.GameDao;
import com.sg.ramimans.bullsandcowsrest.dao.GuessDao;
import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.util.List;
import java.util.Random;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Rami Mansieh email: rmansieh@gmail.com data: Apr. 22, 2022 purpose:
 */

@Service
public class BullsAndCowsService {

    @Autowired
    private final GameDao gameDao;

    @Autowired
    private final GuessDao guessDao;

    public BullsAndCowsService(GameDao gameDao, GuessDao guessDao) {
        this.gameDao = gameDao;
        this.guessDao = guessDao;
    }

    private int getNumberWithUniqueDigits(int digitCount) {
        digitCount = Math.abs(digitCount % 10);
        Random rand = new Random();
        int[] intArray = new int[digitCount];
        boolean unique = false;

        while (!unique) {
            for (int i = 0; i < intArray.length; i++) {
                if (i == 0) {
                    intArray[i] = rand.nextInt(9) + 1;
                } else {
                    intArray[i] = rand.nextInt(10);
                }
            }
            unique = true;
            for (int i = 0; i < intArray.length; i++) {
                for (int j = 0; j < intArray.length; j++) {
                    if (intArray[i] == intArray[j] && i != j) {
                        unique = false;
                    }
                }
            }
        }

        int multiplier = 1;
        int sum = 0;
        for (int i = intArray.length - 1; i >= 0; i--) {
            sum += intArray[i] * multiplier;
            multiplier *= 10;
        }
        return sum;
    }

    private String processResult(int guess, int answer) throws InvalidGuessException {
        String guessStr = String.valueOf(guess);
        String answerStr = String.valueOf(answer);
        int exact = 0;
        int partial = 0;
        if (guessStr.length() != answerStr.length()) {
            throw new InvalidGuessException(String.format("Error: please provide a 'value' with %S digits", guessStr.length(), answerStr.length()));
        }
        for (int i = 0; i < guessStr.length(); i++) {
            for (int j = 0; j < guessStr.length(); j++) {
                if (i != j) {
                    if (guessStr.charAt(i) == answerStr.charAt(j)) {
                        partial++;
                    }
                } else {
                    if (guessStr.charAt(i) == answerStr.charAt(j)) {
                        exact++;
                    }
                }
            }
        }
        return String.format("e:%S:p:%S", exact, partial);
    }

    public Game addGame() {
        Game game = new Game(getNumberWithUniqueDigits(4));
        game.setStatus("IN PROGRESS");
        game = gameDao.addGame(game);

        return game;
    }

    public List<Game> getGames() {
        List<Game> games = gameDao.getGames();
        return games;
    }

    public Guess addGuess(Guess guess) throws InvalidGuessException, InvalidGameException {
        int gameID = guess.getGameID();
        if (gameID == 0) {
            throw new InvalidGuessException("Please provide 'gameID'");
        }
        Game gameToGuess = gameDao.getGameByID(gameID);
        if (gameToGuess == null) {
            throw new InvalidGameException("No game exists with gameID" + gameID);
        }
        if (gameToGuess.getStatus().equals("FINISHED")) {
            throw new InvalidGameException("Game with gameID " + gameToGuess.getGameID() + " is complete");
        } 
        int guessValue = guess.getValue();
        if (guess.getValue() == 0) {
            throw new InvalidGuessException("Please provide 'value' for guess");
        }
        int answer = gameToGuess.getAnswer();

        guess.setResult(processResult(guessValue, answer));
        if (guess.getValue() == gameToGuess.getAnswer()) {
            gameToGuess.setStatus("FINISHED");
            gameDao.updateGame(gameToGuess);
        }
        
        return guessDao.addGuess(guess);
    }

    public List<Guess> getGuessesForGame(int gameID) throws InvalidGameException {
        Game game = gameDao.getGameByID(gameID);
        if (game == null) {
            throw new InvalidGameException("No game exists with gameID " + gameID);
        }
        return guessDao.getGuessesByGameID(gameID);
    }

    public Game getGame(int gameID) throws InvalidGameException {
        Game game = gameDao.getGameByID(gameID);
        if (game == null) {
            throw new InvalidGameException("No game exists with gameID " + gameID);
        }
        return game;
    }
}
