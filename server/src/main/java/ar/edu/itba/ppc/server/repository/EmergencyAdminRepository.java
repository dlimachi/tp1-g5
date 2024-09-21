package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.Availabilities;
import ar.edu.itba.ppc.server.dto.AddRoomResponse;
import ar.edu.itba.ppc.server.dto.InfoDoctorResponse;
import ar.edu.itba.ppc.server.exceptions.*;
import ar.edu.itba.ppc.server.dto.AddDoctorResponse;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.dto.EmeregencyCare;
import ar.edu.itba.ppc.server.model.Room;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class EmergencyAdminRepository {
    private static final Set<String> VALID_AVAILABILITIES = Set.of("available", "unavailable", "attending");

    private final Map<String, Doctor> doctors;
    private final Map<Integer, Room> rooms;

    private Integer sizeRoom = 1;

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public Map<Integer, Room> getRooms() {
        return rooms;
    }

    public EmergencyAdminRepository() {
        doctors = new ConcurrentHashMap<>();
        rooms = new ConcurrentHashMap<>();
    }

    public AddRoomResponse addRoom() {
        rooms.put(sizeRoom, new Room(sizeRoom, Availabilities.AVAILABLE.getValue()));
        return new AddRoomResponse(sizeRoom++, Availabilities.AVAILABLE.getValue());
    }

    public AddDoctorResponse addDoctor(String doctorName, Integer level) {
        if (doctors.containsKey(doctorName) || level < 1 || level > 5) {
            throw new DoctorAlreadyExistsException(doctorName);
        }
        doctors.putIfAbsent(doctorName, new Doctor(doctorName, level, Availabilities.AVAILABLE.getValue(), null));
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

    public synchronized EmeregencyCare assignRoomToEmergencyCare(Integer roomId) {
        if(!rooms.containsKey(roomId)) {
            throw new RoomDoesntExistsException(roomId);
        }

        if(rooms.get(roomId).getStatus().equals(Availabilities.ATTENDING.getValue())) {
            throw new RoomAlreadyOccupiedException(roomId);
        }

        Optional<Doctor> availableDoctor = doctors.values().stream()
                .filter(doctor -> doctor.getAvailability().equals(Availabilities.AVAILABLE.getValue()))
                .sorted(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .findFirst();

        if (availableDoctor.isEmpty()) {
            throw new NoAvailableDoctorException(roomId);
        }

        rooms.get(roomId).setStatus(Availabilities.ATTENDING.getValue());
        Doctor doctor = availableDoctor.get();
        doctor.setRoom(roomId.toString());
        doctor.setAvailability(Availabilities.ATTENDING.getValue());

        return new EmeregencyCare(roomId, doctor.getDoctorName(), "patient");
    }

    public synchronized EmeregencyCare endEmergencyCare(Integer roomId, String doctorName, String patientName) {
        if (!doctors.containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }

        Doctor attendingDoctor = doctors.get(doctorName);

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(roomId.toString())) {
            throw new DoctorNotAssignedToRoomException(doctorName, roomId);
        }

        rooms.get(roomId).setStatus(Availabilities.AVAILABLE.getValue());

        attendingDoctor.setAvailability(Availabilities.AVAILABLE.getValue());

        return new EmeregencyCare(roomId, doctorName, "patient");
    }



}
