package com.bsg5.chapter9.common;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface BaseSongRepository<
    A extends BaseArtist<ID>,
    S extends BaseSong<A, ID>,
    ID
    > extends CrudRepository<S, ID> {
    Optional<S> findByArtistIdAndNameIgnoreCase(
            ID artistId, String name
    );

    List<S> findByArtistIdOrderByVotesDesc(ID artistId);

    List<S> findByArtistIdAndNameLikeIgnoreCaseOrderByNameDesc(
            ID artistId, String name
    );
}
