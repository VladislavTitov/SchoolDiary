package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladislav.models.Score;

public interface ScoresRepo extends JpaRepository<Score, Long>{
}
