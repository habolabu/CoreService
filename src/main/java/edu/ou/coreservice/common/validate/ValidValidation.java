package edu.ou.coreservice.common.validate;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Component;

import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;
import java.util.Objects;

@Component
public class ValidValidation {
    private final ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
    private final Validator validator = factory.getValidator();

    /**
     * Validate object with specific class
     *
     * @param input      object which will be validated
     * @param inputClass type of object
     * @return valid or not
     * @author Nguyen Trung Kien - OU
     */
    public boolean isInValid(
            Object input,
            Class<?> inputClass
    ) {
        return Objects.isNull(inputClass.cast(input))
                || !validator.validate(inputClass.cast(input))
                .isEmpty();
    }


    /**
     * Validate an object
     *
     * @param input object
     * @return valid or not
     * @author Nguyen Trung Kien - OU
     */
    public boolean isInValid(Object input) {
        if (input instanceof String) {
            return StringUtils.isBlank((CharSequence) input);
        }
        return Objects.isNull(input);
    }
}
