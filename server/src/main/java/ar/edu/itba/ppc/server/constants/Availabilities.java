package ar.edu.itba.ppc.server.constants;

public enum Availabilities {
    AVAILABLE("available"),
    UNAVAILABLE("unavailable"),
    ATTENDING("attending");

    private final String value;

    Availabilities(String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }
}
