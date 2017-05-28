package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.models.User;

public interface UsersRepo extends JpaRepository<User, Long> {

    @Query(value = "select user from User user where user.name = :userName")
    User findByUserName(@Param("userName") String userName);

    @Modifying(clearAutomatically = true)
    @Transactional
    @Query(value = "update User user set user.name = :name, user.password = :password where user.id = :userId")
    int update(@Param("name") String name, @Param("password") String password, @Param("userId") Long id);

}
