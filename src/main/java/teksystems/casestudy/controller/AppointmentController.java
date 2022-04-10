package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;
import teksystems.casestudy.formbean.SelectAppointmentScheduleFormBean;

import javax.validation.Valid;
import java.sql.Time;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class AppointmentController {

    private final String[] appointmentTimes = {"11:00:00", "11:30:00", "12:00:00"};

    @Autowired
    private AppointmentDAO appointmentDao;

    @Autowired
    private ClinicianDAO clinicianDao;


//    @RequestMapping(value="/user/schedule_appointment", method = RequestMethod.GET)
//    public ModelAndView schedule() throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/schedule_appointment");
//
////        Date day = new Date(2022, 04, 04);
////        log.info(day.toString());
//
//        Time time = new Time(12, 30, 00);
//
//        List<Appointment> appointmentsByTime = appointmentDao.findByTime(time);
//
//        Appointment appointment = appointmentDao.findByAppointmentId(3);
//        List<Appointment> appointments2 = appointmentDao.getByPatientId(1);
//        Appointment apt = appointmentDao.findByPaqId(1);
//
//
//        response.addObject("appointment", appointment);
//
//        log.info(appointment.toString() + "this was found by Appointment Id");
//        log.info(apt.toString() + "this was found by Paq");
//
//
//        for (Appointment appointmente : appointments2) {
//            log.info(appointmente.toString() + "found by pt Id");
//        }
//
//        for (Appointment appointmentT : appointmentsByTime) {
//            log.info(appointmentT.toString() + "found by time");
//        }
//
//        return response;
//    }

    //TO DO: Add drop-down with DATE and clinicianId
    //TO DO: Update table after clicking submit button
    //store all clinicianId in a list, pass to front-end to use in drop down
    //store all dates in a drop-down (as strings), maybe have four drop downs?
    //handle null entry, when page is loaded initially

    @RequestMapping(value= "user/schedule_appointment", method = RequestMethod.GET)
    public ModelAndView schedule(@RequestParam(required = false) Integer clinicianId,
                                 @RequestParam(required = false) Integer year,
                                 @RequestParam(required = false) Integer month,
                                 @RequestParam(required = false) Integer day) throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("user/schedule_appointment");

        log.info(clinicianId + "====" + year + "====" + month + "====" + day);

        if(day == null) {
            day = 4;
        }
        if(month == null) {
            month = 4;
        }
        if(year == null) {
            year = 2022;
        }
        if(clinicianId == null) {
            clinicianId = 1;
        }

//        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        LocalDate date = LocalDate.of(year, month, day);

        log.info("==================================");

        List<Appointment> appointments = appointmentDao.findByClinicianClinicianIdAndDate(clinicianId, date);

        Set<String> scheduledTime = new HashSet<>();

        //prepare a List of times ["11:00:00","01:00:00"]

        for (Appointment appointment : appointments) {
            scheduledTime.add(appointment.getTime().toString());
            log.info(appointment.getTime().toString());
        }

        log.info(scheduledTime.toString());

        List<Clinician> clins = clinicianDao.findAll();

        response.setViewName("user/schedule_appointment"); //getting the jsp file
        response.addObject("clinician", clins);
        response.addObject("scheduledTime", scheduledTime);
        response.addObject("localDate", date);
        response.addObject("clinicianId", clinicianId);
        response.addObject("appointmentTimes", appointmentTimes);

        //attributeName is object inside of jsp, and scheduledTime is the object that is being passed to that name

        return response;
    }

    @PostMapping(value= "user/appointmentSubmit")
    public ModelAndView appointmentSubmit(@Valid SelectAppointmentScheduleFormBean form) throws Exception {
//                                          @RequestParam String date,
//                                          @RequestParam String time,
//                                          @RequestParam Integer clinicianId)
        ModelAndView response = new ModelAndView();

//        response.setViewName("redirect:/user/schedule_appointment");

        LocalDate date = LocalDate.of(form.getYear(), form.getMonth(), form.getDay());
        Clinician clinician = form.getClinician();
        Integer clinId = clinician.getClinicianId();

        List<Appointment> appointments = appointmentDao.findByClinicianClinicianIdAndDate(clinId, date);

        Set<String> scheduledTime = new HashSet<>();

        for (Appointment appointment : appointments) {
            scheduledTime.add(appointment.getTime().toString());
            log.info(appointment.getTime().toString());
        }

        return response;
        //convert string to local date -->
        //convert time to local time -->
        //convert time localtime --> just in DAO, entity, and wherever... not in database

    }
}
