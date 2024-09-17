package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.exceptions.DoctorAlreadyExistsException;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.EmeregencyCare;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

public class EmergencyAdminRepository {
    private static final Set<String> VALID_AVAILABILITIES = Set.of("available", "unavailable", "attending");

    private final Map<String, Doctor> doctors;
    private final List<Integer> rooms;

    private Integer sizeRoom = 0;

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public List<Integer> getRooms() {
        return rooms;
    }

    public EmergencyAdminRepository() {
        doctors = new ConcurrentHashMap<>();
        rooms = new ArrayList<>();
    }

    public Integer addRoom() {
        rooms.add(sizeRoom++);
        return sizeRoom;
    }

    public Integer addDoctor(String doctorName, Integer level) {
        if (doctors.containsKey(doctorName) || level < 1 || level > 5) {
            throw  new DoctorAlreadyExistsException(doctorName);
        }
        doctors.putIfAbsent(doctorName, new Doctor(doctorName, level, "available"));
        return level;
    }

    public String setDoctor(String doctorName, String availability) {
        if (!doctors.containsKey(doctorName) || VALID_AVAILABILITIES.contains(availability)) {
            return null;
        }
        doctors.get(doctorName).setAvailability(availability);
        return availability;
    }

    public String checkDoctor(String doctorName) {
        if (!doctors.containsKey(doctorName)) {
            return null;
        }
        return doctors.get(doctorName).getAvailability();
    }

    public synchronized EmeregencyCare assignRoom(Integer room) {
        if(!rooms.contains(room)) {
            return null;
        }

        for (Doctor doctor : doctors.values()) {
            if (doctor.getAvailability().equals("available")) {
                doctor.setAvailability("attending");
                return new EmeregencyCare(room, doctor.getDoctorName(), "patient");
            }
        }

        return null;
    }


}
