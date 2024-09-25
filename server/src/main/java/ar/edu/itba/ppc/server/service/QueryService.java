package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.tp1g5.*;
import io.grpc.stub.StreamObserver;
import io.grpc.Status;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.constants.EmergencyRoomStatus;
import ar.edu.itba.ppc.server.constants.StatusPatient;

import java.util.List;
import java.util.stream.Collectors;

public class QueryService extends QueryServiceGrpc.QueryServiceImplBase {
    private final PatientRepository patientRepository;
    private final RoomRepository roomRepository;
    private final DoctorRepository doctorRepository;
    private final EmergencyCareService emergencyCareService;

    public QueryService(PatientRepository patientRepository, RoomRepository roomRepository, DoctorRepository doctorRepository, EmergencyCareService emergencyCareService) {
        this.patientRepository = patientRepository;
        this.roomRepository = roomRepository;
        this.doctorRepository = doctorRepository;
        this.emergencyCareService = emergencyCareService;
    }

    @Override
    public void queryRooms(QueryRequest request, StreamObserver<QueryRoomResponse> responseObserver) {
        if(roomRepository.getRooms().isEmpty()) {
            responseObserver.onError(
                    Status.FAILED_PRECONDITION
                            .withDescription("There are no rooms added.")
                            .asRuntimeException()
            );
            return;
        }

        List<RoomStatus> roomStatuses = roomRepository.getRooms().values().stream()
                .map(this::mapRoomToRoomStatus)
                .collect(Collectors.toList());

        QueryRoomResponse response = QueryRoomResponse.newBuilder()
                .addAllRooms(roomStatuses)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private RoomStatus mapRoomToRoomStatus(Room room) {
        RoomStatus.Builder builder = RoomStatus.newBuilder()
                .setRoomNumber(room.getRoom())
                .setRoomStatus(room.getStatus());

        if (room.getStatus().equals(EmergencyRoomStatus.OCCUPIED.getValue())) {
            Doctor attendingDoctor = doctorRepository.getDoctors().values().stream()
                    .filter(d -> d.getRoom() != null && d.getRoom().equals(String.valueOf(room.getRoom())))
                    .findFirst()
                    .orElse(null);

            Patient attendingPatient = patientRepository.getPatients().values().stream()
                    .filter(p -> p.getStatus().equals(StatusPatient.ATTENDING.getValue()))
                    .findFirst()
                    .orElse(null);

            if (attendingDoctor != null) {
                builder.setDoctorName(attendingDoctor.getDoctorName())
                        .setDoctorLevel(String.valueOf(attendingDoctor.getLevel()));
            }

            if (attendingPatient != null) {
                builder.setPatientName(attendingPatient.getPatientName())
                        .setPatientLevel(String.valueOf(attendingPatient.getEmergencyLevel()));
            }
        }

        return builder.build();
    }

    @Override
    public void queryWaitingRoom(QueryRequest request, StreamObserver<QueryWaitingRoomResponse> responseObserver) {
        if(patientRepository.getPatients().isEmpty()) {
            responseObserver.onError(
                    Status.FAILED_PRECONDITION
                            .withDescription("There are no patients in the waiting room.")
                            .asRuntimeException()
            );
            return;
        }

        List<WaitingRoom> patientsWaiting = patientRepository.getWaitingPatientsInOrder().stream()
                .map(patient -> WaitingRoom.newBuilder()
                        .setPatientName(patient.getPatientName())
                        .setPatientLevel(patient.getEmergencyLevel().toString())
                        .build())
                .collect(Collectors.toList());

        QueryWaitingRoomResponse reply = QueryWaitingRoomResponse.newBuilder()
                .addAllWaitingRooms(patientsWaiting)
                .build();
        responseObserver.onNext(reply);
        responseObserver.onCompleted();
    }

    @Override
    public void queryCares(QueryRequest request, StreamObserver<QueryCareCompletedResponse> responseObserver) {
        List<EmergencyCareResponse> completedCares = emergencyCareService.getCompletedCares();

        if (completedCares.isEmpty()) {
            responseObserver.onError(
                    Status.NOT_FOUND
                            .withDescription("No completed cares found.")
                            .asRuntimeException()
            );
            return;
        }

        List<CareCompleted> careCompletedList = completedCares.stream()
                .map(this::mapEmergencyCareResponseToCareCompleted)
                .collect(Collectors.toList());

        QueryCareCompletedResponse response = QueryCareCompletedResponse.newBuilder()
                .addAllCareCompleted(careCompletedList)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    private CareCompleted mapEmergencyCareResponseToCareCompleted(EmergencyCareResponse care) {
        return CareCompleted.newBuilder()
                .setRoomNumber(care.getRoom())
                .setPatientName(care.getPatientName())
                .setPatientLevel(String.valueOf(care.getPatientLevel()))
                .setDoctorName(care.getDoctorName())
                .setDoctorLevel(String.valueOf(care.getDoctorLevel()))
                .build();
    }
}