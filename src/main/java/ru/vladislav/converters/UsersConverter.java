package ru.vladislav.converters;

import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import ru.vladislav.dto.UserDto;
import ru.vladislav.dto.UserDtoForRegistration;
import ru.vladislav.models.User;

public class UsersConverter {

    private static PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    public static User toUserFromRegistrationDto(UserDtoForRegistration user){
        if (user == null){
            return null;
        }
        return new User(null, user.getName(), passwordEncoder.encode(user.getPassword()), null, null, null, null);
    }


    public static UserDto toUserDto(User user){
        if (user == null){
            return null;
        }
        return new UserDto(user.getId(), user.getName());
    }

}
