package ar.edu.itba.ppc.server.model;

import java.time.Instant;

public class EmergencyCare {
    private Integer roomNumber;
    private Doctor doctor;
    private Patient patient;
    private Instant startTime;

    public EmergencyCare(Integer roomNumber, Doctor doctor, Patient patient) {
        this.roomNumber = roomNumber;
        this.doctor = doctor;
        this.patient = patient;
        this.startTime = Instant.now();
    }

    // Getters and setters...

    @Override
    public String toString() {
        return "EmergencyCare{" +
                "roomNumber=" + roomNumber +
                ", doctor=" + doctor.getDoctorName() +
                ", patient=" + patient.getPatientName() +
                ", startTime=" + startTime +
                '}';
    }
}