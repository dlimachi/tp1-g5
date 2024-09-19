package ar.edu.itba.ppc.server.dto;

public record InfoDoctorResponse(
        String doctorName,
        Integer level,
        String availability
) {
}
