package org.example.api;

import org.example.*;

import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;

public class CacheEngine<K,V> {

    private Cache<K,V> cache;

    public CacheEngine(long expiry, int maxSize, EvictionAlgorithmType evictionAlgorithmType, PersistanceAlgorithmType persistanceAlgorithmType,int maxStaleUpdates){
        this.cache = new Cache<K,V>(evictionAlgorithmType,persistanceAlgorithmType,maxSize,expiry,maxStaleUpdates);
    }


    public CompletableFuture<V> get(K key) throws Exception {
        Janitor.getInstance().clean(this.cache);
        if(cache==null){throw new Exception("Cache not initalized!");}
        int threadId = Utils.getHash(key) % KeyedExecutor.getInstance().size();
        Supplier<V> supplier = new Supplier<V>() {
            @Override
            public V get() {
                try {
                    return cache.get(key);
                }
                catch (Exception e){
                    return null;
                }
            }
        };
        return KeyedExecutor.getInstance().submit(threadId,supplier);
    }

    public CompletableFuture<Void> set(K key,V value) throws  Exception {
        Janitor.getInstance().clean(this.cache);
        if(cache==null){throw new Exception("Cache not initalized!");}
        int threadId = Utils.getHash(key) % KeyedExecutor.getInstance().size();
        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                cache.set(key,value);
            }
        };
        return KeyedExecutor.getInstance().submit(threadId,runnable);
    }

}