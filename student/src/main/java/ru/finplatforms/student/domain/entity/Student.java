package ru.finplatforms.student.domain.entity;

import com.fasterxml.jackson.annotation.*;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;
import lombok.ToString;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
public class Student {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Schema(description = "ИД", nullable = true, hidden = true)
    private Long id;

    @Schema(description = "Имя", example = "Иван", required = true)
    private String firstName;
    @Schema(description = "Фамилия", example = "Иванов", required = true)
    private String lastName;
    @Schema(description = "Отчество", example = "Иванович", required = true)
    private String secondName;

    @Temporal(TemporalType.DATE)
    @JsonFormat(pattern = "yyyy-MM-dd")
    @Schema(type = "string", description = "Дата рождения в формате (yyyy-MM-dd)", format = "date", example = "2000-06-19", required = true)
    private Date dateOfBirth;

    @ManyToOne
    @ToString.Exclude
    @JoinColumn(name = "group_id")
    @JsonProperty(value = "groupId")
    @JsonIdentityReference(alwaysAsId = true)
    private StudentGroup group;

}
