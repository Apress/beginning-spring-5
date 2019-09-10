package com.bsg5.chapter9.live;

import com.bsg5.chapter9.jpa.MusicService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
public class JpaAppRunner implements ApplicationRunner {
    @Autowired
    MusicService musicService;

    Logger log = LoggerFactory.getLogger(this.getClass());

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String artistName = "Artist Known as " + System.currentTimeMillis();
        // force creation of a new artist for this run
        musicService.getArtist(artistName);

        for (String artist : musicService.getMatchingArtistNames("")) {
            log.error(artist);
        }
    }
}
