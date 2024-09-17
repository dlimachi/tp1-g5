package ar.edu.itba.ppc.server.dto;

public record Doctor (
        String doctorName,
        Integer level,
        String availability
) {
}
