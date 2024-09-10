package com.example.demo;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@Controller
public class PatientController {

    private final List<Patient> patients = new ArrayList<>();

   
    @GetMapping("/index")
    public String indexPage() {
        return "index";  
    }

    
    @GetMapping("/register")
    public String registerPage() {
        return "register";  
    }

    
    @PostMapping("/submit-register")
    public String submitRegister(@RequestParam String name, @RequestParam String email, @RequestParam String id, @RequestParam String password) {
        Patient newPatient = new Patient(patients.size() + 1, name, email, id, password);
        patients.add(newPatient);
        return "redirect:/patient-list";  
    }

    
    @GetMapping("/patient-list")
    public String patientListPage(Model model) {
        model.addAttribute("patients", patients);  
        return "patient-list";  
    }

    
    @GetMapping("/editpatientdetails/{id}")
    public String editPatientForm(@PathVariable int id, Model model) {
        Patient patient = patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (patient != null) {
            model.addAttribute("patient", patient);  
            return "editpatientdetails";  
        }
        return "redirect:/patient-list";  
    }

    
    @PostMapping("/edit-patient")
    public String editPatient(@RequestParam int id, @RequestParam String name, @RequestParam String email, @RequestParam String password) {
        Patient patient = patients.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
        if (patient != null) {
            patient.setName(name);
            patient.setEmail(email);
            patient.setPassword(password);
        }
        return "redirect:/patient-ligist";  
    }

    
    @GetMapping("/deletepatient/{id}")
    public String deletePatient(@PathVariable int id) {
        patients.removeIf(p -> p.getId() == id);  
        return "redirect:/patient-list";  
    }
    
}