package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.constants.StatusPatient;
import ar.edu.itba.ppc.server.model.Patient;

import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class PatientRepository {
    private final ConcurrentHashMap<String, Patient> patients;
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public PatientRepository() {
        this.patients = new ConcurrentHashMap<>();
    }

    public Patient addPatient(String patientName, Integer level) {
        rwLock.writeLock().lock();
        try {
            patients.put(patientName, new Patient(patientName, level, StatusPatient.WAITING.getValue()));
            return patients.get(patientName);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Patient updateEmergencyLevel(String patientName, int newLevel) {
        rwLock.writeLock().lock();
        try {
            Patient patient = patients.get(patientName);
            if (Objects.nonNull(patient)) {
                patient.setEmergencyLevel(newLevel);
                return patient;
            }
            return null;
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public Integer getPatientsAhead(String patientName) {
        rwLock.readLock().lock();
        try {
            Patient patient = patients.get(patientName);
            if (Objects.isNull(patient)) {
                return null;
            }

            List<Patient> waitingPatients = getWaitingPatientsInOrder();
            int index = waitingPatients.indexOf(patient);
            return index == -1 ? null : index;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public List<Patient> getWaitingPatientsInOrder() {
        return patients.values().stream()
                .filter(patient -> patient.getStatus().equals(StatusPatient.WAITING.getValue()))
                .sorted(Comparator
                        .comparing(Patient::getEmergencyLevel).reversed()
                        .thenComparing(Patient::getArrivalTime))
                .toList();
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    public Patient getPatient(String name) {
        return patients.get(name);
    }

    public Patient getUrgentPatient() {
        return patients.values().stream()
                .filter(p -> p.getStatus().equals(StatusPatient.WAITING.getValue()))
                .sorted(Comparator.comparing(Patient::getEmergencyLevel).reversed()
                        .thenComparing(Patient::getArrivalTime))
                .findFirst()
                .orElse(null);
    }
}
