package com.bsg5.chapter8;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;
import java.util.List;

@RestController
public class SongController {
    @Autowired
    private MusicService service;

    String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping("/artist/{name}/vote/{title}")
    Song voteForSong(@PathVariable String name, @PathVariable String title) {
        return service.voteForSong(decode(name), decode(title));
    }

    @GetMapping("/artist/{name}/song/{title}")
    Song getSong(@PathVariable String name, @PathVariable String title) {
        return service.getSong(decode(name), decode(title));
    }

    @GetMapping("/artist/{name}/songs")
    List<Song> getSongsForArtist(@PathVariable String name) {
        return service.getSongsForArtist(decode(name));
    }

    @GetMapping({"/artist/{name}/match/{title}", "/artist/{name}/match/"})
    List<String> findSongsForArtist(@PathVariable String name,
                                    @PathVariable(required = false) String title) {
        return service.getMatchingSongNamesForArtist(decode(name),
                title != null ? decode(title) : "");
    }
}
