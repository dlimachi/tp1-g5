package ar.edu.itba.ppc.server.constants;

public enum AvailabilityDoctor {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable"),
    ATTENDING("attending");

    private final String value;

    AvailabilityDoctor(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
