package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.AvailabilityDoctor;
import ar.edu.itba.ppc.server.model.Doctor;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class DoctorRepository {
    private final Map<String, Doctor> doctors;

    private final ReadWriteLock rwLock = new ReentrantReadWriteLock();

    public DoctorRepository() {
        doctors = new ConcurrentHashMap<>();
    }

    public Doctor addDoctor(String doctorName, Integer level) {
        rwLock.writeLock().lock();
        try {
            doctors.putIfAbsent(doctorName, new Doctor(doctorName, level, AvailabilityDoctor.AVAILABLE.getValue(), null));
            return doctors.get(doctorName);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Doctor setAvailabilityDoctor(String doctorName, String availability) {
        rwLock.writeLock().lock();
        try {
            doctors.get(doctorName).setAvailability(availability);
            return doctors.get(doctorName);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Doctor getDoctor(String doctorName) {
        return doctors.get(doctorName);
    }

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

}
