package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.Patient;
import teksystems.casestudy.database.entity.User;
import teksystems.casestudy.formbean.ClinicianRegisterFormBean;

import javax.validation.Valid;
import java.time.LocalDate;
import java.util.*;

@Slf4j
@Controller
public class ClinicianController {

    private final String[] appointmentTimes = {"08:00", "08:30", "09:00", "09:30", "10:00",
            "10:30", "11:00", "11:30", "12:00", "01:00", "01:30",
            "02:00", "02:30", "03:00", "03:30", "04:00"};

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private ClinicianDAO clinicianDao;

    @Autowired
    private AppointmentDAO appointmentDao;

    @Autowired
    private UserDAO userDao;

    @PreAuthorize("hasAnyAuthority('CLINICIAN','PATIENT')")
    @GetMapping(value="/user/search")//, method= {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView searchClinicianByLastName(@RequestParam (required = false) String searchLastName) {
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

        response.addObject("clinicians", clinicians);
        response.addObject("searchLastName", searchLastName);
        response.addObject("users", users);

        return response;

    }

    @PreAuthorize("hasAuthority('CLINICIAN')")
    @RequestMapping(value="/clinician/register_clinician", method = RequestMethod.GET)
    public ModelAndView registerClinician() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("clinician/register_clinician");

        ClinicianRegisterFormBean form = new ClinicianRegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    @PreAuthorize("hasAuthority('CLINICIAN')")
    @RequestMapping(value="/clinician/register_clinicianSubmit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registerClinicianSubmit(@Valid ClinicianRegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

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

        User user = userDao.findByEmail(form.getEmail());

        if (user == null) {
            user = new User();
        }

        user.setEmail(form.getEmail());
        user.setFirstName(form.getFirstName());
        user.setLastName(form.getLastName());
        String password = passwordEncoder.encode(form.getPassword());
        user.setPassword(password);
        user.setUserRole("CLINICIAN");

        userDao.save(user);

        Clinician clinician = clinicianDao.findByUserId(user.getUserId());

        if (clinician == null) {
            clinician = new Clinician();
        }

//        clinician.setFirstName(form.getFirstName());
//        clinician.setLastName(form.getLastName());
//        clinician.setEmail(form.getEmail());
//        clinician.setPassword(form.getPassword());
        clinician.setUserId(user.getUserId());
        //TODO: Fix user ID setter
        clinician.setTitle(form.getTitle());
        clinician.setDepartment(form.getDepartment());
        clinician.setLanguages(form.getLanguages());

        clinicianDao.save(clinician);

        //here instead of showing a view, we want to redirect for the edit page
        //the edit page will then be responsible for loading the user from the database
        //and dynamically creating the page
        //Browser to do a redirect to the URL after the. The big piece here to
        // recognize is redirect uses an actual URL rather than a view ??
        response.setViewName("redirect:/clinician/my_clinician_schedule/" + user.getUserId());
        //TODO: Change to clinician my schedule, today's date

        return response;
    }

    @PreAuthorize("hasAuthority('CLINICIAN')")
    @RequestMapping(value= "/clinician/my_clinician_schedule/{userId}", method = RequestMethod.GET)
    public ModelAndView viewClinicianScheduleAsClinician(@PathVariable("userId") Integer userId,
                                                       @RequestParam(required = false) String date) throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("clinician/my_clinician_schedule");

        //need to get userId to show today's schedule
        Clinician clinician = clinicianDao.findByUserId(userId);

        Integer year = ((date != null) && (date != "")) ? Integer.parseInt(date.split("-")[0]) : 2022;
        Integer month = ((date != null) && (date != "")) ? Integer.parseInt(date.split("-")[1]) : 4;
        Integer day = ((date != null) && (date != "")) ? Integer.parseInt(date.split("-")[2]) : 4;

        User user = userDao.findByUserId(userId);

        LocalDate dateFormatted = LocalDate.of(year, month, day);

        String dayOfWeek = dateFormatted.getDayOfWeek().toString().substring(0, 1).toUpperCase()
                + dateFormatted.getDayOfWeek().toString().substring(1).toLowerCase();

        String monthName = dateFormatted.getMonth().toString().substring(0, 1).toUpperCase()
                + dateFormatted.getMonth().toString().substring(1).toLowerCase();

        String yearDate = year.toString();
        String dayDate = day.toString();

        Integer clinicianId = clinician.getClinicianId();

        List<Appointment> appointments =
                appointmentDao.findByClinicianClinicianIdAndDate(clinicianId, dateFormatted);

        //TODO: Need to sort appointments!
//        appointments.sort(Comparator.comparing(Appointment::getTime));

        List<String> scheduledTime = new ArrayList<>();
        List<Patient> patients = new ArrayList<>();
        List<User> users = new ArrayList<>();

        for (Appointment appointment : appointments) {
            scheduledTime.add(appointment.getTime().toString());
            Patient patient = appointment.getPatient();
            patients.add(patient);
            Integer userIdOfPatient = patient.getUserId();
            users.add(userDao.getById(userIdOfPatient));
        }

        List<Clinician> clins = clinicianDao.findAll();
        List<User> clinicianUsers = new ArrayList<>();

        for (Clinician clin : clins) {
            User clinUser = userDao.findByUserId(clin.getUserId());
            clinicianUsers.add(clinUser);
        }

        response.addObject("clinicianUsers", clinicianUsers);
        response.addObject("clinUser", user);
        response.addObject("scheduledTime", scheduledTime);
        response.addObject("localDate", dateFormatted);
        response.addObject("dayOfWeek", dayOfWeek);
        response.addObject("userId", userId);
        response.addObject("monthName", monthName);
        response.addObject("dayDate", dayDate);
        response.addObject("yearDate", yearDate);
        response.addObject("clinicianId", clinicianId);
        response.addObject("appointmentTimes", appointmentTimes);

        //right now I might be using only these objects
        response.addObject("appointments", appointments);
        response.addObject("patients", patients);
        response.addObject("users", users);

        return response;
    }

}
