package ru.vladislav.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import ru.vladislav.models.Token;
import ru.vladislav.models.User;

import java.util.List;

public interface TokensRepo extends JpaRepository<Token, Long>{

    Token findTokenByToken(String token);

    List<Token> findTokensByUser(User user);

}
