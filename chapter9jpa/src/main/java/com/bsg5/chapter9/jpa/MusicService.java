package com.bsg5.chapter9.jpa;

import com.bsg5.chapter9.common.BaseMusicService;
import com.bsg5.chapter9.common.WildcardConverter;
import org.springframework.stereotype.Component;

public class MusicService extends BaseMusicService<Artist, Song, Integer> {
    MusicService(
        ArtistRepository artistRepository,
        SongRepository songRepository,
        WildcardConverter converter
    ) {
        super(artistRepository, songRepository, converter);
    }

    @Override
    protected Artist createArtist(String name) {
        return new Artist(name);
    }

    @Override
    protected Song createSong(Artist artist, String name) {
        return new Song(artist, name);
    }
}
