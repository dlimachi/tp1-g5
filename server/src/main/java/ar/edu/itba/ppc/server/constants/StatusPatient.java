package ar.edu.itba.ppc.server.constants;

public enum StatusPatient {
    WAITING("waiting"),
    COMPLETED("completed"),
    ATTENDING("attending");

    private String value;
    StatusPatient(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
