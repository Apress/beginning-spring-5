package com.bsg5.chapter8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.springframework.web.util.UriUtils;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.nio.charset.Charset;
import java.util.List;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SongControllerTest extends AbstractTestNGSpringContextTests {
    @LocalServerPort
    private int port;
    @Autowired
    private TestRestTemplate restTemplate;
    @Autowired
    JdbcTemplate jdbcTemplate;

    private Object[][] model = new Object[][]{
            {"Threadbare Loaf", "Someone Stole the Flour", 4},
            {"Threadbare Loaf", "What Happened To Our First CD?", 17},
            {"Therapy Zeppelin", "Medium", 4},
            {"Clancy in Silt", "Igneous", 5}
    };

    @BeforeMethod
    void clearDatabase() {
        jdbcTemplate.update("DELETE FROM songs");
        jdbcTemplate.update("DELETE FROM artists");
        populateData();
    }

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    String encode(Object data) {
        return UriUtils.encode(data.toString(), Charset.defaultCharset());
    }

    void populateData() {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                String url = "http://localhost:"
                    + port
                    + "/artists/"
                    + encode(data[0])
                    + "/vote/"
                    + encode(data[1]);

                ResponseEntity<Song> response =
                    restTemplate.getForEntity(url, Song.class);
                assertEquals(response.getStatusCode(), HttpStatus.OK);
            }
        });
    }

    @Test
    void testSongVoting() {
        iterateOverModel(data -> {
            String url = "http://localhost:"
                + port
                + "/artists/"
                + encode(data[0])
                + "/song/"
                + encode(data[1]);

            ResponseEntity<Song> response =
                restTemplate.getForEntity(url, Song.class);
            assertEquals(response.getStatusCode(), HttpStatus.OK);
            Song song = response.getBody();
            assertNotNull(song);
            assertEquals(song.getName(), data[1]);
            assertEquals(song.getVotes(), ((Integer) data[2]).intValue());
        });
    }

    @Test
    void testSongsForArtist() {
        ParameterizedTypeReference<List<Song>> type =
            new ParameterizedTypeReference<>() {
        };
        String url = "http://localhost:"
            + port
            + "/artists/"
            + encode("Threadbare Loaf")
            + "/songs";
        ResponseEntity<List<Song>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                type
        );
        assertEquals(response.getStatusCode(), HttpStatus.OK);
        List<Song> songs =response.getBody();

        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    @Test
    void testMatchingSongNamesForArtist() {
        ParameterizedTypeReference<List<String>> type =
            new ParameterizedTypeReference<>() {
        };
        String url = "http://localhost:"
            + port
            + "/artists/"
            + encode("Threadbare Loaf")
            + "/match/" +
            encode("W");
        ResponseEntity<List<String>> response = restTemplate.exchange(
                url,
                HttpMethod.GET,
                null,
                type
        );

        List<String> names = response.getBody();
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD?");
    }
}


