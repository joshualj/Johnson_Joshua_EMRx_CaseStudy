package teksystems.casestudy.database.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

//Repository
@Repository
public interface PatientDAO extends JpaRepository<Patient, Integer> {

    public Patient findByPatientId(@Param("patientId") Integer patientId);

    public Patient findByUserId(@Param("userId") Integer userId);

    public Patient findByMedicalRecordNumber(@Param("medicalRecordNumber") Integer medicalRecordNumber);



    //The below queries will be used for later implementation in a Patient Search, used by Clinicians
//    public List<Patient> findByFirstName(@Param("firstName") String firstName);
//
//    public List<Patient> findByLastName(@Param("lastName") String lastName);

    public List<Patient> findByPreferredName(@Param("preferredName") String preferredName);

    public List<Patient> findByBirthDate(@Param("birthDate") LocalDate birthDate);

    public List<Patient> findByPrimaryLanguage(@Param("primaryLanguage") String primaryLanguage);

//    public List<Patient> findByFirstNameAndLastName(@Param("firstName") String firstName, @Param("lastName") String lastName);
}
