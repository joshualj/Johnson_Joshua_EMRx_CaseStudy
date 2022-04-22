package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.dao.PatientDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
public class UserController{

    @Autowired
    private UserDAO userDao;

    @Autowired
    private ClinicianDAO clinicianDao;

    @Autowired
    private PatientDAO patientDao;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value="/user/register", method = RequestMethod.GET)
    public ModelAndView register() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/register");


        /* all these 2 lines of code are doing is seeding teh mdoel with an
        // empty form bean so JSP page substitutions will not error out
        // in this case spring is being nice enough not to throw errors but
        // these 2 lines are safety
        */
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value="/user/registerSubmit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registerSubmit(@Valid RegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {
//            List<String> errorMessages = new ArrayList<>();
//
//            for(ObjectError error : bindingResult.getAllErrors()) {
//                errorMessages.add(error.getDefaultMessage());
//            }

            response.addObject("form", form);

            response.addObject("bindingResult", bindingResult);

            response.setViewName("user/register");
            return response;
        }


        //we first assume we are going to do an edit by loading the user by loading the user from
        // the database using the incoming id on the form

        //now check if the id in the register in the form bean is not null, then query it
        //from the database
        User user = userDao.findByEmail(form.getEmail());

        if (user == null) {
            user = new User();
        }

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);
        user.setUserRole("PATIENT");


        userDao.save(user);

        Patient patient = patientDao.findByUserId(user.getUserId());

        if (patient == null) {
            patient = new Patient();
        }

        //sets medical_record_number to a random number from 0-1999
        //after checking to make sure no patient has that MRN.

        Random rand = new Random();
        int upperbound = 2000;
        int mrn = rand.nextInt(upperbound);

        while(patientDao.findByMedicalRecordNumber(mrn) != null) {
            mrn = rand.nextInt(upperbound);
        }
        patient.setMedicalRecordNumber(mrn);
        patient.setUserId(user.getUserId());

        patient.setPreferredName(form.getPreferredName());

        //converting String birthDate to a LocalDate object
        Integer day = Integer.parseInt(form.getBirthDate().split("-")[2]);
        Integer month = Integer.parseInt(form.getBirthDate().split("-")[1]);
        Integer year = Integer.parseInt(form.getBirthDate().split("-")[0]);
        LocalDate date = LocalDate.of(year, month, day);

        patient.setBirthDate(date);

        patient.setGender(form.getGender());
        patient.setPronouns(form.getPronouns());
        patient.setSex(form.getSex());
        patient.setPrimaryLanguage(form.getPrimaryLanguage());

        patientDao.save(patient);

        log.info(form.toString());

        response.setViewName("redirect:/user/schedule_appointment");

        return response;
    }
}

