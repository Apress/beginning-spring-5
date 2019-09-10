package com.bsg5.chapter9.jpa;

import com.bsg5.chapter9.common.BaseSongRepository;

public interface SongRepository
    extends BaseSongRepository<Artist, Song, Integer> {
}
