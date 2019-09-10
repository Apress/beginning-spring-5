package com.bsg5.chapter6;

import com.bsg5.chapter3.MusicService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class GetMatchingSongNamesForArtistController {

    @Autowired
    MusicService service;

    @GetMapping("/songnames")
    @ResponseBody
    public ResponseEntity<List<String>> getMatchingSongNamesForArtist(
            @RequestParam(name="artist") String artist,
            @RequestParam(name="prefix") String prefix
    ) {
        List<String> songNames = service.getMatchingSongNamesForArtist(artist, prefix);

        return new ResponseEntity<>(songNames, HttpStatus.OK);
    }
}
