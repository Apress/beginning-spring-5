package com.bsg5.chapter4;

import com.bsg5.chapter4.model.Artist;
import com.bsg5.chapter4.model.Song;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class AbstractMusicService implements MusicService {
    private Map<String, Artist> bands = new HashMap<>();

    protected String transformArtist(String input) {
        return input;
    }

    protected String transformSong(String input) {
        return input;
    }


    private Artist getArtist(String name) {
        String normalizedName = transformArtist(name);
        return bands.computeIfAbsent(normalizedName,
                s -> new Artist(normalizedName));
    }

    @Override
    public Song getSong(String artistName, String name) {
        Artist artist = getArtist(artistName);
        String normalizedTitle = transformSong(name);
        return artist
                .getSongs()
                .computeIfAbsent(normalizedTitle, Song::new);
    }

    @Override
    public List<Song> getSongsForArtist(String artist) {
        List<Song> songs = new ArrayList<>(
                getArtist(artist)
                        .getSongs()
                        .values()
        );
        songs.sort(Song::compareTo);
        return songs;
    }

    @Override
    public List<String> getMatchingSongNamesForArtist(String artist,
                                                      String prefix) {
        String normalizedPrefix = transformSong(prefix)
                .toLowerCase();
        return getArtist(artist)
                .getSongs()
                .keySet()
                .stream()
                .map(this::transformSong)
                .filter(name -> name
                        .toLowerCase()
                        .startsWith(normalizedPrefix))
                .sorted(Comparator.comparing(Function.identity()))
                .collect(Collectors.toList());
    }

    @Override
    public List<String> getMatchingArtistNames(String prefix) {
        String normalizedPrefix = transformArtist(prefix)
                .toLowerCase();
        return bands
                .keySet()
                .stream()
                .filter(name -> name
                        .toLowerCase()
                        .startsWith(normalizedPrefix))
                .sorted(Comparator.comparing(Function.identity()))
                .collect(Collectors.toList());
    }

    @Override
    public Song voteForSong(String artistName, String name) {
        Song song = getSong(artistName, name);
        song.setVotes(song.getVotes() + 1);
        return song;
    }
}
