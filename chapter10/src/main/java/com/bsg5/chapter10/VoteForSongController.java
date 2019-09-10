package com.bsg5.chapter10;

import com.bsg5.chapter3.MusicService;
import com.bsg5.chapter3.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class VoteForSongController {
    @Autowired
    MusicService service;

    @GetMapping("/vote")
    private Song voteForSong(
            @RequestParam(name = "artist") String artist,
            @RequestParam(name = "song") String song
    ) {
//        return Mono.just(service.voteForSong(artist, song));

        return null;
    }
}
