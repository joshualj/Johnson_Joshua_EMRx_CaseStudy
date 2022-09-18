package teksystems.casestudy.controller;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;

import com.google.gson.Gson;

import java.time.LocalDate;
import java.time.LocalTime;

import teksystems.casestudy.database.dao.AppointmentDAO;
import teksystems.casestudy.database.dao.ClinicianDAO;
import teksystems.casestudy.database.dao.UserDAO;
import teksystems.casestudy.database.entity.Appointment;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.database.entity.User;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@WebServlet("/ajaxAppointment")
public class UpdateClinician extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Autowired
    private AppointmentDAO appointmentDao;

    @Autowired
    private UserDAO userDao;

    public UpdateClinician() {
        super();
    }
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException { 
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Map<String, List<Clinician>> clinicianMap = new HashMap<String, List<Clinician>>();
        Map<String, List<User>> userMap = new HashMap<String, List<User>>();
        Map<String, Object> validMap = new HashMap<String, Object>();
        boolean isValid = false;
        // String date = request.getParameter("date");
        // String time = request.getParameter("time");

        String[] timeDateArray = request.getParameter("timeDate").split("&");
        String timeString = timeDateArray[0];
        String dateString = timeDateArray[1];
        List<Clinician> availableClinicians = new ArrayList<>();
        List<User> availableClinicianUsers = new ArrayList<>();
        List<Appointment> appointments = new ArrayList<>();
        if((dateString != null) && (timeString != null)) {
    
            LocalTime timeFormatted = LocalTime.parse(timeString); 
            LocalDate dateFormatted = LocalDate.parse(dateString); 
            appointments = appointmentDao.findByDateAndTime(dateFormatted, timeFormatted);
            appointments.stream().filter(apt -> apt.getPatient() == null);

            if(!appointments.isEmpty()){
                isValid = true;

                for (Appointment appt : appointments) {
                    if(!availableClinicians.contains(appt.getClinician())){
                        availableClinicians.add(appt.getClinician());
                    }
                }
    
                for (Clinician clin : availableClinicians) {
                    availableClinicianUsers.add(userDao.getById(clin.getUserId()));
                }
    
                // clinicianMap.put("allClinicians", availableClinicians);
                userMap.put("allClinUsers", availableClinicianUsers);
            }
        }
        validMap.put("isValid", isValid);
        write(response, userMap, validMap);
    }

    private void write(HttpServletResponse response, Map<String, List<User>> userMap, Map<String, Object> validMap) throws IOException {
            response.setContentType("application/json");
            response.setCharacterEncoding("UTF-8");
            response.getWriter().write(new Gson().toJson(userMap));
            response.getWriter().write(new Gson().toJson(validMap));
            // response.getWriter().write(new Gson().toJson(clinicianMap));
    }
}