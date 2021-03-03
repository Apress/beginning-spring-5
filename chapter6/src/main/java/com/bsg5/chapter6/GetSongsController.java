package com.bsg5.chapter6;

import com.bsg5.chapter3.MusicService;
import com.bsg5.chapter3.model.Song;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.List;

@Controller
public class GetSongsController {

    @Autowired
    MusicService service;

    @GetMapping("/artists/{artist}/songs/{name}")
    @ResponseBody
    public ResponseEntity<Song> getSong(
            @PathVariable("artist") final String artist,
            @PathVariable("name") final String name
    ) throws UnsupportedEncodingException {
        String artistDecoded = URLDecoder.decode(artist, "UTF-8");
        String nameDecoded = URLDecoder.decode(name, "UTF-8");
        Song song = service.getSong(artistDecoded, nameDecoded);

        return new ResponseEntity<>(song, HttpStatus.OK);
    }

    @GetMapping("/songs")
    @ResponseBody
    public ResponseEntity<List<Song>> getSongsByArtist(
            @RequestParam(name="artist") String artist
    ) {
        System.out.println(artist);
        List<Song> data = service.getSongsForArtist(artist);

        return new ResponseEntity<>(data, HttpStatus.OK);
    }

}
