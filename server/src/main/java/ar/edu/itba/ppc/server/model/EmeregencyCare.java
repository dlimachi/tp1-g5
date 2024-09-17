package ar.edu.itba.ppc.server.model;

public class EmeregencyCare {
    private Integer room;
    private String doctorName;
    private String patient;

    public EmeregencyCare(Integer room, String doctorName, String patient) {
        this.room = room;
        this.doctorName = doctorName;
        this.patient = patient;
    }

    public Integer getRoom() {
        return room;
    }

    public void setRoom(Integer room) {
        this.room = room;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public String getPatient() {
        return patient;
    }
}
