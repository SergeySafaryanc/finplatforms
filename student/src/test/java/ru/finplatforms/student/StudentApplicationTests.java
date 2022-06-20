package ru.finplatforms.student;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.SneakyThrows;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.test.web.servlet.MockMvc;
import ru.finplatforms.student.domain.entity.Student;
import ru.finplatforms.student.domain.entity.StudentGroup;

import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
public class StudentApplicationTests {

    @Autowired
    protected MockMvc mockMvc;
    @Autowired
    protected ObjectMapper objectMapper;

    @Test
    void contextLoads() {

    }

    @SneakyThrows
    protected List<Student> getStudents() {
        var filePath = "src/test/resources/students.json";
        return Arrays.stream(objectMapper.readValue(Paths.get(filePath).toFile(), Student[].class)).toList();
    }

    @SneakyThrows
    protected List<StudentGroup> getStudentGroups() {
        var filePath = "src/test/resources/student_groups.json";
        return Arrays.stream(objectMapper.readValue(Paths.get(filePath).toFile(), StudentGroup[].class)).toList();
    }



}