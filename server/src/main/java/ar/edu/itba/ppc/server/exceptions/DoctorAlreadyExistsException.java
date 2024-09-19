package ar.edu.itba.ppc.server.exceptions;

public class DoctorAlreadyExistsException extends RuntimeException {
    private final String doctorName;

    public DoctorAlreadyExistsException(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String getMessage() {
        return "Doctor " + doctorName + " already exists";
    }
}