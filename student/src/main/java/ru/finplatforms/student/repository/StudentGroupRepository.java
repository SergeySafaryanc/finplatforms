package ru.finplatforms.student.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.finplatforms.student.domain.entity.StudentGroup;

public interface StudentGroupRepository extends JpaRepository<StudentGroup, Integer> {
}
