package teksystems.casestudy.database.dao;

import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ActiveProfiles;
import teksystems.casestudy.database.entity.*;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@DataJpaTest
@Slf4j
@ActiveProfiles({"test", "default"})
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
public class PreAppointmentQuestionsDAOTest {

    @Autowired
    private PreAppointmentQuestionsDAO paqDao;

    @Autowired
    private PatientDAO patientDao;

    PreAppointmentQuestions paqOne;
    PreAppointmentQuestions paqTwo;
    Patient patientOne;
    Patient patientTwo;

    @BeforeEach
    void name() {
        //User objects used for testing.
        // p indicates a clinician object is later created from this object
        paqOne = new PreAppointmentQuestions("headache", "2022-04-01", "head", "5 hours",
                "dull", "tylenol", "neck", "dizziness", "daily");

        paqTwo = new PreAppointmentQuestions("left ankle pain", "2022-02-01", "foot", "3 hours",
                "sharp", "ice", "none", "headache", "weekly");
        paqDao.save(paqTwo);

    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void savePaqTest(){
        paqDao.save(paqOne);

        Assertions.assertThat(paqOne.getComplaint()).isEqualTo("headache");
        Assertions.assertThat(paqOne.getOnset()).isEqualTo("2022-04-01");
        Assertions.assertThat(paqOne.getAlleviating()).isEqualTo("tylenol");
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void getByIdTest() {
        PreAppointmentQuestions paq = paqDao.getById(paqTwo.getId());
        Assertions.assertThat(paq).isEqualTo(paqTwo);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void editPaqTest(){
        Assertions.assertThat(paqTwo.getComplaint()).isEqualTo("left ankle pain");
        Assertions.assertThat(paqTwo.getOnset()).isEqualTo("2022-02-01");
        Assertions.assertThat(paqTwo.getLocation()).isEqualTo("foot");

        paqTwo.setComplaint("jaw pain");
        paqTwo.setOnset("2022-08-30");
        paqTwo.setLocation("jaw");
        paqDao.save(paqTwo);

        Assertions.assertThat(paqTwo.getComplaint()).isEqualTo("jaw pain");
        Assertions.assertThat(paqTwo.getOnset()).isEqualTo("2022-08-30");
        Assertions.assertThat(paqTwo.getLocation()).isEqualTo("jaw");
    }
}
