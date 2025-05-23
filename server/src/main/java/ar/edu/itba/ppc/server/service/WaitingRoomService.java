package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.tp1g5.PatientRequest;
import ar.edu.itba.tp1g5.PatientResponse;
import ar.edu.itba.tp1g5.WaitingRoomServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

import java.util.Objects;

public class WaitingRoomService extends WaitingRoomServiceGrpc.WaitingRoomServiceImplBase {
    private final PatientRepository patientRepository;

    public WaitingRoomService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void registerPatient(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        String name = request.getPatientName();
        int level = request.getLevel();

        Patient existingPatient = patientRepository.getPatient(name);
        if (Objects.nonNull(existingPatient)) {
            responseObserver.onError(Status.ALREADY_EXISTS
                    .withDescription("Patient already exists in the waiting room.")
                    .asRuntimeException());
            return;
        }

        if (level < 1 || level > 5) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid emergency level. Must be between 1 and 5.")
                    .asRuntimeException());
            return;
        }

        Patient createdPatient = patientRepository.addPatient(name, level);

        if (Objects.nonNull(createdPatient)) {
            PatientResponse response = PatientResponse.newBuilder()
                    .setPatientName(createdPatient.getPatientName())
                    .setLevel(createdPatient.getEmergencyLevel())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Failed to add patient to the waiting room.")
                    .asRuntimeException());
        }
    }

    @Override
    public void updateEmergencyLevel(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        String name = request.getPatientName();
        int newLevel = request.getLevel();

        if (newLevel < 1 || newLevel > 5) {
            responseObserver.onError(Status.INVALID_ARGUMENT
                    .withDescription("Invalid emergency level. Must be between 1 and 5.")
                    .asRuntimeException());
            return;
        }

        Patient updatedPatient = patientRepository.updateEmergencyLevel(name, newLevel);

        if (Objects.nonNull(updatedPatient)) {
            PatientResponse response = PatientResponse.newBuilder()
                    .setPatientName(updatedPatient.getPatientName())
                    .setLevel(updatedPatient.getEmergencyLevel())
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
        } else {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Patient not found in the waiting room.")
                    .asRuntimeException());
        }
    }

    @Override
    public void checkWaitingList(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        String name = request.getPatientName();
        Patient patient = patientRepository.getPatient(name);

        if (Objects.isNull(patient)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Patient not found in the waiting room.")
                    .asRuntimeException());
            return;
        }

        Integer patientsAhead = patientRepository.getPatientsAhead(name);

        if (Objects.isNull(patientsAhead)) {
            responseObserver.onError(Status.INTERNAL
                    .withDescription("Error calculating patients ahead.")
                    .asRuntimeException());
            return;
        }

        PatientResponse response = PatientResponse.newBuilder()
                .setPatientName(name)
                .setLevel(patient.getEmergencyLevel())
                .setWaitingPatient(patientsAhead)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
