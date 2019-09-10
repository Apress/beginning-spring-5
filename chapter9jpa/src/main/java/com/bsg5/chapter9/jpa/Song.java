package com.bsg5.chapter9.jpa;

import com.bsg5.chapter9.common.BaseSong;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@AllArgsConstructor
@Table(indexes = {
    @Index(
        name = "artist_song",
        columnList = "artist_id,name",
        unique = true
    )
})
public class Song implements BaseSong<Artist, Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @ManyToOne
    @NonNull
    Artist artist;
    @NonNull
    String name;
    int votes;
}
