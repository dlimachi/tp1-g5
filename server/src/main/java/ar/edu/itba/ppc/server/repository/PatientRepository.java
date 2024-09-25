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
    // Usamos ConcurrentHashMap para operaciones básicas thread-safe
    private final ConcurrentHashMap<String, Patient> patients;

    // ReentrantReadWriteLock permite múltiples lecturas simultáneas, pero escrituras exclusivas
    private final ReentrantReadWriteLock rwLock = new ReentrantReadWriteLock();

    public PatientRepository() {
        this.patients = new ConcurrentHashMap<>();
    }

    /**
     * Agrega un nuevo paciente al repositorio.
     * Usa un write lock para asegurar que la operación sea atómica.
     */
    public Patient addPatient(String patientName, Integer level) {
        rwLock.writeLock().lock();
        try {
            patients.put(patientName, new Patient(patientName, level, StatusPatient.WAITING.getValue()));
            return patients.get(patientName);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * Actualiza el nivel de emergencia de un paciente.
     * Usa un write lock para asegurar que la actualización sea atómica.
     */
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

    /**
     * Calcula cuántos pacientes están delante en la lista de espera.
     * Usa un read lock para asegurar una vista consistente de todos los pacientes durante el cálculo.
     */
    public Integer getPatientsAhead(String patientName) {
        rwLock.readLock().lock();
        try {
            Patient patient = patients.get(patientName);
            if (Objects.isNull(patient)) {
                return null;
            }

            return getWaitingPatientsInOrder().indexOf(patient);
        } finally {
            rwLock.readLock().unlock();
        }
    }

    public List<Patient> getWaitingPatientsInOrder() {
        return patients.values().stream().sorted(Comparator
                .comparing(Patient::getEmergencyLevel).reversed()
                .thenComparing(Patient::getArrivalTime))
                .filter(patient -> !patient.getStatus().equals(StatusPatient.WAITING.getValue()))
                .toList();
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }

    public Patient getPatient(String name) {
        return patients.get(name);
    }
}