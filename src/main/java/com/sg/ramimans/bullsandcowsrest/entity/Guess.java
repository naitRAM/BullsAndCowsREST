package com.sg.ramimans.bullsandcowsrest.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 *
 * @author Rami Mansieh
 * email: rmansieh@gmail.com
 * data: Apr. 21, 2022
 * purpose: 
 */
public class Guess {
    
    LocalDateTime time;
    int count;
    int value;
    String result;
    int gameID;
    
    public Guess (LocalDateTime time, int value, String result, int gameID) {
        this.time = time;
        this.value = value;
        this.result = result;
        this.gameID = gameID;
    }
    
    public Guess (){
        
    }

    public LocalDateTime getTime() {
        return time;
    }

    public void setTime(LocalDateTime time) {
        this.time = time;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
    }

    public int getGameID() {
        return gameID;
    }

    public void setGameID(int gameID) {
        this.gameID = gameID;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 71 * hash + Objects.hashCode(this.time);
        hash = 71 * hash + this.count;
        hash = 71 * hash + this.value;
        hash = 71 * hash + Objects.hashCode(this.result);
        hash = 71 * hash + this.gameID;
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
        final Guess other = (Guess) obj;
        if (this.count != other.count) {
            return false;
        }
        if (this.value != other.value) {
            return false;
        }
        if (this.gameID != other.gameID) {
            return false;
        }
        if (!Objects.equals(this.result, other.result)) {
            return false;
        }
        if (!Objects.equals(this.time, other.time)) {
            return false;
        }
        return true;
    }

    
}
