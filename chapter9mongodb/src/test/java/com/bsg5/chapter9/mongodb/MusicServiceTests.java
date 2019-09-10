package com.bsg5.chapter9.mongodb;

import com.bsg5.chapter9.test.BaseMusicServiceTests;
import org.springframework.boot.test.autoconfigure.data.mongo.DataMongoTest;

import java.util.UUID;

@DataMongoTest
public class MusicServiceTests
    extends BaseMusicServiceTests<Artist, Song, String> {
    @Override
    protected String getNonexistentId() {
        return UUID.randomUUID().toString();
    }
}
