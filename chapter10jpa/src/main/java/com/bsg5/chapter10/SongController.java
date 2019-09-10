package com.bsg5.chapter10;

import com.bsg5.chapter9.jpa.MusicService;
import com.bsg5.chapter9.jpa.Song;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SongController {

    private MusicService service;

    SongController(MusicService service) {
        this.service = service;
    }

    @GetMapping(value = "/songs/{id}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    Song getSongById(@PathVariable int id) {
        Song song = service.getSongById(id);

        if (song != null) {
            return song;
        } else {
            throw new SongNotFoundException();
        }
    }

    @PostMapping(value="/songs",
        consumes = MediaType.APPLICATION_JSON_VALUE,
        produces = MediaType.APPLICATION_JSON_VALUE)
    Song saveSong(@RequestBody Song song) {
        Song songLookup  = service.getSong(song.getArtist().getName(), song.getName());

        if(songLookup != null) {
            return songLookup;
        } else {
            throw new SongNotFoundException();
        }
    }
}
