package com.example.map;

import java.util.HashMap;
import java.util.Map;

/**
 * Flyweight factory — caches MarkerStyle instances by a stable key.
 * Key format: shape|color|size|F_or_O  e.g. "PIN|RED|12|F"
 * Same combination always returns the exact same object from cache.
 */
public class MarkerStyleFactory {

    private final Map<String, MarkerStyle> cache = new HashMap<>();

    public MarkerStyle get(String shape, String color, int size, boolean filled) {
        String key = shape + "|" + color + "|" + size + "|" + (filled ? "F" : "O");
        if (!cache.containsKey(key)) {
            cache.put(key, new MarkerStyle(shape, color, size, filled));
        }
        return cache.get(key);
    }

    public int cacheSize() {
        return cache.size();
    }
}
