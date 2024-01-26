package org.example;


import java.util.*;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
public class Database {


    private static Database instance;

    public static Database getInstance() {
        synchronized (Database.class){
            if(instance == null){
                synchronized (Database.class){
                    instance = new Database();
                }
            }
        }
        return instance;
    }
    private Set<String> keys;

    public Database(){
        keys = new HashSet<>();
    }


    public <K,V> Future<Void> store(K key,Record<V> record){
        keys.add(key.toString());
        return CompletableFuture.completedFuture(null);
    }
}