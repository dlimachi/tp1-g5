package ar.edu.itba.ppc.server.model;

public class Room {
    private Integer room;
    private String status;

    public Room(Integer room, String status) {
        this.room = room;
        this.status = status;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }


    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }
}
