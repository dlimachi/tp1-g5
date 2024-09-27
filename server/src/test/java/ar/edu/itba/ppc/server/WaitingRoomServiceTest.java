package ar.edu.itba.ppc.server;

import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.service.WaitingRoomService;
import ar.edu.itba.tp1g5.*;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class WaitingRoomServiceTest {
    PatientRepository patientRepository = Mockito.spy(new PatientRepository());

    WaitingRoomService waitingRoomService = new WaitingRoomService(patientRepository);

    @Test
    void testStartEmergencyCareTest() {
        Patient patient = new Patient("Patient", 2, "waiting");
        when(patientRepository.getPatient(patient.getPatientName())).thenReturn(null);
        when(patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel())).thenReturn(patient);

        StreamObserver<PatientResponse> responseObserver = mock(StreamObserver.class);
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient.getPatientName())
                .setLevel(2)
                .build();
        waitingRoomService.registerPatient(request, responseObserver);

        ArgumentCaptor<PatientResponse> argumentCaptor = ArgumentCaptor.forClass(PatientResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        PatientResponse response = argumentCaptor.getValue();


        Assertions.assertEquals("Patient", response.getPatientName());
        Assertions.assertEquals(2, response.getLevel());
    }

    @Test
    void testUpdateEmergencyLevelTest() {
        Patient patient = new Patient("Patient", 1, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getPatient(patient.getPatientName())).thenReturn(patient);

        StreamObserver<PatientResponse> responseObserver = mock(StreamObserver.class);
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient.getPatientName())
                .setLevel(2)
                .build();
        waitingRoomService.updateEmergencyLevel(request, responseObserver);

        ArgumentCaptor<PatientResponse> argumentCaptor = ArgumentCaptor.forClass(PatientResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        PatientResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("Patient", response.getPatientName());
        Assertions.assertEquals(2, response.getLevel());
    }

    @Test
    void testUpdateEmergencyLevelInvalidLevelTest() {
        Patient patient = new Patient("Patient", 1, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getPatient(patient.getPatientName())).thenReturn(patient);

        StreamObserver<PatientResponse> responseObserver = mock(StreamObserver.class);
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient.getPatientName())
                .setLevel(6)
                .build();
        waitingRoomService.updateEmergencyLevel(request, responseObserver);

        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        Assertions.assertEquals("INVALID_ARGUMENT: Invalid emergency level. Must be between 1 and 5.", errorCaptor.getValue().getMessage());
    }

    @Test
    void testRegisterPatientAlreadyExistsTest() {
        Patient patient = new Patient("Patient", 1, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getPatient(patient.getPatientName())).thenReturn(patient);

        StreamObserver<PatientResponse> responseObserver = mock(StreamObserver.class);
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient.getPatientName())
                .setLevel(1)
                .build();
        waitingRoomService.registerPatient(request, responseObserver);

        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        Assertions.assertEquals("ALREADY_EXISTS: Patient already exists in the waiting room.", errorCaptor.getValue().getMessage());
    }

    @Test
    void testCheckPatient() {
        Patient patient = new Patient("Patient", 1, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getPatientsAhead(patient.getPatientName())).thenReturn(0);

        StreamObserver<PatientResponse> responseObserver = mock(StreamObserver.class);
        PatientRequest request = PatientRequest.newBuilder()
                .setPatientName(patient.getPatientName())
                .setLevel(1)
                .build();
        waitingRoomService.checkWaitingList(request, responseObserver);

        ArgumentCaptor<PatientResponse> argumentCaptor = ArgumentCaptor.forClass(PatientResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        PatientResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("Patient", response.getPatientName());
        Assertions.assertEquals(1, response.getLevel());
        Assertions.assertEquals(0, response.getWaitingPatient());
    }
}