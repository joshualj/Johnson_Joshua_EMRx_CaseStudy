package teksystems.casestudy;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.PatientDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Patient;

import java.security.Timestamp;
import java.sql.SQLOutput;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;

@SpringBootApplication
@ImportResource({"classpath*:application-context.xml"})
public class EMR_Application {

	public static void main(String[] args) {
		SpringApplication.run(EMR_Application.class, args);
	}

}