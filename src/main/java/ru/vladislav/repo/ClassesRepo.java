package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.models.ClassOfStudents;

public interface ClassesRepo extends JpaRepository<ClassOfStudents, Long> {

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update ClassOfStudents classofstudents set classofstudents.name = :name where classofstudents.id = :classId")
    int update(@Param("classId") Long id, @Param("name") String name);

}
