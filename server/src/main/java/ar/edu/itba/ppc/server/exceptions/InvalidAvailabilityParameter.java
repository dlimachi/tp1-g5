package ar.edu.itba.ppc.server.exceptions;

public class InvalidAvailabilityParameter extends RuntimeException {
    public final String availability;

    public InvalidAvailabilityParameter(String availability) {
        this.availability = availability;
    }

    @Override
    public String getMessage() {
        return "The availability is invalid. Correct " + availability;
    }
}
