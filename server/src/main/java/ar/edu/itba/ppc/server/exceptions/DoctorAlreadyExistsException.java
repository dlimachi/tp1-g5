package ar.edu.itba.ppc.server.exceptions;

public class DoctorAlreadyExistsException extends RuntimeException {
    private final String sectorName;

    public DoctorAlreadyExistsException(String sectorName) {
        this.sectorName = sectorName;
    }

    @Override
    public String getMessage() {
        return "Doctor " + sectorName + " already exists";
    }
}