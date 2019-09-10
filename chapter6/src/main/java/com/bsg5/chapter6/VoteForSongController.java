package com.bsg5.chapter6;

import com.bsg5.chapter3.MusicService;
import com.bsg5.chapter3.model.Song;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class VoteForSongController {

    @Autowired
    MusicService service;

    @PostMapping("/vote")
    @ResponseBody
    public ResponseEntity<Song> vote(
            @RequestParam(name = "artist") String artist,
            @RequestParam(name = "song") String song
    ) {
        service.voteForSong(artist, song);

        return new ResponseEntity<>(service.getSong(artist, song), HttpStatus.ACCEPTED);
    }
}
