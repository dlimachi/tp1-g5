package ar.edu.itba.ppc.server.repository;

import ar.edu.itba.ppc.server.model.Patient;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
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
    public Patient addPatient(Patient patient) {
        rwLock.writeLock().lock();
        try {
            if (patients.putIfAbsent(patient.getPatientName(), patient) == null) {
                return patient;
            }
            return null; // Patient already exists
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    /**
     * Obtiene un paciente por su nombre.
     * No necesita lock porque ConcurrentHashMap maneja lecturas de forma thread-safe.
     */
    public Patient getPatient(String patientName) {
        return patients.get(patientName);
    }

    /**
     * Actualiza el nivel de emergencia de un paciente.
     * Usa un write lock para asegurar que la actualización sea atómica.
     */
    public Patient updateEmergencyLevel(String patientName, int newLevel) {
        rwLock.writeLock().lock();
        try {
            Patient patient = patients.get(patientName);
            if (patient != null) {
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
    public Patient getPatientsAhead(String patientName) {
        rwLock.readLock().lock();
        try {
            Patient patient = patients.get(patientName);
            if (patient == null) {
                return null; // Patient not found
            }

            List<Patient> patientList = new ArrayList<>(patients.values());
            patientList.sort(Comparator
                    .comparing(Patient::getEmergencyLevel).reversed()
                    .thenComparing(Patient::getArrivalTime));

            int position = patientList.indexOf(patient);
            return patient;
        } finally {
            rwLock.readLock().unlock();
        }
    }

    /**
     * Elimina un paciente del repositorio.
     * Usa un write lock para asegurar que la eliminación sea atómica.
     */
    public Patient removePatient(String patientName) {
        rwLock.writeLock().lock();
        try {
            return patients.remove(patientName);
        } finally {
            rwLock.writeLock().unlock();
        }
    }

    public List<Patient> getWaitingPatientsInOrder() {
        return patients.values().stream().sorted(Comparator
                .comparing(Patient::getEmergencyLevel).reversed()
                .thenComparing(Patient::getArrivalTime))
                //.filter(patient -> patient.getStatus().equals("waiting"))
                .toList();
    }

    public Map<String, Patient> getPatients() {
        return patients;
    }
}