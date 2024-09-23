package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PatientService {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private PasswordEncoder passwordEncoder; // Autowiring the PasswordEncoder

    public List<Patient> getAllUsers() {
        return patientRepository.findAll();
    }

    public void savePatient(Patient patient) {
        String encodedPassword = passwordEncoder.encode(patient.getPassword());
        patient.setPassword(encodedPassword);
        patientRepository.save(patient);
    }

    public Patient getPatientById(int id) {
        return patientRepository.findById(id).orElse(null);
    }

    public void updatePatient(int id, String name, String email, String password) {
        Patient patient = getPatientById(id);
        if (patient != null) {
            patient.setName(name);
            patient.setEmail(email);
            if (!password.isEmpty()) {
                patient.setPassword(passwordEncoder.encode(password)); // Encrypting the password
            }
            patientRepository.save(patient);
        }
    }

    public void deletePatient(int id) {
        patientRepository.deleteById(id);
    }
}
