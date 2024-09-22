package ar.edu.itba.ppc.client.utilsConsole;

public enum ClientArgs {
    SERVER_ADDRESS("serverAddress"),
    ACTION("action"),
    DOCTOR("doctor"),
    LEVEL("level"),
    AVAILABILITY("availability"),
    PATIENT("patient"),
    ROOM("room"),
    OUT_PATH("outPath");

    private final String value;
    ClientArgs(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}