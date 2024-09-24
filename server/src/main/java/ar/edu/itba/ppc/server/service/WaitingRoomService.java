package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.tp1g5.PatientRequest;
import ar.edu.itba.tp1g5.PatientResponse;
import ar.edu.itba.tp1g5.WaitingRoomServiceGrpc;
import io.grpc.Status;
import io.grpc.stub.StreamObserver;

public class WaitingRoomService extends WaitingRoomServiceGrpc.WaitingRoomServiceImplBase {
    private final PatientRepository patientRepository;

    public WaitingRoomService(PatientRepository patientRepository) {
        this.patientRepository = patientRepository;
    }

    @Override
    public void registerPatient(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        String name = request.getPatientName();
        int level = request.getLevel();

        boolean created = patientRepository.addPatient(new Patient(request.getPatientName(), request.getLevel()));

        if(created) {
            PatientResponse response = PatientResponse.newBuilder()
                    .setPatientName(name)
                    .setLevel(level)
                    .build();

            responseObserver.onNext(response);
            responseObserver.onCompleted();
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

        if (!patientRepository.updateEmergencyLevel(name, newLevel)) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Patient not found in the waiting room.")
                    .asRuntimeException());
            return;
        }

        PatientResponse response = PatientResponse.newBuilder()
                .setPatientName(name)
                .setLevel(newLevel)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }

    @Override
    public void checkWaitingList(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        String name = request.getPatientName();
        Patient patient = patientRepository.getPatient(name);

        if (patient == null) {
            responseObserver.onError(Status.NOT_FOUND
                    .withDescription("Patient not found in the waiting room.")
                    .asRuntimeException());
            return;
        }

        int patientsAhead = patientRepository.getPatientsAhead(name);

        PatientResponse response = PatientResponse.newBuilder()
                .setPatientName(name)
                .setLevel(patient.getEmergencyLevel())
                .setWaitingPatient(patientsAhead)
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}

/* package ar.edu.itba.ppc.server.service;

import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import ar.edu.itba.tp1g5.PatientRequest;
import ar.edu.itba.tp1g5.PatientResponse;
import ar.edu.itba.tp1g5.WaitingRoomServiceGrpc;
import io.grpc.stub.StreamObserver;

public class WaitingRoomService extends WaitingRoomServiceGrpc.WaitingRoomServiceImplBase {
   // private final PatientRepository patientRepository;


    @Override
    public void registerPatient(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
        String name = request.getPatientName();
        Integer level = request.getLevel();


        // Debo construir la response con Builder que es un objeto.
        PatientResponse.Builder patientResponseBuilder = PatientResponse.newBuilder();
        patientResponseBuilder.setPatientName(name).setLevel(level);


        //Luego debo mandarle la respuesta nvamente al cliente mediante el responseObserver
        responseObserver.onNext(patientResponseBuilder.build());
        // Debemos cerrar la llamada
        responseObserver.onCompleted();
    }


    @Override
    public void updateEmergencyLevel(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
    }

    @Override
    public void checkWaitingList(PatientRequest request, StreamObserver<PatientResponse> responseObserver) {
    }
}

 */
