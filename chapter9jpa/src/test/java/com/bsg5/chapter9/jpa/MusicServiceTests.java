package com.bsg5.chapter9.jpa;

import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.bsg5.chapter9.test.BaseMusicServiceTests;

@DataJpaTest
public class MusicServiceTests
    extends BaseMusicServiceTests<Artist, Song, Integer> {
    @Override
    protected Integer getNonexistentId() {
        return 1928491;
    }
}
