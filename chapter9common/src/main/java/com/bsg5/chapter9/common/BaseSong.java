package com.bsg5.chapter9.common;

public interface BaseSong<
    T extends BaseArtist<ID>,
    ID
    > extends BaseEntity<ID> {
    T getArtist();

    void setArtist(T artist);

    /**
     * Get the song name
     */
    String getName();

    void setName(String name);

    int getVotes();

    void setVotes(int votes);
}
