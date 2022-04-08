package teksystems.casestudy.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.User;

import java.util.List;

@Slf4j
@Controller
public class IndexController {

    @Autowired
    private UserDAO userDao;

    @RequestMapping(value= "/index", method = RequestMethod.GET)
    public ModelAndView index() throws Exception {
        ModelAndView response = new ModelAndView();

        User user = userDao.findByEmail("rb2020@gmail.com");
        List<User> users2 = userDao.findByUserType("patient");

        response.setViewName("index");
        response.addObject("user", user);

        for (User individual : users2) {
            log.info(individual.toString());
        }

        return response;
    }
}
