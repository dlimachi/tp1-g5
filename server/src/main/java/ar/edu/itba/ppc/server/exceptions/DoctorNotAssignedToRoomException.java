package ar.edu.itba.ppc.server.exceptions;

public class DoctorNotAssignedToRoomException extends RuntimeException {
    public DoctorNotAssignedToRoomException(String doctorName, Integer roomId) {
    }
}
