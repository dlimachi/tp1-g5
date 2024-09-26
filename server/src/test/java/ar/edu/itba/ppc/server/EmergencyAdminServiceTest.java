package ar.edu.itba.ppc.server;

import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.service.EmergencyAdminService;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.tp1g5.DoctorRequest;
import ar.edu.itba.tp1g5.DoctorResponse;
import ar.edu.itba.tp1g5.RoomResponse;
import com.google.protobuf.Empty;
import io.grpc.StatusRuntimeException;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;
import static org.mockito.Mockito.*;

class EmergencyAdminServiceTest {
    RoomRepository roomRepository = Mockito.spy(new RoomRepository());
    DoctorRepository doctorRepository = Mockito.spy(new DoctorRepository());
    EmergencyAdminService service = new EmergencyAdminService(doctorRepository, roomRepository);


    @Test
    void testAddRoom() {
        Room room = new Room(1, "free");
        when(roomRepository.addRoom()).thenReturn(room);
        StreamObserver<RoomResponse> responseObserver = mock(StreamObserver.class);

        service.addRoom(Empty.newBuilder().build(), responseObserver);
        ArgumentCaptor<RoomResponse> argumentCaptor = ArgumentCaptor.forClass(RoomResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        RoomResponse response = argumentCaptor.getValue();

        Assertions.assertEquals(1, roomRepository.getRooms().size());
        Assertions.assertEquals(1, response.getRoom());
    }

    @Test
    void testAddDoctor() {
        Doctor doctor = new Doctor("John", 2, "available", null);
        when(doctorRepository.addDoctor(anyString(), anyInt())).thenReturn(doctor);

        StreamObserver<DoctorResponse> responseObserver = mock(StreamObserver.class);
        DoctorRequest request = DoctorRequest.newBuilder()
                .setDoctorName(doctor.getDoctorName())
                .setLevel(2)
                .build();

        service.addDoctor(request, responseObserver);

        ArgumentCaptor<DoctorResponse> argumentCaptor = ArgumentCaptor.forClass(DoctorResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        DoctorResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("John", response.getDoctorName());
        Assertions.assertEquals(2, response.getLevel());
        Assertions.assertEquals(1, doctorRepository.getDoctors().size());
    }

    @Test
    void testCheckDoctor() {
        Doctor doctor = new Doctor("John", 2, "available", null);
        when(doctorRepository.getDoctor(anyString())).thenReturn(doctor);

        StreamObserver<DoctorResponse> responseObserver = mock(StreamObserver.class);
        DoctorRequest request = DoctorRequest.newBuilder()
                .setDoctorName(doctor.getDoctorName())
                .build();

        service.checkDoctor(request, responseObserver);

        ArgumentCaptor<DoctorResponse> argumentCaptor = ArgumentCaptor.forClass(DoctorResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        DoctorResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("John", response.getDoctorName());
        Assertions.assertEquals(2, response.getLevel());
        Assertions.assertEquals("available", response.getAvailability());
    }

    @Test
    void testSetDoctor() {
        Doctor doctor = new Doctor("John", 2, "available", null);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());
        when(doctorRepository.setAvailabilityDoctor(doctor.getDoctorName(), doctor.getAvailability())).thenReturn(doctor);

        StreamObserver<DoctorResponse> responseObserver = mock(StreamObserver.class);
        DoctorRequest request = DoctorRequest.newBuilder()
                .setDoctorName(doctor.getDoctorName())
                .setAvailability("unavailable")
                .build();

        service.setDoctor(request, responseObserver);

        ArgumentCaptor<DoctorResponse> argumentCaptor = ArgumentCaptor.forClass(DoctorResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        DoctorResponse response = argumentCaptor.getValue();

        Assertions.assertEquals("John", response.getDoctorName());
        Assertions.assertEquals(2, response.getLevel());
        Assertions.assertEquals("unavailable", response.getAvailability());
    }

    @Test
    void testSetDoctorWhenDoesNotExist() {
        StreamObserver<DoctorResponse> responseObserver = mock(StreamObserver.class);
        DoctorRequest request = DoctorRequest.newBuilder()
                .setDoctorName("John")
                .setAvailability("available")
                .build();

        service.setDoctor(request, responseObserver);

        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        Assertions.assertEquals(StatusRuntimeException.class, errorCaptor.getValue().getClass());
        Assertions.assertEquals("NOT_FOUND: Doctor John doesn't exists", errorCaptor.getValue().getMessage());
    }

    @Test
    void testAddDoctorWhenAlreadyExist() {
        Doctor doctor = new Doctor("John", 2, "available", null);
        doctorRepository.addDoctor(doctor.getDoctorName(), doctor.getLevel());

        when(doctorRepository.getDoctor(doctor.getDoctorName())).thenReturn(doctor);

        StreamObserver<DoctorResponse> responseObserver = mock(StreamObserver.class);
        DoctorRequest request = DoctorRequest.newBuilder()
                .setDoctorName(doctor.getDoctorName())
                .setLevel(2)
                .build();

        service.addDoctor(request, responseObserver);

        ArgumentCaptor<Throwable> errorCaptor = ArgumentCaptor.forClass(Throwable.class);
        verify(responseObserver, times(1)).onError(errorCaptor.capture());

        Assertions.assertEquals(StatusRuntimeException.class, errorCaptor.getValue().getClass());
        Assertions.assertEquals("ALREADY_EXISTS: Doctor John already exists", errorCaptor.getValue().getMessage());
    }
}