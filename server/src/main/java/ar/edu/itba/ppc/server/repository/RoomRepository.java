package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.EmergencyRoomStatus;
import ar.edu.itba.ppc.server.model.Room;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class RoomRepository {
    private final ConcurrentHashMap<Integer, Room> rooms;
    private Integer sizeRoom = 1;

    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public RoomRepository() {
        this.rooms = new ConcurrentHashMap<>();
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }


    public Room addRoom() {
        rwLock.writeLock().lock();
        try {
            rooms.put(sizeRoom, new Room(sizeRoom, EmergencyRoomStatus.FREE.getValue()));
            return rooms.get(sizeRoom++);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public List<Room> getFreeRooms () {
        return rooms.values().stream()
                .filter(room -> room.getStatus().equals(EmergencyRoomStatus.FREE.getValue()))
                .sorted(Comparator.comparing(Room::getRoom))
                .collect(Collectors.toList());
    }
}
