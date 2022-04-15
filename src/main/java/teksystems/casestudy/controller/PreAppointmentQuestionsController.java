package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.PreAppointmentQuestionsDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Patient;
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
    private UserDAO userDao;

    @Autowired
    private AppointmentDAO appointmentDao;

    @RequestMapping(value= "user/paq/{appointmentId}", method = RequestMethod.GET)
    public ModelAndView paq(@PathVariable("appointmentId") Integer appointmentId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/paq");

        log.info(appointmentId.toString());

        //attributeName is object inside of jsp, and scheduledTime is the object that is being passed to that name

        PreAppointmentQuestionsFormBean form = new PreAppointmentQuestionsFormBean();

        if(appointmentDao.findByAppointmentId(appointmentId).getPaqId() != null){
            PreAppointmentQuestions paq = paqDao.getById(appointmentDao.findByAppointmentId(appointmentId).getPaqId());
            form.setComplaint(paq.getComplaint());
            form.setOnset(paq.getOnset());
            form.setLocation(paq.getLocation());
            form.setDuration(paq.getDuration());
            form.setDescription(paq.getDuration());
            form.setAlleviating(paq.getAlleviating());
            form.setRadiation(paq.getRadiation());
            form.setTemporalPatterns(paq.getTemporalPatterns());
            form.setSymptoms(paq.getSymptoms());
        }
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value= "/user/paqSubmit/{appointmentId}", method = RequestMethod.POST)
    public ModelAndView paqSubmit(@Valid PreAppointmentQuestionsFormBean form,
                                  @PathVariable("appointmentId") Integer appointmentId) throws Exception {

        ModelAndView response = new ModelAndView();
        response.addObject("appointmentId", appointmentId);

        log.info(form.toString());
//        log.info(form.getApptId());

        Appointment appointment = appointmentDao.findByAppointmentId(appointmentId);

//        Appointment appointment = appointmentDao.findByAppointmentId(Integer.parseInt(form.getApptId()));
        log.info("============");

        //erroring out here
        PreAppointmentQuestions paq = (appointment.getPaqId() != null) ?
                paqDao.getById(appointment.getPaqId()) : new PreAppointmentQuestions();

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

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if(!StringUtils.equals("anonymousUser", currentPrincipalName)){
            paqDao.save(paq);
            appointment.setPaqId(paq.getId());
            appointmentDao.save(appointment);
            User user = userDao.findByEmail(currentPrincipalName);
            response.setViewName("redirect:/user/my_schedule/" + user.getUserId());
        }

        return response;
    }
}
