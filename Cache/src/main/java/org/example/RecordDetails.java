package org.example;

public class RecordDetails {
    long loadTime;

    long accessCount;

    long accessTime;

    long udpateCount;

    public long getLoadTime() {
        return loadTime;
    }

    public void setLoadTime(long loadTime) {
        this.loadTime = loadTime;
    }

    public long getAccessCount() {
        return accessCount;
    }

    public void setAccessCount(long accessCount) {
        this.accessCount = accessCount;
    }

    public long getAccessTime() {
        return accessTime;
    }

    public void setAccessTime(long accessTime) {
        this.accessTime = accessTime;
    }

    public long getUdpateCount() {
        return udpateCount;
    }

    public void setUdpateCount(long udpateCount) {
        this.udpateCount = udpateCount;
    }

    public RecordDetails(long loadTime, long accessCount, long accessTime, long updateCount) {
        this.loadTime = loadTime;
        this.accessCount = accessCount;
        this.accessTime = accessTime;
        this.udpateCount = updateCount;
    }
}