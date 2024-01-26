package org.example;


public class Utils {
    public static <K> int getHash(K key) {
        return (int)Math.abs(key.hashCode());
    }
}