package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.constants.Availabilities;
import ar.edu.itba.ppc.server.exceptions.*;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.stub.StreamObserver;

import java.util.Comparator;
import java.util.Optional;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase{
    private final DoctorRepository repository;
    private final RoomRepository roomRepository;

    public EmergencyCareService(DoctorRepository repository, RoomRepository roomRepository) {
        this.repository = repository;
        this.roomRepository = roomRepository;
    }

    @Override
    public void startEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        Integer roomId = request.getRoom();
        String doctorName = request.getDoctorName();

        if(!roomRepository.getRooms().containsKey(roomId)) {
            throw new RoomDoesntExistsException(roomId);
        }

        if(roomRepository.getRooms().get(roomId).getStatus().equals(Availabilities.ATTENDING.getValue())) {
            throw new RoomAlreadyOccupiedException(roomId);
        }

        Optional<Doctor> availableDoctor = repository.getDoctors().values().stream()
                .filter(doctor -> doctor.getAvailability().equals(Availabilities.AVAILABLE.getValue()))
                .sorted(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .findFirst();

        if (availableDoctor.isEmpty()) {
            throw new NoAvailableDoctorException(roomId);
        }

        Doctor doctor = availableDoctor.get();
        Room room = roomRepository.getRooms().get(roomId);

        doctor.lockWriting();
        room.lockWriting();
        try {
            room.setStatus(Availabilities.ATTENDING.getValue());
            doctor.setRoom(roomId.toString());
            doctor.setAvailability(Availabilities.ATTENDING.getValue());
        }
        finally {
            doctor.unlockWriting();
            room.unlockWriting();
        }

        EmergencyCareResponse reply = EmergencyCareResponse.newBuilder()
                .setDoctorName(doctor.getDoctorName())
                .setRoom(room.getRoom())
                .setPatientName("patient")
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        String doctorName = request.getDoctorName();
        Integer room = request.getRoom();

        if (!repository.getDoctors().containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }

        Doctor attendingDoctor = repository.getDoctors().get(doctorName);
        Room attendingRoom = roomRepository.getRooms().get(room);

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room.toString())) {
            throw new DoctorNotAssignedToRoomException(doctorName, room);
        }

        attendingDoctor.lockWriting();
        attendingRoom.lockWriting();

        try {
            attendingRoom.setStatus(Availabilities.AVAILABLE.getValue());
            attendingDoctor.setAvailability(Availabilities.AVAILABLE.getValue());
            attendingDoctor.setRoom(null);
        }
        finally {
            attendingDoctor.unlockWriting();
            attendingRoom.unlockWriting();
        }

        EmergencyCareResponse reply = EmergencyCareResponse.newBuilder()
                .setDoctorName(doctorName)
                .setRoom(room)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }
}
