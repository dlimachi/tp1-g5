package ar.edu.itba.ppc.server;

import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.ppc.server.service.EmergencyCareService;
import ar.edu.itba.tp1g5.EmergencyCareListResponse;
import ar.edu.itba.tp1g5.EmergencyCareRequest;
import ar.edu.itba.tp1g5.EmergencyCareResponse;
import com.google.protobuf.Empty;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import static org.mockito.Mockito.*;

class EmergencyCareServiceTest {
    DoctorRepository doctorRepository = Mockito.spy(new DoctorRepository());
    RoomRepository roomRepository = Mockito.spy(new RoomRepository());
    PatientRepository patientRepository = Mockito.spy(new PatientRepository());

    EmergencyCareService service = new EmergencyCareService(doctorRepository, roomRepository, patientRepository);

    @Test
    void testStartEmergencyCare() {
        Patient patient = new Patient("Foo", 1, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getUrgentPatient()).thenReturn(patient);

        Doctor doctor = new Doctor("John", 2, "available", null);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());
        when(doctorRepository.getAvailableDoctor(patient.getEmergencyLevel())).thenReturn(doctor);

        Room room = new Room(1, "free");
        roomRepository.addRoom();
        when(roomRepository.getRoom(1)).thenReturn(room);


        StreamObserver<EmergencyCareResponse> responseObserver = mock(StreamObserver.class);
        EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                .setRoom(1)
                .build();

        service.startEmergencyCare(request, responseObserver);

        ArgumentCaptor<EmergencyCareResponse> argumentCaptor = ArgumentCaptor.forClass(EmergencyCareResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        EmergencyCareResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("John", response.getDoctorName());
        Assertions.assertEquals(2, response.getDoctorLevel());
        Assertions.assertEquals(1, response.getRoom());
        Assertions.assertEquals("Foo", response.getPatientName());
        Assertions.assertEquals(1, response.getPatientLevel());
    }

    @Test
    void testStartAllEmergencyCare() {
        Patient patient = new Patient("Foo", 3, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getUrgentPatient()).thenReturn(patient);

        Doctor doctor = new Doctor("Doctor", 3, "available", null);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());
        when(doctorRepository.getAvailableDoctor(patient.getEmergencyLevel())).thenReturn(doctor);

        Room room = new Room(1, "free");
        roomRepository.addRoom();
        when(roomRepository.getRoom(1)).thenReturn(room);

        StreamObserver<EmergencyCareListResponse> responseObserver = mock(StreamObserver.class);

        service.startAllEmergencyCare(Empty.newBuilder().build(), responseObserver);

        ArgumentCaptor<EmergencyCareListResponse> argumentCaptor = ArgumentCaptor.forClass(EmergencyCareListResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        EmergencyCareListResponse response = argumentCaptor.getValue();

        Assertions.assertEquals(1, response.getEmergencyCareListCount());
        Assertions.assertEquals("Doctor", response.getEmergencyCareList(0).getDoctorName());
        Assertions.assertEquals("Foo", response.getEmergencyCareList(0).getPatientName());
    }

    @Test
    void testEndEmergencyCare() {
        Doctor doctor = new Doctor("John", 2, "attending", 1);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());
        when(doctorRepository.getDoctor(doctor.getDoctorName())).thenReturn(doctor);

        Room room = new Room(1, "occupied");
        roomRepository.addRoom();
        when(roomRepository.getRoom(1)).thenReturn(room);

        Patient patient = new Patient("Foo", 2, "attending");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getPatient(patient.getPatientName())).thenReturn(patient);

        StreamObserver<EmergencyCareResponse> responseObserver = mock(StreamObserver.class);
        EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                .setRoom(1)
                .setDoctorName("John")
                .setPatientName("Foo")
                .build();

        service.endEmergencyCare(request, responseObserver);

        ArgumentCaptor<EmergencyCareResponse> argumentCaptor = ArgumentCaptor.forClass(EmergencyCareResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        EmergencyCareResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("John", response.getDoctorName());
        Assertions.assertEquals(2, response.getDoctorLevel());
        Assertions.assertEquals(1, response.getRoom());
        Assertions.assertEquals("Foo", response.getPatientName());
        Assertions.assertEquals(2, response.getPatientLevel());
        Assertions.assertEquals("free", response.getRoomStatus());
    }

    @Test
    void testStartEmergencyCareWhenTheRoomStillFree() {
        Patient patient = new Patient("Foo", 3, "waiting");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getUrgentPatient()).thenReturn(patient);

        Doctor doctor = new Doctor("John", 1, "available", null);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());
        when(doctorRepository.getAvailableDoctor(patient.getEmergencyLevel())).thenReturn(null);

        Room room = new Room(1, "free");
        roomRepository.addRoom();
        when(roomRepository.getRoom(1)).thenReturn(room);

        StreamObserver<EmergencyCareResponse> responseObserver = mock(StreamObserver.class);
        EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                .setRoom(1)
                .build();

        service.startEmergencyCare(request, responseObserver);

        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        Assertions.assertEquals(StatusRuntimeException.class, errorCaptor.getValue().getClass());
        Assertions.assertEquals("NOT_FOUND: Room #1 remains free", errorCaptor.getValue().getMessage());
    }

    @Test
    void testEndEmergencyCareWhenDoctorIsInAnotherRoom() {
        Doctor doctor = new Doctor("John", 2, "attending", 2);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());
        when(doctorRepository.getDoctor(doctor.getDoctorName())).thenReturn(doctor);

        Room room = new Room(1, "occupied");
        roomRepository.addRoom();
        when(roomRepository.getRoom(1)).thenReturn(room);

        Patient patient = new Patient("Foo", 3, "attending");
        patientRepository.addPatient(patient.getPatientName(), patient.getEmergencyLevel());
        when(patientRepository.getPatient(patient.getPatientName())).thenReturn(patient);

        StreamObserver<EmergencyCareResponse> responseObserver = mock(StreamObserver.class);
        EmergencyCareRequest request = EmergencyCareRequest.newBuilder()
                .setRoom(1)
                .setDoctorName("John")
                .setPatientName("Foo")
                .build();

        service.endEmergencyCare(request, responseObserver);

        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        Assertions.assertEquals(StatusRuntimeException.class, errorCaptor.getValue().getClass());
        Assertions.assertEquals("NOT_FOUND: Doctor John not assigned to room #1", errorCaptor.getValue().getMessage());
    }


}