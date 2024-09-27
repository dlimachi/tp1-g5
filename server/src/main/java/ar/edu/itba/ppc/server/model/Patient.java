package ar.edu.itba.ppc.server.model;

import java.time.LocalDateTime;

public class Patient {
    private String patientName;
    private Integer emergencyLevel;
    private String status;
    private LocalDateTime arrivalTime;
    private Integer currentRoom;

    public Patient(String patientName, Integer emergencyLevel, String status) {
        this.patientName = patientName;
        this.emergencyLevel = emergencyLevel;
        this.status = status;
        this.arrivalTime = LocalDateTime.now();
        this.currentRoom = null;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public LocalDateTime getArrivalTime() {
        return arrivalTime;
    }

    public void setArrivalTime(LocalDateTime arrivalTime) {
        this.arrivalTime = arrivalTime;
    }

    public Integer getCurrentRoom() {
        return currentRoom;
    }

    public void setCurrentRoom(Integer currentRoom) {
        this.currentRoom = currentRoom;
    }
}