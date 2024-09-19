package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.Availabilities;
import ar.edu.itba.ppc.server.dto.AddRoomResponse;
import ar.edu.itba.ppc.server.dto.InfoDoctorResponse;
import ar.edu.itba.ppc.server.exceptions.DoctorAlreadyExistsException;
import ar.edu.itba.ppc.server.dto.AddDoctorResponse;
import ar.edu.itba.ppc.server.exceptions.DoctorDoesntExistsException;
import ar.edu.itba.ppc.server.exceptions.InvalidAvailabilityParameter;
import ar.edu.itba.ppc.server.exceptions.RoomDoesntExistsException;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.EmeregencyCare;
import ar.edu.itba.ppc.server.model.Room;

import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EmergencyAdminRepository {
    private static final Set<String> VALID_AVAILABILITIES = Set.of("available", "unavailable", "attending");

    private final Map<String, Doctor> doctors;
    private final List<Room> rooms;

    private Integer sizeRoom = 1;

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public EmergencyAdminRepository() {
        doctors = new ConcurrentHashMap<>();
        rooms = new ArrayList<>();
    }

    public AddRoomResponse addRoom() {
        rooms.add(new Room(sizeRoom, Availabilities.AVAILABLE.getValue()));
        return new AddRoomResponse(sizeRoom++, Availabilities.AVAILABLE.getValue());
    }

    public AddDoctorResponse addDoctor(String doctorName, Integer level) {
        if (doctors.containsKey(doctorName) || level < 1 || level > 5) {
            throw new DoctorAlreadyExistsException(doctorName);
        }
        doctors.putIfAbsent(doctorName, new Doctor(doctorName, level, Availabilities.AVAILABLE.getValue()));
        return new AddDoctorResponse(doctorName, level);
    }

    public InfoDoctorResponse setDoctor(String doctorName, String availability) {
        if (!doctors.containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }
        if(!VALID_AVAILABILITIES.contains(availability)){
            throw new InvalidAvailabilityParameter(availability);
        }

        doctors.get(doctorName).setAvailability(availability);
        return new InfoDoctorResponse(doctorName, doctors.get(doctorName).getLevel(), availability);
    }

    public InfoDoctorResponse checkDoctor(String doctorName) {
        if (!doctors.containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }
        return new InfoDoctorResponse(doctorName, doctors.get(doctorName).getLevel(), doctors.get(doctorName).getAvailability());
    }

    public synchronized EmeregencyCare assignRoom(Integer room) {
        if(!rooms.contains(room)) {
            throw new RoomDoesntExistsException(room);
        }

        Optional<Doctor> availableDoctor = doctors.values().stream()
                .filter(doctor -> doctor.getAvailability().equals(Availabilities.AVAILABLE.getValue()))
                .sorted(Comparator.comparing(Doctor::getLevel)
                        .reversed()
                        .thenComparing(Doctor::getDoctorName))
                .findFirst();

        return new EmeregencyCare(room, availableDoctor.get().getDoctorName(), "patient");
    }


}
