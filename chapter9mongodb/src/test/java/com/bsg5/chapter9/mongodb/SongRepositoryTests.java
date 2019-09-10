package com.bsg5.chapter9.mongodb;

import com.bsg5.chapter9.test.BaseSongRepositoryTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

@DataMongoTest
public class SongRepositoryTests
    extends BaseSongRepositoryTests<Artist, Song, String> {
    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
