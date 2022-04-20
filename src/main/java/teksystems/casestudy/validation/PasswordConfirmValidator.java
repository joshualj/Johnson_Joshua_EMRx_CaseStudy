package teksystems.casestudy.validation;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.expression.spel.standard.SpelExpressionParser;

@Slf4j
@ToString
public class PasswordConfirmValidator implements ConstraintValidator<PasswordConfirm, Object> {

    private final SpelExpressionParser parser = new SpelExpressionParser();
    private String password;
    private String passwordConfirm;

    @Override
    public void initialize(PasswordConfirm constraintAnnotation) {
        password = constraintAnnotation.password();
        passwordConfirm = constraintAnnotation.passwordConfirm();
    }

    @Override
    public boolean isValid(Object value, ConstraintValidatorContext context) {
        Object pass = parser.parseExpression(password).getValue(value);
        Object passCon = parser.parseExpression(passwordConfirm).getValue(value);

        return pass.equals(passCon);
    }

}
