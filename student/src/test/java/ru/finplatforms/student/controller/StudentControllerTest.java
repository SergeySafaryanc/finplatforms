package ru.finplatforms.student.controller;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.projection.SpelAwareProxyProjectionFactory;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import ru.finplatforms.student.StudentApplicationTests;
import ru.finplatforms.student.domain.view.StudentView;
import ru.finplatforms.student.repository.StudentGroupRepository;
import ru.finplatforms.student.repository.StudentRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class StudentControllerTest extends StudentApplicationTests {

    @MockBean
    StudentRepository studentRepository;
    @MockBean
    StudentGroupRepository studentGroupRepository;


    @Test
    void createStudent() throws Exception {
        var student = getStudents().get(0);

        Mockito.when(
                studentRepository.save(student)
        ).thenReturn(
                student
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/student")
                .content(objectMapper.writeValueAsString(student))
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    @Test
    void getPageStudent() throws Exception {
        Mockito.when(
                studentRepository.findAllProjectionBy(PageRequest.of(0, 10))
        ).thenReturn(
                new PageImpl<>(getStudents().stream().limit(10)
                        .map(s -> new SpelAwareProxyProjectionFactory().createProjection(StudentView.class, s))
                        .toList())
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/student?page=0&size=10"))
                .andDo(MockMvcResultHandlers.print())
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.content[0].id").value(1));
    }

    @Test
    void positiveTestGetStudentById() throws Exception {
        Mockito.when(
                studentRepository.findAllProjectionById(1L)
        ).thenReturn(
                getStudents().stream().filter(s -> s.getId().equals(1L))
                        .map(s -> new SpelAwareProxyProjectionFactory().createProjection(StudentView.class, s))
                        .findFirst()
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/student/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void negativeTestGetStudentById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/99999999"))
                .andExpect(status().isNotFound());
    }

    @Test
    void removeStudentById() throws Exception {
        Mockito.when(
                studentRepository.findAllProjectionById(1L)
        ).thenReturn(
                getStudents().stream().filter(s -> s.getId().equals(1L))
                        .map(s -> new SpelAwareProxyProjectionFactory().createProjection(StudentView.class, s))
                        .findFirst()
        );

        mockMvc.perform(MockMvcRequestBuilders.delete("/student/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    @Test
    void testCreateStudentGroup() throws Exception {
        var studentGroup = getStudentGroups().get(0);

        Mockito.when(
                studentGroupRepository.save(studentGroup)
        ).thenReturn(
                studentGroup
        );

        mockMvc.perform(MockMvcRequestBuilders.post("/student/group")
                        .content(objectMapper.writeValueAsString(studentGroup))
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$").isNumber())
                .andExpect(jsonPath("$").value(1));
    }

    @Test
    void positiveTestGetStudentGroupById() throws Exception {
        Mockito.when(
                studentGroupRepository.findById(1)
        ).thenReturn(
                getStudentGroups().stream().filter(s -> s.getId().equals(1)).findFirst()
        );

        mockMvc.perform(MockMvcRequestBuilders.get("/student/group/1"))
                .andExpect(status().is2xxSuccessful())
                .andExpect(jsonPath("$.id").isNumber())
                .andExpect(jsonPath("$.id").value(1));
    }

    @Test
    void negativeTestGetStudentGroupById() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get("/student/group/999"))
                .andExpect(status().isNotFound());
    }
}