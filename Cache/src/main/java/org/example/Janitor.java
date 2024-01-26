package org.example;


import java.util.List;
import java.util.Map;

public class Janitor {

    private static Janitor janitor;

    public static Janitor getInstance() {
        synchronized (Janitor.class){
            if(janitor == null){
                synchronized (Janitor.class){
                    janitor = new Janitor();
                }
            }
        }
        return janitor;
    }

    public <K,V> void clean(Cache<K,V> cache){
        List<K> keys = cache.getExpiredKeys(System.currentTimeMillis());
        for(K key : keys) {
            int threadId = Utils.getHash(key) % KeyedExecutor.getInstance().size();
            Runnable runnable = new Runnable() {
                @Override
                public void run() {
                    try {
                        cache.remove(key);
                    }
                    catch (Exception e){
                        System.out.println("Given Key doesnt exist");
                    }
                }
            };
            KeyedExecutor.getInstance().submit(threadId,runnable);
        }
    }
}