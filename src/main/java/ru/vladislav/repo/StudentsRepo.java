package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladislav.models.Student;

public interface StudentsRepo extends JpaRepository<Student, Long> {
}
