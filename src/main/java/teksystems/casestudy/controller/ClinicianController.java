package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.ClinicianRegisterFormBean;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@Slf4j
@Controller
public class ClinicianController {

    @Autowired
    private ClinicianDAO clinicianDao;

    @Autowired
    private UserDAO userDao;


    @GetMapping(value="/user/search")//, method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchByLastName(@RequestParam (required = false) String searchLastName) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        List<Clinician> clinicians = new ArrayList<>();
        List<User> users = new ArrayList<>();


        if (!StringUtils.isEmpty(searchLastName)) {
            users = userDao.findByLastNameIgnoreCaseContaining(searchLastName);
        }

        for(User user : users) {
            Clinician clinician = clinicianDao.findByUserId(user.getUserId());
            clinicians.add(clinician);
        }

        log.info(users.toString());

        response.addObject("clinicians", clinicians);
        response.addObject("searchLastName", searchLastName);
        response.addObject("users", users);

        return response;

    }

    @RequestMapping(value="/clinician/register_clinician", method = RequestMethod.GET)
    public ModelAndView registerClinician() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("clinician/register_clinician");

        ClinicianRegisterFormBean form = new ClinicianRegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    @RequestMapping(value="/clinician/register_clinicianSubmit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registerClinicianSubmit(@Valid ClinicianRegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        log.info(form.toString());

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            bindingResult.getFieldErrorCount("title");

            for(ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info( ((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);

            response.setViewName("clinician/register_clinician");
            return response;
        }


        Clinician clinician = clinicianDao.findByUserId(form.getUserId());

        //TODO Add new user

        if (clinician == null) {
            clinician = new Clinician();
        }

//        clinician.setFirstName(form.getFirstName());
//        clinician.setLastName(form.getLastName());
//        clinician.setEmail(form.getEmail());
//        clinician.setPassword(form.getPassword());
        clinician.setUserId(form.getUserId());
        clinician.setTitle(form.getTitle());
        clinician.setDepartment(form.getDepartment());
        clinician.setLanguages(form.getLanguages());

        log.info(clinician.toString());

        clinicianDao.save(clinician);

        log.info(form.toString());

        //here instead of showing a view, we want to redirect for the edit page
        //the edit page will then be responsible for loading the user from the database
        //and dynamically creating the page
        //Browser to do a redirect to the URL after the. The big piece here to
        // recognize is redirect uses an actual URL rather than a view ??
        response.setViewName("redirect:/user/my_schedule");
        //TODO: Change to clinician my schedule, today's date

        return response;
    }

}
