package com.example.demo;

import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
public class DoctorController {

    @Autowired
    private DoctorService doctorService;

    @GetMapping("/doctor-list")
    public String doctorListPage(Model model) {
        model.addAttribute("doctors", doctorService.getAllDoctors());
        return "doctor-list";
    }

   
    @GetMapping("/doctor-register")
    public String doctorRegisterPage(Model model) {
        model.addAttribute("doctor", new Doctor()); 
        return "doctorregister";
    }

    @PostMapping("/submit-doctor-register")
    public String submitDoctorRegister(@Valid @ModelAttribute("doctor") Doctor doctor,
                                       BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "doctorregister"; 
        }

        if (doctorService.emailExists(doctor.getEmail())) {
            bindingResult.rejectValue("email", "error.doctor", "Email already exists.");
            return "doctorregister"; 
        }

        
        doctorService.saveDoctor(doctor);
        return "redirect:/doctor-list";
    }

    @GetMapping("/edit-doctor-details/{id}")
    public String editDoctorForm(@PathVariable int id, Model model) {
        Doctor doctor = doctorService.getDoctorById(id);
        if (doctor != null) {
            model.addAttribute("doctor", doctor); // Bind the existing doctor object to the form
            return "editdoctor";
        }
        return "redirect:/doctor-list"; 
    }

    @PostMapping("/edit-doctor")
    public String editDoctor(@Valid @ModelAttribute("doctor") Doctor doctor,
                             BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            return "editdoctor"; 
        }

       
        doctorService.updateDoctor(doctor.getId(), doctor.getName(), doctor.getEmail(),
                                   doctor.getSpecialization(), doctor.getPhone(), doctor.getPassword());
        return "redirect:/doctor-list";
    }

   
    @GetMapping("/delete-doctor/{id}")
    public String deleteDoctor(@PathVariable int id) {
        doctorService.deleteDoctor(id);
        return "redirect:/doctor-list";
    }
}
