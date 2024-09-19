package ar.edu.itba.ppc.server.exceptions;

public class RoomDoesntExistsException extends RuntimeException {
    private final Integer room;

    public RoomDoesntExistsException(Integer room) {
        this.room = room;
    }

    @Override
    public String getMessage() {
        return "Room " + room + " doesn't exists";
    }
}
