package ar.edu.itba.ppc.server.model;


public class Doctor {
    String doctorName;
    Integer level;
    String availability;

    public Doctor(String doctorName, Integer level, String available) {
        this.doctorName = doctorName;
        this.level = level;
        this.availability = available;
    }

    public String getDoctorName() {
        return doctorName;
    }

    public void setDoctorName(String doctorName) {
        this.doctorName = doctorName;
    }

    public Integer getLevel() {
        return level;
    }

    public void setLevel(Integer level) {
        this.level = level;
    }

    public String getAvailability() {
        return availability;
    }

    public void setAvailability(String availability) {
        this.availability = availability;
    }
}
