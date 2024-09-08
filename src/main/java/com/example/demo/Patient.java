package com.example.demo;

public class Patient {

    private int id;
    private String name;
    private String email;
    private String patientId;  
    private String password;

    public Patient(int id, String name, String email, String patientId, String password) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.patientId = patientId;
        this.password = password;
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPatientId() {
        return patientId;
    }

    public void setPatientId(String patientId) {
        this.patientId = patientId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
