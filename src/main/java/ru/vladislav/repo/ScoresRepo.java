package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.models.Score;

public interface ScoresRepo extends JpaRepository<Score, Long>{

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query("update Score score set score.score = :score where score.id = :id")
    int update(@Param("id") Long id, @Param("score") int score);

}
