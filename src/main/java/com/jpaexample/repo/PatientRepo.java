package com.jpaexample.repo;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import com.jpaexample.entity.Patient;

public interface PatientRepo extends JpaRepository<Patient, Long> {

	Page<Patient> findAll(Pageable pageable);
    // Pagination ke liye hospitalName ke basis pe data fetch karna
    Page<Patient> findByHospitalName(String hospitalName, Pageable pageable);
}

//
//Get patients by hospital name (hospital ke naam ke basis par paginated data):
//http://localhost:8081/patients/search?hospitalName=XYZ&page=0&size=3


 

//Get all patients (saare patients bina pagination ke):
//http://localhost:8081/patients

//Get patient by ID (specific patient ID ke basis par):
//http://localhost:8081/patients/{id}


//Get paginated patients (pagination ke saath saare patients):
//http://localhost:8081/patients?page=0&size=3