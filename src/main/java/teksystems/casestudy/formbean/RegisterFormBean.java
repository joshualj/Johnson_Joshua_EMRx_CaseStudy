package teksystems.casestudy.formbean;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;
import teksystems.casestudy.validation.EmailUnique;
import teksystems.casestudy.validation.PasswordConfirm;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.time.LocalDate;

@Getter
@Setter
@ToString
@PasswordConfirm(password = "password", passwordConfirm = "confirmPassword")
public class RegisterFormBean {

    private Integer userId;

    @NotBlank(message="Email is required")
    @EmailUnique(message="Email is already in use.")
    @Email(message = "Not a valid email format")
    private String email;

    @NotBlank(message="First name is required")
    private String firstName;

    @NotBlank(message="Second name is required")
    private String lastName;

    private String preferredName;

    @NotBlank(message="Birth date is required")
    private String birthDate;

    private String pronouns;

    private String gender;

    @NotBlank(message="Please enter your biological sex")
    private String sex;

    private String primaryLanguage;

    @Length(min = 3, max = 15, message = "Password must contain between 3 and 15 characters.")
    @NotBlank(message="Password is required")
    private String password;

    @NotBlank(message="Confirming password is required")
    private String confirmPassword;

    @NotBlank
    private String confirmRemove;
}
