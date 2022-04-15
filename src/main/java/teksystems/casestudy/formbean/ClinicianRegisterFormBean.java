package teksystems.casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import teksystems.casestudy.validation.EmailUnique;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

@Getter
@Setter
@ToString
public class ClinicianRegisterFormBean {
//    private Integer userId; //probably need to delete this, once table field is deleted
//    private Integer id;

    @NotBlank(message="Email is required")
    @EmailUnique(message="Email is already in use.")
    @Email(message = "Not a valid email format")
    private String email;

    @NotBlank(message="First name is required")
    private String firstName;

    @NotBlank(message="Last name is required")
    private String lastName;

    @NotBlank(message="Title is required")
    private String title;

    @NotBlank(message="Department is required")
    private String department;

    @NotBlank(message="Languages is required")
    private String languages;

    @Length(min = 3, max = 15, message = "Password must contain between 3 and 15 characters.")
    @NotBlank(message="Password is required")
    private String password;

    @NotBlank(message="Confirming password is required")
    private String confirmPassword;
}
