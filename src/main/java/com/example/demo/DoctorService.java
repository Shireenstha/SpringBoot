package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DoctorService {

    @Autowired
    private DoctorRepository doctorRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    public List<Doctor> getAllDoctors() {
        return doctorRepository.findAll();
    }

    public void saveDoctor(Doctor doctor) {
        // Check if email already exists
        if (emailExists(doctor.getEmail())) {
            throw new IllegalArgumentException("Email already exists.");
        }

        String encodedPassword = passwordEncoder.encode(doctor.getPassword());
        doctor.setPassword(encodedPassword);
        doctorRepository.save(doctor);
    }

    public Doctor getDoctorById(int id) {
        return doctorRepository.findById(id).orElse(null);
    }

    public void updateDoctor(int id, String name, String email, String specialization, String phone, String password) {
        Doctor doctor = getDoctorById(id);
        if (doctor != null) {
            doctor.setName(name);
            doctor.setEmail(email);
            doctor.setSpecialization(specialization);
            doctor.setPhone(phone);
            if (!password.isEmpty()) {
                doctor.setPassword(passwordEncoder.encode(password)); // Encrypting the password
            }
            doctorRepository.save(doctor);
        }
    }

    public void deleteDoctor(int id) {
        doctorRepository.deleteById(id);
    }

    public boolean emailExists(String email) {
        return doctorRepository.findByEmail(email) != null;
    }
}
