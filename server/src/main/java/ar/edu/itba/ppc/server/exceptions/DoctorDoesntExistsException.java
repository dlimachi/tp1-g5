package ar.edu.itba.ppc.server.exceptions;

public class DoctorDoesntExistsException extends RuntimeException {
    private final String doctorName;

    public DoctorDoesntExistsException(String doctorName) {
        this.doctorName = doctorName;
    }

    @Override
    public String getMessage() {
        return "Doctor " + doctorName + " doesn't exists";
    }
}
