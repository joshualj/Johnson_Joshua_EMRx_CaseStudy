package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.PreAppointmentQuestionsDAO;
import teksystems.casestudy.formbean.PreAppointmentQuestionsFormBean;
import teksystems.casestudy.formbean.RegisterFormBean;

@Slf4j
@Controller
public class PreAppointmentQuestionsController {

    @Autowired
    private PreAppointmentQuestionsDAO paqDao;

    @RequestMapping(value= "user/paq", method = RequestMethod.GET)
    public ModelAndView paq() throws Exception {
        ModelAndView response = new ModelAndView();
        response.setViewName("user/paq");

        //attributeName is object inside of jsp, and scheduledTime is the object that is being passed to that name

        PreAppointmentQuestionsFormBean form = new PreAppointmentQuestionsFormBean();
        response.addObject("form", form);

        return response;
    }
}
