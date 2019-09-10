package com.bsg5.chapter4;

public interface Normalizer {
    default String transform(String input) {
        return input.trim();
    }
}
