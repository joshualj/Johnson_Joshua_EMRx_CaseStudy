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
    public ModelAndView searchClinician(@RequestParam (required = false) String searchEntry,
                                                  @RequestParam (required = false) String searchType) {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/search");

        List<Clinician> clinicians = new ArrayList<>();
        List<User> users = new ArrayList<>();

        if (!StringUtils.isEmpty(searchEntry) && StringUtils.equals("lastName", searchType)) {
            List<User> allUsers = userDao.findByLastNameIgnoreCaseContaining(searchEntry);
            //since this is a clinician search, I need specify users who are clinicians
            for(User user : allUsers) {
                if(clinicianDao.findByUserId(user.getUserId()) != null) {
                    users.add(user);
                }
            }
            for(User user : users) {
                Clinician clinician = clinicianDao.findByUserId(user.getUserId());
                clinicians.add(clinician);
            }
        }

        if (!StringUtils.isEmpty(searchEntry) && StringUtils.equals("department", searchType)) {
            clinicians = clinicianDao.findByDepartment(searchEntry);

            for(Clinician clinician : clinicians) {
                User user = userDao.findByUserId(clinician.getUserId());
                users.add(user);
            }
        }

        if (!StringUtils.isEmpty(searchEntry) && StringUtils.equals("language", searchType)) {
            clinicians = clinicianDao.findByLanguagesIgnoreCaseContaining(searchEntry);

            for(Clinician clinician : clinicians) {
                User user = userDao.findByUserId(clinician.getUserId());
                users.add(user);
            }
        }

        response.addObject("clinicians", clinicians);
        response.addObject("searchEntry", searchEntry);
        response.addObject("users", users);

        return response;

    }

    @PreAuthorize("hasAuthority('CLINICIAN')")
    @RequestMapping(value="/clinician/register_clinician", method = RequestMethod.GET)
    public ModelAndView registerClinicianUser() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("clinician/register_clinician");

        ClinicianRegisterFormBean form = new ClinicianRegisterFormBean();
        response.addObject("form", form);

        return response;
    }

    @PreAuthorize("hasAuthority('CLINICIAN')")
    @RequestMapping(value="/clinician/register_clinicianSubmit", method = {RequestMethod.GET, RequestMethod.POST})
    public ModelAndView registerClinicianUserSubmit(@Valid ClinicianRegisterFormBean form, BindingResult bindingResult) throws Exception {
        ModelAndView response = new ModelAndView();

        if (bindingResult.hasErrors()) {
            List<String> errorMessages = new ArrayList<>();

            for(ObjectError error : bindingResult.getAllErrors()) {
                errorMessages.add(error.getDefaultMessage());
                log.info( ((FieldError) error).getField() + " " + error.getDefaultMessage());
            }

            response.addObject("form", form);
            response.addObject("bindingResult", bindingResult);

            response.setViewName("clinician/register_clinician");
            return response;
        }

        //search for user by email. If not found, create new user
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

        //must save user so that user_id is generated, and can be assigned
        // when clinician object is instantiated, below
        userDao.save(user);

        //create new clinician object from user object
        Clinician clinician = clinicianDao.findByUserId(user.getUserId());

        if (clinician == null) {
            clinician = new Clinician();
        }

        clinician.setUserId(user.getUserId());
        clinician.setTitle(form.getTitle());
        clinician.setDepartment(form.getDepartment());
        clinician.setLanguages(form.getLanguages());

        clinicianDao.save(clinician);

        //redirect to clinician's schedule
        response.setViewName("redirect:/clinician/my_clinician_schedule");
        //TODO: Change to clinician my schedule, today's date

        return response;
    }

    @PreAuthorize("hasAuthority('CLINICIAN')")
    @RequestMapping(value= "/clinician/my_clinician_schedule", method = RequestMethod.GET)
    public ModelAndView viewClinicianScheduleAsClinician(@RequestParam(required = false) Integer userId,
                                                       @RequestParam(required = false) String date) throws Exception {

        ModelAndView response = new ModelAndView();
        response.setViewName("clinician/my_clinician_schedule");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        //if userId is not provided, set userId equal to the logged-in user's id
        if(!StringUtils.equals("anonymousUser", currentPrincipalName) && (userId == null)){
            userId = userDao.findByEmail(currentPrincipalName).getUserId();
        }

        //get user object with id of userId
        User user = userDao.findByUserId(userId);

        //find clinician with user id of userId, and get the clinician ID show today's schedule
        Clinician clinician = clinicianDao.findByUserId(userId);
        Integer clinicianId = clinician.getClinicianId();

        //date formatting. Setting default to today's date, if date is not provided
        LocalDate currentDate = LocalDate.now();

        //if the inputted date is null, set the date to today's date
        LocalDate dateFormatted = (date != null) ?
                LocalDate.of(Integer.parseInt(date.split("-")[0]),
                        Integer.parseInt(date.split("-")[1]),
                        Integer.parseInt(date.split("-")[2])) : currentDate;

        //Getting variables to send to .jsp. With these Strings & Variables,
        // we can see: "Tuesday, April 19, 2022"
        String dayOfWeek = dateFormatted.getDayOfWeek().toString().substring(0, 1).toUpperCase() + dateFormatted.getDayOfWeek().toString().substring(1).toLowerCase();
        String monthName = dateFormatted.getMonth().toString().substring(0, 1).toUpperCase() + dateFormatted.getMonth().toString().substring(1).toLowerCase();
        Integer year = dateFormatted.getYear();
        String yearDate = year.toString();
        Integer dayDate = Integer.parseInt(dateFormatted.toString().split("-")[2]);

        //finding all appointments of a clinician on a specific date
        List<Appointment> appointments =
                appointmentDao.findByClinicianClinicianIdAndDate(clinicianId, dateFormatted);

        //TODO: Need to sort appointments!
        //appointments.sort(Comparator.comparing(Appointment::getTime));

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
//        response.addObject("userId", userId);
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
