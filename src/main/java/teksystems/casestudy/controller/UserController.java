package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.PatientDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.RegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Slf4j
@Controller
public class UserController{

    @Autowired
    private UserDAO userDao;

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
    public ModelAndView registerSubmit(@Valid RegisterFormBean form) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

//        if (bindingResult.hasErrors()) {
//            List<String> errorMessages = new ArrayList<>();
//
//            bindingResult.getFieldErrorCount("firstName");
//
//            for(ObjectError error : bindingResult.getAllErrors()) {
//                errorMessages.add(error.getDefaultMessage());
////                errors.put( ((FieldError) error).getField(), error.getDefaultMessage());
//                log.info( ((FieldError) error).getField() + " " + error.getDefaultMessage());
//            }
//
//            response.addObject("form", form);
//
//            response.addObject("bindingResult", bindingResult);
//
//            //add error list ot the model
////            response.addObject("errorMessages", errorMessages);
//
//
//            //because there is 1 or more error, we do not want to process logic below
//            // that will create a new user in the database. We want to show the register_clinician.jsp
//
//            response.setViewName("user/register");
//            return response;
//        }


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

        patientDao.save(patient);

        log.info(form.toString());

        //here instead of showing a view, we want to redirect for the edit page
        //the edit page will then be responsible for loading the user from the database
        //and dynamically creating the page
        //Browser to do a redirect to the URL after the. The big piece here to
        // recognize is redirect uses an actual URL rather than a view ??
        response.setViewName("redirect:/user/schedule_appointment");

        return response;
    }
}

//@Slf4j
//@Controller
//public class UserController {
//
//    @Autowired
//    private UserDAO userDao;
//    /*
//    //This is the controller method for the entry point of the user registration page.
//    //It does not do anything really other than provide a route to the register_clinician.jsp page.
//    */
//    @RequestMapping(value="/user/register", method = RequestMethod.GET)
//    public ModelAndView register() throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/register");
//
//
//        /* all these 2 lines of code are doing is seeding teh mdoel with an
//        // empty form bean so JSP page substitutions will not error out
//        // in this case spring is being nice enough not to throw errors but
//        // these 2 lines are safety
//        */
//        RegisterFormBean form = new RegisterFormBean();
//        response.addObject("form", form);
//
//        return response;
//    }
//
//    /*
//    When user submits form, it will call into this method
//    1) action of form must match value here in request
//    2) method of form must match value here
//    */
//
//    @RequestMapping(value="/user/registerSubmit", method = RequestMethod.POST)
//    public ModelAndView registerSubmit(RegisterFormBean form) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/register");
//
//        //we first assume we are going to do an edit by loading the user by loading the user from
//        // the database using the incoming id on the form
//        User user = new User();
//
//        //now check if the id in the register in the form bean is not null, then query it
//        //from the database
//        if(form.getId() != null) {
//            //now, if user from database is null then it means we did not find this user. Therefore, it is a create.
//            user = userDao.findById(form.getId());
//        }
//
//        if (user == null) {
//            user = new User();
//        }
//
//        user.setEmail(form.getEmail());
//        user.setFirstName(form.getFirstName());
//        user.setLastName(form.getLastName());
//        user.setPassword(form.getPassword());
//
//        userDao.save(user);
//
//        log.info(form.toString());
//
//        //here instead of showing a view, we want to redirect for the edit page
//        //the edit page will then be responsible for loading the user from the database
//        //and dynamically creating the page
//        //Browser to do a redirect to the URL after the. The big piece here to
//        // recognize is redirect uses an actual URL rather than a view ??
//        response.setViewName("redirect:/user/edit/" + user.getId());
//
//        return response;
//    }
//
//
//
//    /* This method is for editing a user.
//    There is a path paramater being used to pass the userid for the user that is to be edited.
//    In this case, the @GetMapping is equivalent to @Request Mapping
//     */
//
//    @RequestMapping(value="/user/edit/{userId}", method = RequestMethod.GET)
////    @GetMapping("user/edit/{userId}")
//    public ModelAndView editUser(@PathVariable("userId") Integer userId) throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/register");
//
//        User user = userDao.findById(userId);
//
//        RegisterFormBean form = new RegisterFormBean();
//
//        form.setId(user.getId());
//        form.setEmail(user.getEmail());
//        form.setFirstName(user.getFirstName());
//        form.setLastName(user.getLastName());
//        form.setPassword(user.getPassword());
//        form.setConfirmPassword(user.getPassword());
//
//        response.addObject("form", form);
//
//        return response;
//
//    }
//
//}

//    @RequestMapping(value="/user/registerSubmit", method = RequestMethod.POST)
//    public ModelAndView register() throws Exception {
//        ModelAndView response = new ModelAndView();
//        response.setViewName("user/register");
//    }

