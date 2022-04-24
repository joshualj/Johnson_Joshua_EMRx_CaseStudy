package teksystems.casestudy.database.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;

import java.time.LocalDate;

@Slf4j
@DataJpaTest
@ActiveProfiles({"test", "default"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PatientDAOTest {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private PatientDAO patientDao;

    User pUserOne;
    User pUserTwo;
    Patient patientOne;
    Patient patientTwo;

    @BeforeEach
    void name() {
        //User objects used for testing.
        // p indicates a clinician object is later created from this object
        pUserOne = new User("Barry", "bobson",
                "barry@gmail.com", "blah", "PATIENT");
        userDao.save(pUserOne);

        pUserTwo = new User("Lisa", "Lobster",
                "lisa@gmail.com", "blah", "PATIENT");
        userDao.save(pUserTwo);


        //Patient object used for testing
        LocalDate birthDate = LocalDate.of(2000, 2,28);
        patientOne = new Patient(pUserOne.getUserId(), 6, birthDate, "M");
//        patientDao.save(patientOne);

    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePatientTest(){
        patientDao.save(patientOne);

        Assertions.assertThat(patientOne.getUserId()).isEqualTo(pUserOne.getUserId());
        Assertions.assertThat(patientOne.getMedicalRecordNumber()).isEqualTo(6);
        Assertions.assertThat(patientOne.getSex()).isEqualTo("M");
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void findByPatientIdTest() {
        patientDao.save(patientOne);
        Patient patient = patientDao.findByPatientId(patientOne.getPatientId());
        log.info(patient.toString() + "======findByPatientIdTest");
        Assertions.assertThat(patient).isEqualTo(patientOne);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void assignUserIdToPatientTest(){
        userDao.save(pUserTwo);
        patientTwo = new Patient(pUserTwo.getUserId(), 5,
                LocalDate.of(2000, 1, 26),
                "F");
        Assertions.assertThat(patientTwo.getUserId()).isEqualTo(pUserTwo.getUserId());
    }
}
