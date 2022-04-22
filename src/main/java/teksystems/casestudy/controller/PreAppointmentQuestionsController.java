package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
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
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class PreAppointmentQuestionsController {

    @Autowired
    private PreAppointmentQuestionsDAO paqDao;

    @Autowired
    private UserDAO userDao;

    @Autowired
    private AppointmentDAO appointmentDao;

    @PreAuthorize("hasAnyAuthority('CLINICIAN','PATIENT')")
    @RequestMapping(value= "user/paq/{appointmentId}", method = RequestMethod.GET)
    public ModelAndView viewPaq(@PathVariable("appointmentId") Integer appointmentId) throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/paq");

        log.info(appointmentId.toString());

        PreAppointmentQuestionsFormBean form = new PreAppointmentQuestionsFormBean();

        //if a form has previously been completed, this will populate those fields into the form
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
        response.addObject("appointmentId", appointmentId);
        log.info(response.toString());

        return response;
    }

    @PreAuthorize("hasAnyAuthority('CLINICIAN','PATIENT')")
    @RequestMapping(value= "/user/paqSubmit/{appointmentId}", method=RequestMethod.POST)
    public ModelAndView paqSubmit(@PathVariable("appointmentId") Integer appointmentId,
                                  @Valid PreAppointmentQuestionsFormBean form,
                                  BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();
        log.info("This will not print");

        //check for errors, prompt user with error message and form if error results
        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for(ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info( ((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);

            response.addObject("bindingResult", bindingResult);

            response.setViewName("user/paq");
            return response;
        }

        response.addObject("appointmentId", appointmentId);

        //retrieve the appointment with the provided id, so that the PAQ can be added to that appointment
        Appointment appointment = appointmentDao.findByAppointmentId(appointmentId);

        PreAppointmentQuestions paq = (appointment.getPaqId() != null) ?
                paqDao.getById(appointment.getPaqId()) : new PreAppointmentQuestions();

        log.info(paq.toString());

        //set paq values to the form values
        paq.setAlleviating(form.getAlleviating());
        paq.setDescription(form.getDescription());
        paq.setComplaint(form.getComplaint());
        paq.setDuration(form.getDuration());
        paq.setLocation(form.getLocation());
        paq.setRadiation(form.getRadiation());
        paq.setSymptoms(form.getSymptoms());
        paq.setOnset(form.getOnset());
        paq.setTemporalPatterns(form.getTemporalPatterns());
        appointment.setChiefComplaint(paq.getComplaint());

        log.info(paq.toString());

        //make sure user is authenticated, before saving changes
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if(!StringUtils.equals("anonymousUser", currentPrincipalName)){
            paqDao.save(paq);
            appointment.setPaqId(paq.getId());
            appointmentDao.save(appointment);
            User user = userDao.findByEmail(currentPrincipalName);
            response.addObject(user);
            if(StringUtils.equals("PATIENT", user.getUserRole())){
                response.setViewName("redirect:/user/my_schedule");
            } else {
                response.setViewName("redirect:/clinician/my_clinician_schedule");
            }
        }

        return response;
    }
}
