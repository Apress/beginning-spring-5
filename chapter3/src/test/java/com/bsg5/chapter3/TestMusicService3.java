package com.bsg5.chapter3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.testng.AbstractTestNGSpringContextTests;
import org.testng.annotations.Test;

@ContextConfiguration(locations = "/config-03.xml")
public class TestMusicService3 extends AbstractTestNGSpringContextTests {
    @Autowired
    MusicService service;

    MusicServiceTests tests = new MusicServiceTests();

    @Test
    public void testSongVoting() {
        tests.testSongVoting(service);
    }

    @Test
    public void testGetMatchingArtistNames() {
        tests.testMatchingArtistNames(service);
    }

    @Test
    public void testGetSongsForArtist() {
        tests.testSongsForArtist(service);
    }

    @Test
    public void testMatchingSongNamesForArtist() {
        tests.testMatchingSongNamesForArtist(service);
    }

}
