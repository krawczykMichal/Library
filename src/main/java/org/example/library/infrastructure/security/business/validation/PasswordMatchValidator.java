package org.example.library.infrastructure.security.business.validation;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.example.library.api.dto.PasswordDTO;
import org.example.library.api.dto.UsersDTO;


public class PasswordMatchValidator implements ConstraintValidator<PasswordMatch, PasswordDTO> {

    @Override
    public boolean isValid(PasswordDTO dto, ConstraintValidatorContext context) {
        if (dto == null || dto.getUsersUserPassword() == null || dto.getConfirmPassword() == null) {
            return false;
        }

        boolean isValid = dto.getUsersUserPassword().equals(dto.getConfirmPassword());

        if (!isValid) {
            context.disableDefaultConstraintViolation();
            context.buildConstraintViolationWithTemplate("Passwords do not match")
                    .addPropertyNode("confirmPassword")
                    .addConstraintViolation();
        }

        return isValid;
    }
}