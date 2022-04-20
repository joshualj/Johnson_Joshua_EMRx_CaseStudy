package teksystems.casestudy.database.dao;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.*;
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
public class AppointmentDAOTest {

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AppointmentDAO appointmentDao;

    @Autowired
    private PatientDAO patientDao;

    @Autowired
    private ClinicianDAO clinicianDao;

    User cUserOne;  //User object. c indicates a clinician object will be created from it.
    User cUserTwo;
    User cUserThree;
    User pUserOne;      //User object. p indicates a patient object will be created from it.
    Patient patientOne;
    Clinician clinicianOne;
    Clinician clinicianTwo;
    Appointment appointmentOne;
    Appointment appointmentTwo;


    @BeforeEach
    void name() {
        //User objects used for testing. c indicates a clinician object is created from this object
        cUserOne = new User("joshua", "johnson",
                "joshualj2010@gmail.com", "blah", "CLINICIAN");
        userDao.save(cUserOne);

        cUserTwo = new User("conor", "johnson",
                "conorjohnson@gmail.com", "blah", "CLINICIAN");
        userDao.save(cUserTwo);

        cUserThree = new User("champ", "dogson",
                "champdogson@gmail.com", "blah", "CLINICIAN");


        //User object used for testing. p indicates a patient object is created from this object
        pUserOne = new User("Barry", "bobson",
                "barry@gmail.com", "blah", "PATIENT");
        userDao.save(pUserOne);


        //Patient object used for testing
        LocalDate birthDate = LocalDate.of(2000, 2,28);
        patientOne = new Patient(pUserOne.getUserId(), 6, birthDate, "M");
        patientDao.save(patientOne);


        //Clinician objects used for testing -- one saved, one not saved
        clinicianOne = new Clinician(cUserOne.getUserId(), "Physician",
                "Orthopedics",
                "English, Spanish");
        clinicianDao.save(clinicianOne);

        clinicianTwo = new Clinician(cUserTwo.getUserId(), "Physician",
                "Pediatrics",
                "English, Spanish");

        //Appointment Object Used for testing, not saved
        LocalDate today = LocalDate.of(2022,4,19);
        LocalTime time = LocalTime.of(12,30);
        appointmentOne = new Appointment(today, time);
        appointmentOne.setClinician(clinicianOne);
        appointmentOne.setPatient(patientOne);

        appointmentTwo = new Appointment(today, time);
        appointmentTwo.setClinician(clinicianOne);
        appointmentTwo.setPatient(patientOne);
        appointmentDao.save(appointmentTwo);

    }

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveAppointment(){
        clinicianDao.save(clinicianOne);
        patientDao.save(patientOne);
        appointmentDao.save(appointmentOne);

        Assertions.assertThat(appointmentOne.getClinician()).isEqualTo(clinicianOne);
        Assertions.assertThat(appointmentOne.getPatient()).isEqualTo(patientOne);
    }

    @Test
    @Order(2)
    @Rollback(value = false)
    public void findByAppointmentIdTest(){
        appointmentDao.save(appointmentTwo);
        Appointment appointment = appointmentDao.findByAppointmentId(appointmentTwo.getAppointmentId());
        Assertions.assertThat(appointment).isEqualTo(appointmentTwo);
//        Assertions.assertThat(appointmentOne).isEqualTo(appointment);
    }

    @Test
    @Order(3)
    @Rollback(value = false)
    public void editAppointmentTest(){
        appointmentDao.save(appointmentOne);
        Appointment appointment = appointmentDao.findByAppointmentId(appointmentOne.getAppointmentId());

        clinicianDao.save(clinicianTwo);
        appointment.setClinician(clinicianTwo);

        appointmentDao.save(appointment);
        Assertions.assertThat(appointment.getClinician()).isEqualTo(clinicianTwo);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void deleteByAppointmentIdTest(){

        appointmentDao.deleteById(1);

        Optional<Appointment> possibleApt = Optional.ofNullable(
                appointmentDao.findByAppointmentId(appointmentOne.getAppointmentId()));

        Appointment nullApt = null;
        if (possibleApt.isPresent()) {
            nullApt = possibleApt.get();
        }

        Assertions.assertThat(nullApt).isNull();
    }

}
