package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.PreAppointmentQuestionsDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.PreAppointmentQuestions;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.PreAppointmentQuestionsFormBean;
import teksystems.casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;

@Slf4j
@Controller
public class PreAppointmentQuestionsController {

    @Autowired
    private PreAppointmentQuestionsDAO paqDao;

    @Autowired
    private AppointmentDAO appointmentDao;

    @RequestMapping(value= "user/paq", method = RequestMethod.GET)
    public ModelAndView paq() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/paq");

        //attributeName is object inside of jsp, and scheduledTime is the object that is being passed to that name

        PreAppointmentQuestionsFormBean form = new PreAppointmentQuestionsFormBean();
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value= "/user/paqSubmit", method = RequestMethod.POST)
    public ModelAndView paqSubmit(@Valid PreAppointmentQuestionsFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/paqSubmit");

        log.info(form.toString());
        log.info(form.getApptId());

        Appointment appointment = appointmentDao.findByAppointmentId(Integer.parseInt(form.getApptId()));
        log.info("============");

        //erroring out here
        PreAppointmentQuestions paq = (appointment.getPaqId() != null) ? paqDao.getById(appointment.getPaqId()) :
                new PreAppointmentQuestions();

        log.info("it makes it this far?");
        log.info(paq.toString());


        paq.setAlleviating(form.getAlleviating());
        log.info("did it make it passed Alleviating??");

        paq.setDescription(form.getDescription());
        paq.setComplaint(form.getComplaint());
        paq.setDuration(form.getDuration());
        paq.setLocation(form.getLocation());
        paq.setRadiation(form.getRadiation());
        paq.setSymptoms(form.getSymptoms());
        paq.setOnset(form.getOnset());
        paq.setTemporalPatterns(form.getTemporalPatterns());

        log.info("did it make it this this far?");
        log.info(paq.toString());

        paqDao.save(paq);

        appointment.setPaqId(paq.getId());
        appointmentDao.save(appointment);

        response.setViewName("redirect:/user/my_schedule");

        return response;
    }
}
