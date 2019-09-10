package com.bsg5.chapter9.jpa;

import com.bsg5.chapter9.common.BaseArtist;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@RequiredArgsConstructor
@EqualsAndHashCode(exclude = "songs")
public class Artist implements BaseArtist<Integer> {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    Integer id;
    @NonNull
    String name;
    @OneToMany(
        cascade = CascadeType.ALL,
        mappedBy = "artist",
        fetch = FetchType.EAGER
    )
    @OrderBy("votes DESC")
    @JsonIgnore
    List<Song> songs = new ArrayList<>();
}
