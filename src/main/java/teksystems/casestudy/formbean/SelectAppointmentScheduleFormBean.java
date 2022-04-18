package teksystems.casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import teksystems.casestudy.database.entity.Clinician;
import teksystems.casestudy.validation.EmailUnique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
public class SelectAppointmentScheduleFormBean {

//    private Integer day;
//
//    private Integer month;
//
//    private Integer year;

    private String date;

    private Integer userId;

//    @NotBlank(message="Please enter an appointment time")
    private String time;

    //Used for Editing

//    @NotBlank(message="Please enter a clinician id")
    private String clinicianId;

//    @NotBlank(message="Please enter a patient id")
    private String patientId;

//    @NotBlank(message="Please enter a chief complaint")
    private String chiefComplaint;

//    @NotBlank(message= "Please enter the paqId corresponding to this appointment")
    private String paqId;

}
