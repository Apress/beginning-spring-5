package com.bsg5.chapter3;

import com.bsg5.chapter3.mem03.CapLeadingNormalizer;
import com.bsg5.chapter3.mem03.SimpleNormalizer;
import com.bsg5.chapter3.mem04.MusicService4;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Configuration10 {
    @Bean
    Normalizer foo() {
        return new SimpleNormalizer();
    }

    @Bean
    Normalizer bar() {
        return new CapLeadingNormalizer();
    }

    @Bean
    MusicService musicService(Normalizer bar,
                              @Qualifier("foo")
                                      Normalizer baz) {
        return new MusicService4(bar, baz);
    }
}
