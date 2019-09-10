package com.bsg5.chapter8;

import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriUtils;

import java.nio.charset.Charset;
import java.util.List;

@RestController
public class SongController {
    private MusicRepository service;

    SongController(MusicRepository service) {
        this.service = service;
    }

    String decode(Object data) {
        return UriUtils.decode(data.toString(), Charset.defaultCharset());
    }

    @GetMapping(value="/artists/{name}/vote/{title}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    Song voteForSong(@PathVariable String name, @PathVariable String title) {
        return service.voteForSong(decode(name), decode(title));
    }

    @GetMapping(value="/artists/{name}/song/{title}",
        produces = MediaType.APPLICATION_JSON_VALUE)
    Song getSong(@PathVariable String name, @PathVariable String title) {
        return service.getSong(decode(name), decode(title));
    }

    @GetMapping(value="/artists/{name}/songs",
        produces = MediaType.APPLICATION_JSON_VALUE)
    List<Song> getSongsForArtist(@PathVariable String name) {
        return service.getSongsForArtist(decode(name));
    }

    @GetMapping(value={"/artists/{name}/match/{title}",
        "/artists/{name}/match/"},
        produces = MediaType.APPLICATION_JSON_VALUE)
    List<String> findSongsForArtist(@PathVariable String name,
                                    @PathVariable(required = false)
                                        String title) {
        return service.getMatchingSongNamesForArtist(decode(name),
            title != null ? decode(title) : "");
    }
}
