package ar.edu.itba.ppc.server.model;


import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class Doctor {
    private final ReadWriteLock lock = new ReentrantReadWriteLock();

    String doctorName;
    Integer level;
    String availability;
    String room;

    public Doctor(String doctorName, Integer level, String available, String room) {
        this.doctorName = doctorName;
        this.level = level;
        this.availability = available;
        this.room = room;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) {
        this.room = room;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }

    public void lockWriting() {
        lock.writeLock().lock();
    }

    public void unlockWriting() {
        lock.writeLock().unlock();
    }
}
