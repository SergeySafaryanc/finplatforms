package ru.finplatforms.student.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import ru.finplatforms.student.domain.entity.Student;
import ru.finplatforms.student.domain.view.StudentView;

import java.util.Optional;

public interface StudentRepository extends JpaRepository<Student, Long> {
    Page<StudentView> findAllProjectionBy(Pageable page);
    Optional<StudentView> findAllProjectionById(Long id);
}
