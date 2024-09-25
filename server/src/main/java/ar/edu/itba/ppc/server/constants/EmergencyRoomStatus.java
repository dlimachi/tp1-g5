package ar.edu.itba.ppc.server.constants;

public enum EmergencyRoomStatus {
    FREE("free"),
    OCCUPIED("occupied");

    private final String value;

    EmergencyRoomStatus(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
