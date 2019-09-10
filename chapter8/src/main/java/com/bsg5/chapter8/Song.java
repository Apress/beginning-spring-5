package com.bsg5.chapter8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
@RequiredArgsConstructor
public class Song {
    Integer id;
    @NonNull
    Integer artistId;
    @NonNull
    String name;
    int votes;
}
