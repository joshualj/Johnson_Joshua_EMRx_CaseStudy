package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.formbean.RegisterFormBean;

@Slf4j
@Controller
public class AppointmentController {

    @Autowired
    private AppointmentDAO appointmentDao;

    @RequestMapping(value="/user/schedule_appointment", method = RequestMethod.GET)
    public ModelAndView schedule() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/schedule_appointment");

        /* all these 2 lines of code are doing is seeding teh mdoel with an
        // empty form bean so JSP page substitutions will not error out
        // in this case spring is being nice enough not to throw errors but
        // these 2 lines are safety
        */
        RegisterFormBean form = new RegisterFormBean();
        response.addObject("form", form);

        return response;
    }

}
