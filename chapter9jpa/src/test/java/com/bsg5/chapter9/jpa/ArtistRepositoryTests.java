package com.bsg5.chapter9.jpa;

import com.bsg5.chapter9.test.BaseArtistRepositoryTests;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class ArtistRepositoryTests
    extends BaseArtistRepositoryTests<Artist, Integer> {
    protected Artist createArtist(String name) {
        return new Artist(name);
    }
}
