package com.bsg5.chapter9.mongodb;

import com.bsg5.chapter9.common.BaseSongRepository;
import org.springframework.stereotype.Repository;

public interface SongRepository
    extends BaseSongRepository<Artist, Song, String> {
}

