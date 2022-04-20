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
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.User;

@Slf4j
@Controller
public class LoginController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value="/login/login", method = RequestMethod.GET)
    public ModelAndView login() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("login/loginForm"); //may want to change this to "/index"

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String currentPrincipalName = authentication.getName();

        if(!StringUtils.equals("anonymousUser", currentPrincipalName)){
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