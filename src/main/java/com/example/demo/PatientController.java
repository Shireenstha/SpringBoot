package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class PatientController {

    @Autowired
    private PatientService patientService;

    @GetMapping("/index")
    public String indexPage() {
        return "index";
    }

    @GetMapping("/register")
    public String registerPage(Model model) {
        model.addAttribute("patient", new Patient()); // Initialize a new Patient object
        return "register";
    }

    @PostMapping("/submit-register")
    public String submitRegister(@ModelAttribute Patient newPatient) {
        patientService.savePatient(newPatient);
        return "redirect:/patient-list";
    }

    @GetMapping("/patient-list")
    public String patientListPage(Model model) {
        model.addAttribute("patients", patientService.getAllUsers());
        return "patient-list";
    }

    @GetMapping("/editpatientdetails/{id}")
    public String editPatientForm(@PathVariable int id, Model model) {
        Patient patient = patientService.getPatientById(id);
        if (patient != null) {
            model.addAttribute("patient", patient);
            return "editpatientdetails";
        }
        return "redirect:/patient-list";
    }

    @PostMapping("/edit-patient")
    public String editPatient(@ModelAttribute Patient patient) {
        patientService.updatePatient(patient.getId(), patient.getName(), patient.getEmail(), patient.getPassword());
        return "redirect:/patient-list";
    }

    @GetMapping("/deletepatient/{id}")
    public String deletePatient(@PathVariable int id) {
        patientService.deletePatient(id);
        return "redirect:/patient-list";
    }
}
