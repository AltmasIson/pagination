package com.jpaexample.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.jpaexample.entity.Patient;
import com.jpaexample.service.PatientService;

@RestController
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientService service;

    // Patient save karne ka endpoint
    @PostMapping
    public ResponseEntity<Patient> createPatient(@RequestBody Patient patient) {
        service.savePatient(patient);
        return ResponseEntity.ok(patient);
    }

    // Multiple patients save karne ka endpoint
    @PostMapping("/bulk")
    public ResponseEntity<List<Patient>> createPatients(@RequestBody List<Patient> patients) {
        List<Patient> savedPatients = service.savePatients(patients);
        return ResponseEntity.ok(savedPatients);
    }

    // ID ke basis pe patient fetch karne ka endpoint
    @GetMapping("/{id}")
    public ResponseEntity<Patient> getPatientById(@PathVariable Long id) {
        Patient patient = service.getPatientById(id);
        return ResponseEntity.ok(patient);
    }

    // Hospital name ke basis pe paginated data fetch karne ka endpoint
    @GetMapping("/search")
    public ResponseEntity<Page<Patient>> getPatientsByHospitalName(
            @RequestParam String hospitalName,
            @RequestParam int page,
            @RequestParam int size) {
        Pageable pageable = PageRequest.of(page, size);
        Page<Patient> patients = service.getPatientsByHospitalName(hospitalName, pageable);
        return ResponseEntity.ok(patients);
    }

    // Saare patients fetch karne ka endpoint
    @GetMapping("/all")
    public ResponseEntity<List<Patient>> getAllPatients() {
        List<Patient> patients = service.getAllPatients();
        return ResponseEntity.ok(patients);
    }

    // Patient update karne ka endpoint
    @PutMapping("/{id}")
    public ResponseEntity<Patient> updatePatient(
            @PathVariable Long id, @RequestBody Patient updatedPatient) {
        updatedPatient.setId(id); // ID ko ensure karte hain
        Patient patient = service.updatePatient(updatedPatient);
        return ResponseEntity.ok(patient);
    }

    // Patient delete karne ka endpoint
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deletePatient(@PathVariable Long id) {
        service.deletePatient(id);
        return ResponseEntity.ok("Patient deleted with ID: " + id);
    }
    @GetMapping
    public ResponseEntity<Page<Patient>> getAllPatients(
            @RequestParam(defaultValue = "0") int page,
            @RequestParam(defaultValue = "10") int size) {
        Pageable pageable = PageRequest.of(page, size); // Pagination object create
        Page<Patient> patients = service.getAllPatients(pageable); // Service se paginated data fetch
        return ResponseEntity.ok(patients); // Response return
    }

}
