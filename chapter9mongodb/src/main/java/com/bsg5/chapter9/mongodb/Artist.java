package com.bsg5.chapter9.mongodb;

import com.bsg5.chapter9.common.BaseArtist;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.lang.NonNull;

@Document
@Data
@NoArgsConstructor
@RequiredArgsConstructor
public class Artist implements BaseArtist<String> {
    @Id
    String id;
    @NonNull
    String name;
}
