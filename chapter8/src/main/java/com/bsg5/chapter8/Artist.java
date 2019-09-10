package com.bsg5.chapter8;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.lang.NonNull;

@Data
@AllArgsConstructor
@RequiredArgsConstructor
@NoArgsConstructor
public class Artist {
    Integer id;
    @NonNull
    String name;
}
