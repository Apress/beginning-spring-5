package com.bsg5.chapter8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;
import java.sql.SQLException;
import java.util.List;

@RestController
public class ArtistController {
    @Autowired
    private MusicService service;

    String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping("/artist/{id}")
    Artist findArtistById(@PathVariable int id) {
        Artist artist = service.findArtistById(id);
        if (artist != null) {
            return artist;
        } else {
            throw new ArtistNotFoundException();
        }
    }

    @GetMapping({"/artist/search/{name}", "/artist/search/"})
    Artist findArtistByName(
            @PathVariable(required = false) String name
    ) {
        if (name != null) {
            Artist artist= service.findArtistByName(decode(name), false);
            if(artist!=null) {
                return artist;
            } else {
                throw new ArtistNotFoundException();
            }
        } else {
            throw new IllegalArgumentException("No artist name submitted");
        }
    }

    @PostMapping("/artist")
    Artist saveArtist(@RequestBody Artist artist) {
        return service.findArtistByName(artist.getName());
    }

    @GetMapping({"/artist/match/{name}", "/artist/match/"})
    List<String> findArtistByMatchingName(
            @PathVariable(required = false)
                    String name
    ) {
        return service.getMatchingArtistNames(name != null ? decode(name) : "");
    }
}
