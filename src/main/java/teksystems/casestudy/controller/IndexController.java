package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.User;

import java.sql.Time;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.Date;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserDAO userDAO;

    @RequestMapping(value = "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception{
        ModelAndView response = new ModelAndView();

        response.setViewName("login/loginForm");

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if(!StringUtils.equals("anonymousUser", currentPrincipalName)){
            User user = userDAO.findByEmail(currentPrincipalName);
            response.addObject(user);
            if(user.getUserRole() == "PATIENT"){
                response.setViewName("redirect:/user/my_schedule/" + user.getUserId());
            } else {
                response.setViewName("redirect:/clinician/my_clinician_schedule/" + user.getUserId());
            }
        }

        return response;
    }

}
