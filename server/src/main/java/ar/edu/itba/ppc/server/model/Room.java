package ar.edu.itba.ppc.server.model;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Room {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    private Integer room;
    private String status;

    public Room(Integer room, String status) {
        this.room = room;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public void lockWriting() {
        lock.writeLock().lock();
    }

    public void unlockWriting() {
        lock.writeLock().unlock();
    }
}
