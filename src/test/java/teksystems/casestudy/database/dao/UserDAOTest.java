package teksystems.casestudy.database.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;

import java.time.LocalDate;

@DataJpaTest
@ActiveProfiles({"test", "default"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class UserDAOTest {

    @Autowired
    private UserDAO userDao;

    User userOne;
    User userTwo;
    Patient patient;
    Clinician clinician;
    Appointment appointment;


    @BeforeEach
    void name() {
        userOne = new User("joshua", "johnson",
                "joshualj2010@gmail.com", "blah", "CLINICIAN");
//        userDao.save(userOne);
//
//        clinician = new Clinician()
//
//        Integer user_id = userOne.getUserId();
//
//        userTwo = new User("bob", "bobson",
//                "jimmy@gmail.com", "blah", "PATIENT");
//
//        Integer patientUserId = userTwo.getUserId();
//
//        LocalDate birthDate = LocalDate.of(2000, 2,28);
//        patient = new Patient(patientUserId, 6, birthDate, "M");
//
//        appointment = new Appointment();
    }

    @Test
//    @Order(2)
    public void findByUserIdTest() {
//        user = userDao.findByUserId(1);
        userDao.save(userOne);
        Assertions.assertThat(userOne.getEmail()).isEqualTo("joshualj2010@gmail.com");
    }

//    @Test
//    public void findByAppointmentIdTest(){
//
//    }
}
