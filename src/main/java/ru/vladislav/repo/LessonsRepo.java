package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladislav.models.Lesson;

public interface LessonsRepo extends JpaRepository<Lesson, Long> {
}
