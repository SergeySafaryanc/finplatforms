package ru.finplatforms.student.domain.entity;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.ObjectIdGenerators.PropertyGenerator;
import lombok.Data;
import lombok.ToString;
import ru.finplatforms.student.utils.EntityIdResolver;

import javax.persistence.*;
import java.util.List;

@Data
@Entity
@JsonIdentityInfo(
        property = "id",
        generator = PropertyGenerator.class,
        resolver = EntityIdResolver.class,
        scope = StudentGroup.class
)
public class StudentGroup {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    @Column(unique = true)
    private String title;

    @JsonIgnore
    @ToString.Exclude
    @OneToMany(mappedBy = "group", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Student> students;

    public StudentGroup() {
    }

    public StudentGroup(String title) {
        this.title = title;
    }
}
