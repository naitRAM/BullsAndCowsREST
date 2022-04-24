package com.sg.ramimans.bullsandcowsrest.dao;

import com.sg.ramimans.bullsandcowsrest.entity.Guess;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Rami Mansieh email: rmansieh@gmail.com data: Apr. 22, 2022 purpose:
 */
@Repository
public class GuessDaoDBImpl implements GuessDao {

    @Autowired
    JdbcTemplate jdbct;

    @Override
    public List<Guess> getGuessesByGameID(int id) {
        final String sql = "SELECT * FROM Guess WHERE GameID = ? ORDER BY Time ASC;";
        List<Guess> guesses = jdbct.query(sql, new GuessMapper(), id);
        int count = 1;
        for (Guess guess : guesses) {
            guess.setCount(count++);
        }
        return guesses;
    }

    @Override
    @Transactional
    public Guess addGuess(Guess guess) {
        final String sqlInsert = "INSERT INTO Guess (Time, Value, Result, GameID) values (?,?,?,?);";
        final String sqlGuessCount = "SELECT COUNT(*) FROM Guess WHERE GameID = ?;";
        jdbct.update(sqlInsert, Timestamp.valueOf(guess.getTime()), guess.getValue(), guess.getResult(), guess.getGameID());
        int guessesForGame = jdbct.queryForObject(sqlGuessCount, Integer.class, guess.getGameID());
        guess.setCount(guessesForGame);
        return guess;
    }

    public static final class GuessMapper implements RowMapper<Guess> {

        @Override
        public Guess mapRow(ResultSet rs, int i) throws SQLException {
            return new Guess(rs.getTimestamp("Time").toLocalDateTime(), rs.getInt("Value"), rs.getString("Result"), rs.getInt("GameID"));
        }

    }
}
