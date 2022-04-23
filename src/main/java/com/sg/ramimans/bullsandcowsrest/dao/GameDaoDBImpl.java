package com.sg.ramimans.bullsandcowsrest.dao;

import com.sg.ramimans.bullsandcowsrest.dao.GuessDaoDBImpl.GuessMapper;
import com.sg.ramimans.bullsandcowsrest.entity.Game;
import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rami Mansieh email: rmansieh@gmail.com data: Apr. 22, 2022 purpose:
 */
@Repository
public class GameDaoDBImpl implements GameDao {

    @Autowired
    private final JdbcTemplate jdbct;

    public GameDaoDBImpl(JdbcTemplate jdbct) {
        this.jdbct = jdbct;
    }

    private Game setGuessList(Game game) {
        final String sql = "SELECT * FROM Guess WHERE GameID = ? ORDER BY Time ASC;";
        List<Guess> guesses = jdbct.query(sql, new GuessMapper(), game.getGameID());
        int count = 1;
        for (Guess guess : guesses) {
            guess.setCount(count++);
        }
        game.setGuesses(guesses);
        return game;
        
    }
    @Override
    public Game getGameByID(int id) {
        final String sql = "SELECT * FROM Game WHERE Game.ID = ?;";
        Game game;
        try {
            game = jdbct.queryForObject(sql, new GameMapper(), id);
            setGuessList(game);
            return game;
        } catch (DataAccessException e) {
            return null;
        }

    }

    @Override
    public List<Game> getGames() {
        final String sql = "SELECT * FROM Game;";
        List<Game> games = jdbct.query(sql, new GameMapper());
        for (Game game : games) {
            setGuessList(game);
        }
        return games;
    }

    @Override
    public Game addGame(Game game) {
        final String sql = "INSERT INTO Game (Answer, Status) VALUES (?, ?);";
        GeneratedKeyHolder keyHolder = new GeneratedKeyHolder();
        jdbct.update((Connection conn) -> {
            PreparedStatement statement = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            statement.setInt(1, game.getAnswer());
            statement.setString(2, game.getStatus());
            return statement;
        }, keyHolder);
        game.setID(keyHolder.getKey().intValue());

        return game;
    }

    @Override
    public boolean updateGame(Game game) {
        final String sql = "UPDATE Game SET "
                + "Status = 'FINISHED'"
                + "WHERE Game.ID = ?;";
        return jdbct.update(sql, game.getGameID()) == 1;
    }

    @Transactional
    @Override
    public boolean deleteGameByID(int id) {
        final String sqlGuess = "DELETE FROM Guess WHERE GameID = ?;";
        final String sqlGame = "DELETE FROM Game WHERE Game.ID = ?;";
        jdbct.update(sqlGuess, id);
        return jdbct.update(sqlGame, id) == 1;
    }

    public static final class GameMapper implements RowMapper<Game> {

        @Override
        public Game mapRow(ResultSet rs, int i) throws SQLException {
            return new Game(rs.getInt("ID"), rs.getInt("Answer"), rs.getString("Status"));
        }

    }
}
