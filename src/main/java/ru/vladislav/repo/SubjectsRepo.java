package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladislav.models.Subject;

public interface SubjectsRepo extends JpaRepository<Subject, Long>{
}
