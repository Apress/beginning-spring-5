package com.bsg5.chapter9.jpa;

import com.bsg5.chapter9.common.WildcardConverter;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Bean;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootConfiguration
@EnableJpaRepositories
@EntityScan
public class JpaConfiguration {
    @Bean
    WildcardConverter converter() {
        return new WildcardConverter("%");
    }

    @Bean
    MusicService musicService(
        ArtistRepository artistRepository,
        SongRepository songRepository,
        WildcardConverter converter
    ) {
        return new MusicService(artistRepository, songRepository, converter);
    }
}
