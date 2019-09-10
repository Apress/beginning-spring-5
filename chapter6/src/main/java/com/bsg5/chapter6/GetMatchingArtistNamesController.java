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
public class GetMatchingArtistNamesController {

    @Autowired
    MusicService service;

    @GetMapping("/artists")
    @ResponseBody
    public ResponseEntity<List<String>> getMatchingArtistNames(
            @RequestParam(name="prefix") String prefix
    ) {
        List<String> artistNames = service.getMatchingArtistNames(prefix);

        return new ResponseEntity<>(artistNames, HttpStatus.OK);
    }
}
