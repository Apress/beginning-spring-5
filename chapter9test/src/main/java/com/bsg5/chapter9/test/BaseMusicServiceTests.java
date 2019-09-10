package com.bsg5.chapter9.test;

import com.bsg5.chapter9.common.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Test;

import java.util.List;
import java.util.function.Consumer;

import static org.testng.Assert.*;

public abstract class BaseMusicServiceTests<
    A extends BaseArtist<ID>,
    S extends BaseSong<A, ID>,
    ID
    > extends AbstractTestNGSpringContextTests {
    @Autowired
    BaseMusicService<A, S, ID> musicService;
    @Autowired
    BaseArtistRepository<A, ID> artistRepository;
    @Autowired
    BaseSongRepository<A, S, ID> songRepository;

    private Object[][] model = new Object[][]{
        {"Threadbare Loaf", "Someone Stole the Flour", 4},
        {"Threadbare Loaf", "What Happened To Our First CD?", 17},
        {"Therapy Zeppelin", "Medium", 4},
        {"Clancy in Silt", "Igneous", 5}
    };

    @BeforeMethod
    public void clearDatabase() {
        songRepository.deleteAll();
        artistRepository.deleteAll();
        populateService();
    }

    protected abstract ID getNonexistentId();

    void iterateOverModel(Consumer<Object[]> consumer) {
        for (Object[] data : model) {
            consumer.accept(data);
        }
    }

    void populateService() {
        iterateOverModel(data -> {
            for (int i = 0; i < (Integer) data[2]; i++) {
                musicService.voteForSong((String) data[0], (String) data[1]);
            }
        });
    }

    @Test
    void testSongVoting() {
        iterateOverModel(data ->
            assertEquals(
                musicService.getSong((String) data[0],
                    (String) data[1]).getVotes(),
                ((Integer) data[2]).intValue()
            ));
    }

    @Test
    void testSongsForArtist() {
        List<S> songs =
            musicService.getSongsForArtist("Threadbare Loaf");
        assertEquals(songs.size(),
            2);
        assertEquals(songs.get(0).getName(),
            "What Happened To Our First CD?");
        assertEquals(songs.get(0).getVotes(),
            17);
        assertEquals(songs.get(1).getName(),
            "Someone Stole the Flour");
        assertEquals(songs.get(1).getVotes(),
            4);
    }

    @Test
    void testMatchingArtistNames() {
        List<String> names = musicService.getMatchingArtistNames("Th");
        assertEquals(names.size(), 2);
        assertEquals(names.get(0), "Therapy Zeppelin");
        assertEquals(names.get(1), "Threadbare Loaf");
    }

    @Test
    void testFindArtistById() {
        A artist = musicService.getArtist("Threadbare Loaf");
        assertNotNull(artist);
        A searched = musicService.getArtistById(artist.getId());
        assertNotNull(searched);
        assertEquals(artist.getName(), searched.getName());
        searched = musicService.getArtistById(getNonexistentId());
        assertNull(searched);
    }

    @Test
    void testFindSongById() {
        S song = musicService.getSong("Therapy Zeppelin",
            "Medium");
        assertNotNull(song);
        S searched = musicService.getSongById(song.getId());
        assertNotNull(searched);
        assertEquals(song.getName(), searched.getName());
        searched = musicService.getSongById(getNonexistentId());
        assertNull(searched);
    }

    @Test
    void testMatchingSongNamesForArtist() {
        List<String> names =
            musicService.getMatchingSongNamesForArtist(
                "Threadbare Loaf", "W"
            );
        assertEquals(names.size(),
            1);
        assertEquals(names.get(0),
            "What Happened To Our First CD?");
    }
}
