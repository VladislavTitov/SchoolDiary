package ru.vladislav.security.provider;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import ru.vladislav.security.auth.TokenAuthentication;

public class TokenAuthenticationProvider implements AuthenticationProvider {

    @Autowired
    private UserDetailsService userDetailsService;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        // преобразуем аутентификацию к токен-аутентификации
        TokenAuthentication tokenAuthentication = (TokenAuthentication)authentication;
        // получили токен
        String token = (String)tokenAuthentication.getPrincipal();
        // загрузили данные по безопасности для пользователя
        UserDetails userDetails = userDetailsService.loadUserByUsername(token);
        if (userDetails == null) {
            throw new IllegalArgumentException("User not found");
        }
        // привязали к этой аутентификации данные пользователя
        tokenAuthentication.setDetails(userDetails);
        // авторизуем
        SecurityContextHolder.getContext().setAuthentication(authentication);
        return authentication;
    }

    @Override
    public boolean supports(Class<?> aClass) {
        return aClass.equals(TokenAuthentication.class);
    }
}
