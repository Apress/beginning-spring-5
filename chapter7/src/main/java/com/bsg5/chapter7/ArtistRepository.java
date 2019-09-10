package com.bsg5.chapter7;

import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@Repository
public class ArtistRepository {
    private DataSource dataSource;

    public ArtistRepository(DataSource dataSource) {
        this.dataSource = dataSource;
    }

    public Artist findArtistById(int id) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findArtistById(conn, id);
        }
    }

    private Artist findArtistById(Connection conn, int id) {
        String sql = "SELECT * FROM artists WHERE id=?";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setInt(1, id);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Artist(id, rs.getString("name"));
                } else {
                    throw new ArtistNotFoundException(id +
                        " not found in artist database");
                }
            }
        } catch (SQLException e) {
            throw new ArtistNotFoundException(e);
        }
    }

    public Artist saveArtist(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            try {
                return saveArtist(conn, name);
            } catch (SQLException e) {
                return findArtistByName(conn, name);
            }
        }
    }

    private Artist saveArtist(Connection conn, String name)
        throws SQLException {
        String sql = "INSERT INTO ARTISTS (NAME) VALUES (?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            ps.executeUpdate();
            try (ResultSet rs = ps.getGeneratedKeys()) {
                rs.next();
                return new Artist(rs.getInt(1), name);
            }
        }
    }

    public Artist findArtistByName(String name) throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findArtistByName(conn, name);
        }
    }

    private Artist findArtistByName(
            Connection conn,
            String name
    ) throws SQLException {
        String sql = "SELECT * FROM artists WHERE LOWER(name)=LOWER(?)";
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name);
            try (ResultSet rs = ps.executeQuery()) {
                if (rs.next()) {
                    return new Artist(
                            rs.getInt("id"),
                            rs.getString("name")
                    );
                } else {
                    throw new ArtistNotFoundException(name +
                        " not found in artist database");
                }
            }
        }
    }

    public List<Artist> findAllArtistsByName(String name)
        throws SQLException {
        try (Connection conn = dataSource.getConnection()) {
            return findAllArtistsByName(conn, name);
        }
    }

    private List<Artist> findAllArtistsByName(
            Connection conn,
            String name
    ) throws SQLException {
        String sql = "SELECT * FROM artists WHERE LOWER(name) LIKE LOWER(?)"
                + " ORDER BY name";
        List<Artist> artists = new ArrayList<>();
        try (PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, name + "%");
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    artists.add(new Artist(
                            rs.getInt("id"),
                            rs.getString("name")
                    ));
                }
            }
        }
        return artists;
    }
}
