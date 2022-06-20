package ru.finplatforms.student.domain.view;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonUnwrapped;

import java.util.Date;

public interface StudentView {
    Long getId();
    String getFirstName();
    String getLastName();
    String getSecondName();

    @JsonFormat(pattern = "dd.MM.yyyy")
    Date getDateOfBirth();

    @JsonUnwrapped
    StudentGroupView getGroup();

    interface StudentGroupView {
        @JsonProperty("group")
        String getTitle();
    }

}
