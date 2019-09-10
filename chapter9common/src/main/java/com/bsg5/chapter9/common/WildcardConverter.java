package com.bsg5.chapter9.common;

public class WildcardConverter {
    private final String append;

    public WildcardConverter(String append) {
        this.append = append;
    }

    public String convertToWildCard(String data) {
        return data + append;
    }
}
