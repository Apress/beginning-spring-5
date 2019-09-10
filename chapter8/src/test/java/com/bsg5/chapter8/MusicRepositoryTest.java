package com.bsg5.chapter8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.testng.Assert.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class MusicRepositoryTest extends AbstractTestNGSpringContextTests {
    @Autowired
    MusicRepository musicRepository;

    @Autowired
    JdbcTemplate jdbcTemplate;

    private Object[][] model = new Object[][]{
            {"Threadbare Loaf", "Someone Stole the Flour", 4},
            {"Threadbare Loaf", "What Happened To Our First CD?", 17},
            {"Therapy Zeppelin", "Medium", 4},
            {"Clancy in Silt", "Igneous", 5}
    };

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    void populateData() {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                musicRepository.voteForSong((String) data[0], (String) data[1]);
            }
        });
    }

    @BeforeMethod
    void clearDatabase() {
        jdbcTemplate.update("DELETE FROM songs");
        jdbcTemplate.update("DELETE FROM artists");
        populateData();
    }

    @Test
    void testSongVoting() {
        iterateOverModel(data ->
                assertEquals(
                        musicRepository.getSong((String) data[0],
                                (String) data[1]).getVotes(),
                        ((Integer) data[2]).intValue()
                ));
    }

    @Test
    void testSongsForArtist() {
        List<Song> songs = musicRepository.getSongsForArtist("Threadbare Loaf");
        assertEquals(songs.size(), 2);
        assertEquals(songs.get(0).getName(), "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(), 17);
        assertEquals(songs.get(1).getName(), "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(), 4);
    }

    @Test
    void testMatchingArtistNames() {
        List<String> names = musicRepository.getMatchingArtistNames("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    @Test
    void testMatchingSongNamesForArtist() {
        List<String> names = musicRepository.getMatchingSongNamesForArtist(
                "Threadbare Loaf", "W"
        );
        assertEquals(names.size(), 1);
        assertEquals(names.get(0), "What Happened To Our First CD?");
    }
}
