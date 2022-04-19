package teksystems.casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class AppointmentEditorFormBean {

//    private Integer day;
//
//    private Integer month;
//
//    private Integer year;

    @NotBlank(message="Please enter an appointment date")
    private String date;

    private Integer userId;

    @NotBlank(message="Please enter an appointment time")
    private String time;

    //Used for Editing

    @NotBlank(message="Please enter a clinician id")
    private String clinicianId;

    @NotBlank(message="Please enter a patient id")
    private String patientId;

    private String chiefComplaint;

    private String paqId;

}
