package teksystems.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.PatientDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Patient;

import java.security.Timestamp;
import java.sql.SQLOutput;
import java.sql.Time;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

@SpringBootApplication
public class EMR_Application {

	@Autowired
	private AppointmentDAO appointmentDao;
	private PatientDAO patientDao;
	private UserDAO userDao;


	public static void main(String[] args) {
//		new EMR_Application().work();
		SpringApplication.run(EMR_Application.class, args);
	}

	public void work() {
//		createNewPatient("Harold", "Potter");
//		seeAllUsers();

		Date date = new Date( 1996, 01, 01, 8, 30);
		Time time = new Time(8,30,0);
		System.out.println("Time = " + time);
		System.out.println("Date = " + date);
		createNewAppointment(date, time);

	}

	public void createNewPatient(String firstName, String lastName) {
		Patient patient = new Patient();
		System.out.println("Before save : " + patient);

		patient.setFirstName(firstName);
		patient.setLastName(lastName);
		System.out.println("After names set : " + patient);
		patientDao.save(patient);
		System.out.println("After save : " + patient);
	}

	public void createNewAppointment(Date date, Time time) {
		Appointment appointment = new Appointment();
		System.out.println(appointment);
		appointment.setDate(date);
		appointment.setTime(time);
		System.out.println(appointment);
		appointmentDao.save(appointment);

		System.out.println(appointment);
	}

	public void seeAllUsers() {
		userDao.findAll();
	}
}