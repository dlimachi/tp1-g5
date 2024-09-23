package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.Availabilities;
import ar.edu.itba.ppc.server.exceptions.*;
import ar.edu.itba.ppc.server.model.Doctor;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class DoctorRepository {
    private final Map<String, Doctor> doctors;

    public Map<String, Doctor> getDoctors() {
        return doctors;
    }

    public DoctorRepository() {
        doctors = new ConcurrentHashMap<>();
    }

    public Doctor addDoctor(String doctorName, Integer level) {
        if (doctors.containsKey(doctorName) || level < 1 || level > 5) {
            throw new DoctorAlreadyExistsException(doctorName);
        }
        doctors.putIfAbsent(doctorName, new Doctor(doctorName, level, Availabilities.AVAILABLE.getValue(), null));
        return doctors.get(doctorName);
    }

    public Doctor setAvailabilityDoctor(String doctorName, String availability) {
        if (!doctors.containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }
        if(!isAvailabilityValid(availability)) {
            throw new InvalidAvailabilityParameter(availability);
        }

        doctors.get(doctorName).setAvailability(availability);
        return doctors.get(doctorName);
    }

    public Doctor getDoctor(String doctorName) {
        if (!doctors.containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }
        return doctors.get(doctorName);
    }

    private boolean isAvailabilityValid(String availability) {
        return EnumSet.allOf(Availabilities.class)
                .stream()
                .map(Enum::name)
                .anyMatch(name -> name.equalsIgnoreCase(availability));
    }

    /*public synchronized EmeregencyCare assignRoomToEmergencyCare(Integer roomId) {
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
    }*/

    /*public synchronized EmeregencyCare endEmergencyCare(Integer roomId, String doctorName, String patientName) {
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
    }*/



}
