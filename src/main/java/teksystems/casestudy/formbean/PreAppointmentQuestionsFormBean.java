package teksystems.casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.*;

@Getter
@Setter
@ToString
public class PreAppointmentQuestionsFormBean {

//    private String apptId;

    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    @NotBlank(message="Medical concern is required")
    private String complaint;

    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    @NotBlank(message="Please enter the time or date of when your concern started")
    private String onset;

    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    @NotBlank(message="Location is required")
    private String location;

    //    @NotBlank(message="Duration is required")
    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    private String duration;

    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    @NotBlank(message="Description is required")
    private String description;

//    @NotBlank(message="Relief sources is required")
    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    private String alleviating;

//    @NotBlank(message="Symptom radiation is required")
    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    private String radiation;

    //    @NotBlank(message="Frequency is required")
    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    private String temporalPatterns;

    @Length(max = 200, message = "Entry must contain less than 200 characters.")
    @NotBlank(message="Please enter symptoms")
    private String symptoms;
}
