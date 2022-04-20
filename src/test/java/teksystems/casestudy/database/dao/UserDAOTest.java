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
import java.time.LocalTime;
import java.util.Optional;

@DataJpaTest
@ActiveProfiles({"test", "default"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AppointmentDAO appointmentDao;

    @Autowired
    private PatientDAO patientDao;

    @Autowired
    private ClinicianDAO clinicianDao;

    User cUserOne;
    User cUserTwo;
    User pUserOne;
    Patient patientOne;

    @BeforeEach
    void name() {
        //User objects used for testing.
        // c indicates a clinician object is later created from this object.
        // p indicates a patient object is later created from this object
        cUserOne = new User("joshua", "johnson",
                "joshualj2010@gmail.com", "blah", "CLINICIAN");

        cUserTwo = new User("conor", "johnson",
                "conorjohnson@gmail.com", "blah", "CLINICIAN");

        pUserOne = new User("Barry", "bobson",
                "barry@gmail.com", "blah", "PATIENT");
    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveUserTest(){
        userDao.save(cUserOne);

        Assertions.assertThat(cUserOne.getFirstName()).isEqualTo("joshua");
        Assertions.assertThat(cUserOne.getLastName()).isEqualTo("johnson");
        Assertions.assertThat(cUserOne.getEmail()).isEqualTo("joshualj2010@gmail.com");
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void findByUserIdTest() {
        User user = userDao.findByUserId(1);

        Assertions.assertThat(user.getUserId()).isEqualTo(1);
    }

    @Order(3)
    @ParameterizedTest
    @CsvSource({"bizzmo@mail.com,password,Benjamin,Maurice, PATIENT", "jimhalpert@mail.com,password,Jim, Halpert, CLINICIAN", "MichaelScott@mail.com,password,Michael,Scott, PATIENT"})
    void parameterizedTest(String email, String password, String firstName, String lastName, String userRole) {

        User expected = new User();

        expected.setEmail(email);
        expected.setPassword(password);
        expected.setFirstName(firstName);
        expected.setLastName(lastName);
        expected.setUserRole(userRole);

        userDao.save(expected);

        User actual = userDao.findByUserId(expected.getUserId());

        Assertions.assertThat(expected).isEqualTo(actual);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void assignUserIdToPatientTest(){
        userDao.save(pUserOne);

        LocalDate birthDate = LocalDate.of(2000, 2,28);
        patientOne = new Patient(pUserOne.getUserId(), 6, birthDate, "M");

        userDao.save(pUserOne);
        patientDao.save(patientOne);
        Assertions.assertThat(patientOne.getUserId()).isEqualTo(pUserOne.getUserId());
    }


}
