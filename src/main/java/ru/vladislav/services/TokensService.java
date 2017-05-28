package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import ru.vladislav.models.Token;
import ru.vladislav.models.User;
import ru.vladislav.repo.TokensRepo;
import ru.vladislav.repo.UsersRepo;

@Service
public class TokensService {

    @Qualifier("tokensRepo")
    @Autowired
    private TokensRepo tokensRepo;
    @Qualifier("usersRepo")
    @Autowired
    private UsersRepo usersRepo;


    public User getUserByToken(String token){
        return tokensRepo.findTokenByToken(token).getUser();
    }

    public Token saveToken(String token, Long userId){
        Token tokenObj = new Token(null, token, usersRepo.findOne(userId));
        return tokensRepo.save(tokenObj);
    }

    public void deleteToken(String token, Long userId){
        Token tokenObj = new Token(null, token, usersRepo.findOne(userId));
        tokensRepo.delete(tokenObj);
    }

}
