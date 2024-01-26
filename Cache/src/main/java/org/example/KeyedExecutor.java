package org.example;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;
import java.util.function.Supplier;

public class KeyedExecutor {
    private Executor[] executors;

    private static KeyedExecutor keyedExecutor;


    public static KeyedExecutor getInstance() {
        synchronized (KeyedExecutor.class){
            if(keyedExecutor == null){
                synchronized (KeyedExecutor.class){
                    keyedExecutor = new KeyedExecutor();
                }
            }
        }
        return keyedExecutor;
    }

    private KeyedExecutor() {
        executors = new Executor[10];
        for(int i=0;i<executors.length;i++)
        {
            executors[i]=Executors.newSingleThreadExecutor();
        }
    }

    public CompletableFuture<Void> submit(int id,Runnable r)
    {
        return CompletableFuture.runAsync(r,executors[id]).toCompletableFuture();
    }
    public <V> CompletableFuture<V> submit(int id,Supplier<V> r)
    {
        return CompletableFuture.supplyAsync(r,executors[id]).toCompletableFuture();
    }
    public Integer size()
    {
        return executors.length;
    }

}