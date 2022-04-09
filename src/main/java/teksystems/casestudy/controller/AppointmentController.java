package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;

import java.sql.Time;
import java.util.Date;
import java.util.List;

@Slf4j
@Controller
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDao;

    @RequestMapping(value="/user/schedule_appointment", method = RequestMethod.GET)
    public ModelAndView schedule() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/schedule_appointment");

//        Date day = new Date(2022, 04, 04);
//        log.info(day.toString());

        Time time = new Time(12, 30, 00);

        List<Appointment> appointmentsByTime = appointmentDao.findByTime(time);

        Appointment appointment = appointmentDao.findByAppointmentId(3);
        List<Appointment> appointments2 = appointmentDao.getByPatientId(1);
        Appointment apt = appointmentDao.findByPaqId(1);


        response.addObject("appointment", appointment);

        log.info(appointment.toString() + "this was found by Appointment Id");
        log.info(apt.toString() + "this was found by Paq");


        for (Appointment appointmente : appointments2) {
            log.info(appointmente.toString() + "found by pt Id");
        }

        for (Appointment appointmentT : appointmentsByTime) {
            log.info(appointmentT.toString() + "found by time");
        }

        return response;
    }

}
