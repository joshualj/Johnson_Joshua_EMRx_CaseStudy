package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.User;

import java.sql.Time;
import java.time.LocalDate;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private AppointmentDAO appointmentDao;

    @RequestMapping(value= "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

//        List <Clinician> clinicians = clinicianDao.findByDepartment("Orthopedics");
//        List <Clinician> cliniciansByFirstName = clinicianDao.findByLanguagesContaining("English");
//        List <Clinician> cliniciansByLastName = clinicianDao.findByFirstNameAndLastName("Christina", "Sanchez");
//
//        for (Clinician clinician : clinicians) {
//            log.info(clinician.toString() + "Department");
//        }
//
//        for (Clinician clin : cliniciansByFirstName) {
//            log.info(clin.toString() + "Language");
//        }
//
//        for (Clinician clinic : cliniciansByLastName) {
//            log.info(clinic.toString() + "First/Last Name");
//        }


//        Appointment appointment = appointmentDao.findByAppointmentId(3);
//        log.info(appointment.toString() + "by Id");

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate day = LocalDate.of(2022,4,4);
        Time time = new Time(12,0,0);
        log.info(time.toString());

        log.info(day.toString());
        log.info("==================================");

        List<Appointment> appointments = appointmentDao.findByClinicianClinicianIdAndDate(1, day);

        Set<Time> scheduledTime = new HashSet<>();

        for (Appointment appointment : appointments) {
            scheduledTime.add(appointment.getTime());
//            log.info(appointment.toString() + " by Id");
        }

        response.setViewName("index"); //getting the jsp file
        response.addObject("scheduledTime", scheduledTime);
        //attributeName is object inside of jsp, and scheduledTime is the object that is being passed to that name

//        log.info(clinician.toString());
//        log.info(clinicianById.toString());
//        log.info(clinicianByEmail.toString());

        return response;
    }
}
