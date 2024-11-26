package com.jpaexample.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.jpaexample.entity.Patient;
import com.jpaexample.repo.PatientRepo;

@Service
public class PatientService {

    @Autowired
    private PatientRepo repo;

    // Ek patient save karne ka method
    public void savePatient(Patient patient) {
        repo.save(patient);
    }

    // Multiple patients ek saath save karne ka method
    public List<Patient> savePatients(List<Patient> patients) {
        return repo.saveAll(patients);
    }

    // ID ke basis pe patient fetch karna
    public Patient getPatientById(Long id) {
        return repo.findById(id).orElse(null);
    }

    // Hospital name ke basis pe paginated data fetch karna
    public Page<Patient> getPatientsByHospitalName(String hospitalName, Pageable pageable) {
        return repo.findByHospitalName(hospitalName, pageable);
    }

    // Saare patients fetch karna
    public List<Patient> getAllPatients() {
        return repo.findAll();
    }

    // Patient ko update karna
    public Patient updatePatient(Patient patient) {
        return repo.save(patient);
    }

    // Patient ko delete karna
    public void deletePatient(Long id) {
        repo.deleteById(id);
    }
    public Page<Patient> getAllPatients(Pageable pageable) {
        return repo.findAll(pageable); // Repository ka findAll method paginated data return karega
    }

}
