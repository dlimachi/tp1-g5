package ar.edu.itba.ppc.server.model;

import java.time.Instant;

public class Patient {
    private String patientName;
    private Integer emergencyLevel;
    private final Instant arrivalTime;

    public Patient(String patientName, Integer emergencyLevel) {
        this.patientName = patientName;
        this.emergencyLevel = emergencyLevel;
        this.arrivalTime = Instant.now();
    }

    public String getPatientName() {
        return patientName;
    }

    public void setPatientName(String patientName) {
        this.patientName = patientName;
    }

    public Integer getEmergencyLevel() {
        return emergencyLevel;
    }

    public void setEmergencyLevel(Integer emergencyLevel) {
        this.emergencyLevel = emergencyLevel;
    }

    public Instant getArrivalTime() {
        return arrivalTime;
    }

    // No setter for arrivalTime as it should not be modified after creation

    @Override
    public String toString() {
        return "Patient{" +
                "patientName='" + patientName + '\'' +
                ", emergencyLevel=" + emergencyLevel +
                ", arrivalTime=" + arrivalTime +
                '}';
    }
}