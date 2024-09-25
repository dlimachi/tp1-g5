package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.constants.Availabilities;
import ar.edu.itba.ppc.server.exceptions.*;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.tp1g5.EmergencyCareServiceGrpc;
import io.grpc.stub.StreamObserver;
import java.util.Comparator;

public class EmergencyCareService extends EmergencyCareServiceGrpc.EmergencyCareServiceImplBase{
    private final DoctorRepository doctorRepository;
    private final RoomRepository roomRepository;
    private final PatientRepository patientRepository;

    public EmergencyCareService(DoctorRepository doctorRepository, RoomRepository roomRepository, PatientRepository patientRepository) {
        this.doctorRepository = doctorRepository;
        this.roomRepository = roomRepository;
        this.patientRepository = patientRepository;
    }

    @Override
    public void startEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForDoctor(request);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void endEmergencyCare(EmergencyCareRequest request, StreamObserver<EmergencyCareResponse> responseObserver) {
        EmergencyCareResponse reply = updateStatusForEmergencyCare(request);
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    private synchronized EmergencyCareResponse updateStatusForDoctor(EmergencyCareRequest request) {
        Integer roomId = request.getRoom();
        Room room = roomRepository.getRooms().get(roomId);

        if (room == null) {
            throw new RoomDoesntExistsException(roomId);
        }
        if (room.getStatus().equals(Availabilities.ATTENDING.getValue())) {
            throw new RoomAlreadyOccupiedException(roomId);
        }

        Patient patient = patientRepository.getPatients().values().stream()
                .sorted(Comparator.comparing(Patient::getEmergencyLevel).reversed()
                        .thenComparing(Patient::getArrivalTime))
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No patient found"));

        Doctor availableDoctor = doctorRepository.getDoctors().values().stream()
                .filter(doctor -> doctor.getAvailability().equals(Availabilities.AVAILABLE.getValue()))
                .sorted(Comparator.comparing(Doctor::getLevel)
                        .thenComparing(Doctor::getDoctorName))
                .findFirst()
                .orElseThrow(() -> new NoAvailableDoctorException(roomId));

        room.setStatus(Availabilities.ATTENDING.getValue());
        availableDoctor.setRoom(roomId.toString());
        availableDoctor.setAvailability(Availabilities.ATTENDING.getValue());

        return EmergencyCareResponse.newBuilder()
                .setDoctorName(availableDoctor.getDoctorName())
                .setRoom(room.getRoom())
                .setPatientName(patient.getPatientName())
                .build();
    }

    private synchronized EmergencyCareResponse updateStatusForEmergencyCare(EmergencyCareRequest request) {
        String doctorName = request.getDoctorName();
        Integer room = request.getRoom();

        if (!doctorRepository.getDoctors().containsKey(doctorName)) {
            throw new DoctorDoesntExistsException(doctorName);
        }

        Doctor attendingDoctor = doctorRepository.getDoctors().get(doctorName);
        Room attendingRoom = roomRepository.getRooms().get(room);
        Patient attendingPatient = patientRepository.getPatients().get(request.getPatientName());

        if (attendingDoctor.getRoom() == null || !attendingDoctor.getRoom().equals(room.toString())) {
            throw new DoctorNotAssignedToRoomException(doctorName, room);
        }

        attendingRoom.setStatus(Availabilities.AVAILABLE.getValue());
        attendingDoctor.setAvailability(Availabilities.AVAILABLE.getValue());
        attendingDoctor.setRoom(null);
        //attendingPatient.setStatus(Availabilities.COMPLETED.getValue());
        //podriamos agregar un estado de completado para el paciente para despues mostrarlo como consulta finalizada

        return EmergencyCareResponse.newBuilder()
                .setDoctorName(attendingDoctor.getDoctorName())
                .setRoom(attendingRoom.getRoom())
                .setPatientName(attendingPatient.getPatientName())
                .build();
    }
}
