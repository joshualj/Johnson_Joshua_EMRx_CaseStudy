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

import java.util.List;

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

        List <Appointment> appointments = appointmentDao.findByPatientPatientId(1);
//        Appointment appointment = appointmentDao.findByAppointmentId(3);
//        log.info(appointment.toString() + "by Id");


        for (Appointment appointmente : appointments) {
            log.info(appointmente.toString() + "by Id");
        }


        response.setViewName("index");
        response.addObject("appointments", appointments);

//        log.info(clinician.toString());
//        log.info(clinicianById.toString());
//        log.info(clinicianByEmail.toString());




        return response;
    }
}
