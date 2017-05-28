package ru.vladislav.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.vladislav.converters.UsersConverter;
import ru.vladislav.dto.UserDto;
import ru.vladislav.dto.UserDtoForRegistration;
import ru.vladislav.exceptions.UserNotFoundException;
import ru.vladislav.exceptions.WrongPasswordException;
import ru.vladislav.models.Token;
import ru.vladislav.models.User;
import ru.vladislav.repo.TokensRepo;
import ru.vladislav.repo.UsersRepo;
import ru.vladislav.security.utils.TokenGenerator;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class UsersService {

    @Qualifier("usersRepo")
    @Autowired
    private UsersRepo usersRepo;

    @Autowired
    private TokensRepo tokensRepo;

    @Qualifier("tokenGeneratorImpl")
    @Autowired
    private TokenGenerator tokenGenerator;

    private PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public UserDto registerUser(UserDtoForRegistration user){
        User insertedUser = usersRepo.save(UsersConverter.toUserFromRegistrationDto(user));
        return UsersConverter.toUserDto(insertedUser);
    }

    public String login(String name, String password){
        User user = usersRepo.findByUserName(name);
        if (user == null){
            throw new UserNotFoundException("You specified a non-existent username!");
        }

        if (passwordEncoder.matches(password, user.getPassword())){
            String token = tokenGenerator.generateToken();
            tokensRepo.save(new Token(token, user));
            return token;
        }else throw new WrongPasswordException("You specified wrong password!");

    }

    public List<UserDto> getAll(){
        List<User> users = usersRepo.findAll();
        List<UserDto> userDtos = new ArrayList<>();
        for (User user : users) {
            userDtos.add(UsersConverter.toUserDto(user));
        }
        return userDtos;
    }

    public UserDto getUserById(Long id){
        User user = usersRepo.findOne(id);
        return UsersConverter.toUserDto(user);
    }

    public UserDto getUserByName(String name){
        User user = usersRepo.findByUserName(name);
        return UsersConverter.toUserDto(user);
    }

    public void updateUserById(Long id, UserDtoForRegistration user){
        String name = user.getName();
        String password = user.getPassword();
        User oldVersionUser = usersRepo.findOne(id);
        if (name == null || name.equals("")){
            name = oldVersionUser.getName();
        }
        if (password == null || password.equals("")){
            password = oldVersionUser.getPassword();
        }
        if (name.equals(oldVersionUser.getName()) && password.equals(oldVersionUser.getPassword())){
            return;
        }
        usersRepo.update(name, password, id);
    }

    public void removeUserById(Long id, String name, String password){
        User user = usersRepo.findByUserName(name);
        if (user == null){
            throw new UserNotFoundException("You specified a non-existent username!");
        }

        if (passwordEncoder.matches(password, user.getPassword())){
            if (id.equals(user.getId())) {
                usersRepo.delete(id);
            }else throw new IllegalArgumentException("You want to delete other person instead of yourself!");
        }else throw new WrongPasswordException("You specified wrong password!");
    }

}
