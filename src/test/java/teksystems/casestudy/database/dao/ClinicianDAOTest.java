package teksystems.casestudy.database.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;

import java.time.LocalDate;

@DataJpaTest
@ActiveProfiles({"test", "default"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class ClinicianDAOTest {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ClinicianDAO clinicianDao;

    User cUserOne;
    User cUserTwo;
    Clinician clinicianOne;
    Clinician clinicianTwo;

    @BeforeEach
    void name() {
        //User objects used for testing.
        // c indicates a clinician object is later created from this object
        cUserOne = new User("joshua", "johnson",
                "joshualj2010@gmail.com", "blah", "CLINICIAN");
        userDao.save(cUserOne);

        cUserTwo = new User("conor", "johnson",
                "conorjohnson@gmail.com", "blah", "CLINICIAN");


        //Clinician objects used for testing -- one saved, one not saved
        clinicianOne = new Clinician(cUserOne.getUserId(), "Physician",
                "Orthopedics",
                "English, Spanish");

    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveClinicianTest(){
        clinicianDao.save(clinicianOne);

        Assertions.assertThat(clinicianOne.getTitle()).isEqualTo("Physician");
        Assertions.assertThat(clinicianOne.getDepartment()).isEqualTo("Orthopedics");
        Assertions.assertThat(clinicianOne.getLanguages()).isEqualTo("English, Spanish");
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void findByClinicianIdTest() {
        Clinician clinician = clinicianDao.findByClinicianId(1);

        Assertions.assertThat(clinician.getClinicianId()).isEqualTo(1);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void assignUserIdToClinicianTest(){
        userDao.save(cUserTwo);
        clinicianTwo = new Clinician(cUserTwo.getUserId(), "Physician",
                "Pediatrics",
                "English, Spanish");
        Assertions.assertThat(clinicianTwo.getUserId()).isEqualTo(cUserTwo.getUserId());
    }
}
