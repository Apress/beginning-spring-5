package com.bsg5.chapter8;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import java.sql.ResultSet;
import java.sql.SQLException;

@Component
public class SongRowMapper implements RowMapper<Song> {
    @Override
    public Song mapRow(ResultSet rs, int rowNum) throws SQLException {
        return new Song(
                rs.getInt("id"),
                rs.getInt("artist_id"),
                rs.getString("name"),
                rs.getInt("votes")
        );
    }
}
