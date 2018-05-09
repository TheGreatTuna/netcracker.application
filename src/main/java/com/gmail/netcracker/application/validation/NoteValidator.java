package com.gmail.netcracker.application.validation;

import com.gmail.netcracker.application.dto.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.PropertySource;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@PropertySource(value = "classpath:message_en.properties")
public class NoteValidator implements Validator {

    @Autowired
    MessageSource messageSource;

    @Override
    public boolean supports(Class<?> aClass) {
        return Note.class.equals(aClass);
    }

    @Override
    public void validate(Object o, Errors errors) {
        Note note = (Note)o;
        ValidationUtils.rejectIfEmptyOrWhitespace(errors, "name", "required.field");
        if(note.getDescription().equals("<br>")){
            errors.rejectValue("description","required.field");
        }
    }
}
