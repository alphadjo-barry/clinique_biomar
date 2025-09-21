package com.exemple.clinique.repository.contracts;

import com.exemple.clinique.entity.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PatientRepository extends JpaRepository<Patient, Long> {

}
