package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.Availabilities;
import ar.edu.itba.ppc.server.model.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RoomRepository {
    private final Map<Integer, Room> rooms;
    private Integer sizeRoom = 1;

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public RoomRepository() {
        this.rooms = new ConcurrentHashMap<>();
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }


    public Room addRoom() {
        rwLock.writeLock().lock();
        try {
            rooms.put(sizeRoom, new Room(sizeRoom, Availabilities.AVAILABLE.getValue()));
            return rooms.get(sizeRoom++);
        } finally {
            rwLock.writeLock().unlock();
        }
    }
}
