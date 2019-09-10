package com.bsg5.chapter3;

public interface Normalizer {
    default String transform(String input) {
        return input.trim();
    }
}
