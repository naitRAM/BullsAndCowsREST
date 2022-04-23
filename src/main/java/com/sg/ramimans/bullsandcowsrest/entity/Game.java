package com.sg.ramimans.bullsandcowsrest.entity;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 *
 * @author Rami Mansieh email: rmansieh@gmail.com data: Apr. 21, 2022 purpose:
 */
public class Game {

    int id;
    int answer;
    String status;
    List<Guess> guesses = new ArrayList();

    public Game(int gameID, int answer, String status) {
        this.id = gameID;
        this.answer = answer;
        this.status = status;
    }
    
    public Game(int answer) {
        this.answer = answer;
    }

    public Game() {

    }

    public int getGameID() {
        return id;
    }

    public void setID(int id) {
        this.id= id;
    }

    public int getAnswer() {
        return answer;
    }

    public void setAnswer(int answer) {
        this.answer = answer;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Guess> getGuesses() {
        return guesses;
    }

    public void setGuesses(List<Guess> guesses) {
        this.guesses = guesses;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 41 * hash + this.id;
        hash = 41 * hash + this.answer;
        hash = 41 * hash + Objects.hashCode(this.status);
        hash = 41 * hash + Objects.hashCode(this.guesses);
        return hash;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Game other = (Game) obj;
        if (this.id != other.id) {
            return false;
        }
        if (this.answer != other.answer) {
            return false;
        }
        if (!Objects.equals(this.status, other.status)) {
            return false;
        }
        if (!Objects.equals(this.guesses, other.guesses)) {
            return false;
        }
        return true;
    }

    

    
    
    
}
