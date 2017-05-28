package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladislav.models.Homework;

public interface HomeworksRepo extends JpaRepository<Homework, Long> {
}
