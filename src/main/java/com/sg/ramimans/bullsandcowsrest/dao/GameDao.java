/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.sg.ramimans.bullsandcowsrest.dao;

import com.sg.ramimans.bullsandcowsrest.entity.Game;
import java.util.List;

/**
 *
 * @author rmans
 */
public interface GameDao {
    public Game getGameByID (int id);
    public List<Game> getGames();
    public Game addGame(Game game);
    public boolean updateGame(Game game);
    public boolean deleteGameByID(int id);
}
