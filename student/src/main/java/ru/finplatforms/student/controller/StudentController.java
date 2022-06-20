package ru.finplatforms.student.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springdoc.api.annotations.ParameterObject;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.finplatforms.student.domain.entity.Student;
import ru.finplatforms.student.domain.entity.StudentGroup;
import ru.finplatforms.student.domain.view.StudentView;
import ru.finplatforms.student.repository.StudentGroupRepository;
import ru.finplatforms.student.repository.StudentRepository;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping(path = "/student")
public class StudentController {

    private final StudentRepository studentRepository;
    private final StudentGroupRepository studentGroupRepository;

    @PostMapping
    public ResponseEntity<Long> createStudent(@RequestBody Student request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentRepository.save(request).getId());
    }

    @GetMapping
    public Page<StudentView> getPageStudent(@ParameterObject Pageable page) {
        return studentRepository.findAllProjectionBy(page);
    }

    @GetMapping(path = "/{id}")
    public ResponseEntity<StudentView> getStudentById(@PathVariable Long id) {
        return ResponseEntity.of(studentRepository.findAllProjectionById(id));
    }

    @DeleteMapping(path = "/{id}")
    public ResponseEntity<?> removeStudentById(@PathVariable Long id) {
        try {
            studentRepository.deleteById(id);
        } catch (EmptyResultDataAccessException exception) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(id);
    }

    @PostMapping(path = "/group")
    public ResponseEntity<Integer> createStudentGroup(@RequestBody StudentGroup request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(studentGroupRepository.save(request).getId());
    }

    @GetMapping(path = "/group/{id}")
    public ResponseEntity<StudentGroup> getStudentGroupById(@PathVariable Integer id) {
        return ResponseEntity.of(studentGroupRepository.findById(id));
    }

}
