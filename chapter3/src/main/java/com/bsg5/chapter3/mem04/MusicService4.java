package com.bsg5.chapter3.mem04;

import com.bsg5.chapter3.AbstractMusicService;
import com.bsg5.chapter3.Normalizer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
public class MusicService4 extends AbstractMusicService {
    private final Normalizer artistNormalizer;
    private final Normalizer songNormalizer;

    public MusicService4(@Autowired
                         @Qualifier("bar")
                                 Normalizer artistNormalizer,
                         @Autowired
                         @Qualifier("foo")
                                 Normalizer songNormalizer) {
        this.artistNormalizer = artistNormalizer;
        this.songNormalizer = songNormalizer;
    }

    @Override
    protected String transformArtist(String input) {
        return artistNormalizer.transform(input);
    }

    @Override
    protected String transformSong(String input) {
        return songNormalizer.transform(input);
    }
}
