package ar.edu.itba.ppc.server;

import ar.edu.itba.ppc.server.model.Doctor;
import ar.edu.itba.ppc.server.model.Patient;
import ar.edu.itba.ppc.server.model.Room;
import ar.edu.itba.ppc.server.repository.DoctorRepository;
import ar.edu.itba.ppc.server.repository.PatientRepository;
import ar.edu.itba.ppc.server.repository.RoomRepository;
import ar.edu.itba.ppc.server.service.EmergencyCareService;
import ar.edu.itba.ppc.server.service.QueryService;
import ar.edu.itba.tp1g5.QueryRequest;
import ar.edu.itba.tp1g5.QueryRoomResponse;
import ar.edu.itba.tp1g5.QueryWaitingRoomResponse;
import io.grpc.stub.StreamObserver;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.ArgumentCaptor;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static org.mockito.Mockito.*;


class QueryServiceTest {

    DoctorRepository doctorRepository = Mockito.spy(new DoctorRepository());
    RoomRepository roomRepository = Mockito.spy(new RoomRepository());
    PatientRepository patientRepository = Mockito.spy(new PatientRepository());
    EmergencyCareService service = Mockito.spy(new EmergencyCareService(doctorRepository, roomRepository, patientRepository));

    QueryService queryService = new QueryService(patientRepository, roomRepository, doctorRepository, service);

    @Test
    void testQueryRooms() {
        Room room = new Room(1, "free");
        Room room2 = new Room(2, "occupied");
        Map<Integer, Room> roomMap = new HashMap<>();
        roomMap.put(room.getRoom(), room);
        roomMap.put(room2.getRoom(), room2);
        when(roomRepository.getRooms()).thenReturn(roomMap);

        Patient patient = new Patient("Foo", 1, "attending");
        patient.setCurrentRoom(2);
        when(patientRepository.getPatients()).thenReturn(Collections.singletonMap("Foo", patient));

        Doctor doctor = new Doctor("John", 2, "attending", 2);
        when(doctorRepository.getDoctors()).thenReturn(Collections.singletonMap("John", doctor));

        StreamObserver<QueryRoomResponse> responseObserver = mock(StreamObserver.class);
        QueryRequest request = QueryRequest.newBuilder().build();

        queryService.queryRooms(request, responseObserver);

        ArgumentCaptor<QueryRoomResponse> argumentCaptor = ArgumentCaptor.forClass(QueryRoomResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        QueryRoomResponse response = argumentCaptor.getValue();

        Assertions.assertEquals(2, response.getRoomsCount());
        Assertions.assertEquals("free", response.getRooms(0).getRoomStatus());
        Assertions.assertEquals("", response.getRooms(0).getPatientName());
        Assertions.assertEquals("", response.getRooms(0).getDoctorName());
        Assertions.assertEquals("occupied", response.getRooms(1).getRoomStatus());
        Assertions.assertEquals("Foo", response.getRooms(1).getPatientName());
        Assertions.assertEquals("John", response.getRooms(1).getDoctorName());
    }

    @Test
    void testQueryRoomsEmpty() {
        when(roomRepository.getRooms()).thenReturn(Collections.emptyMap());

        StreamObserver<QueryRoomResponse> responseObserver = mock(StreamObserver.class);
        QueryRequest request = QueryRequest.newBuilder().build();

        queryService.queryRooms(request, responseObserver);

        verify(responseObserver, times(1)).onError(any());
    }

    @Test
    void testQueryPatients() {
        Patient patient = new Patient("Foo", 1, "waiting");
        when(patientRepository.getPatients()).thenReturn(Collections.singletonMap("Foo", patient));
        when(patientRepository.getWaitingPatientsInOrder()).thenReturn(Collections.singletonList(patient));

        StreamObserver<QueryWaitingRoomResponse> responseObserver = mock(StreamObserver.class);
        QueryRequest request = QueryRequest.newBuilder().build();

        queryService.queryWaitingRoom(request, responseObserver);

        ArgumentCaptor<QueryWaitingRoomResponse> argumentCaptor = ArgumentCaptor.forClass(QueryWaitingRoomResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        QueryWaitingRoomResponse response = argumentCaptor.getValue();

        Assertions.assertEquals(1, response.getWaitingRoomsCount());
        Assertions.assertEquals("Foo", response.getWaitingRooms(0).getPatientName());
        Assertions.assertEquals("1", response.getWaitingRooms(0).getPatientLevel());
    }

    @Test
    void testQueryCare() {
        Patient patient = new Patient("Foo", 1, "waiting");
        when(patientRepository.getPatients()).thenReturn(Collections.singletonMap("Foo", patient));
        when(patientRepository.getWaitingPatientsInOrder()).thenReturn(Collections.singletonList(patient));

        StreamObserver<QueryWaitingRoomResponse> responseObserver = mock(StreamObserver.class);
        QueryRequest request = QueryRequest.newBuilder().build();

        queryService.queryWaitingRoom(request, responseObserver);

        ArgumentCaptor<QueryWaitingRoomResponse> argumentCaptor = ArgumentCaptor.forClass(QueryWaitingRoomResponse.class);
        verify(responseObserver, times(1)).onNext(argumentCaptor.capture());
        QueryWaitingRoomResponse response = argumentCaptor.getValue();

        Assertions.assertEquals(1, response.getWaitingRoomsCount());
        Assertions.assertEquals("Foo", response.getWaitingRooms(0).getPatientName());
        Assertions.assertEquals("1", response.getWaitingRooms(0).getPatientLevel());
    }
}