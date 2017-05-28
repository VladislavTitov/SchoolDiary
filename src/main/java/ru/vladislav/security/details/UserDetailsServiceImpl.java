package ru.vladislav.security.details;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import ru.vladislav.models.Token;
import ru.vladislav.models.User;
import ru.vladislav.repo.TokensRepo;

import java.util.ArrayList;
import java.util.List;

public class UserDetailsServiceImpl implements UserDetailsService {

    //TODO replace with service
    @Qualifier("tokensRepo")
    @Autowired
    private TokensRepo tokensRepo;

    @Override
    public UserDetails loadUserByUsername(String token) throws UsernameNotFoundException {
        Token tokenObj = tokensRepo.findTokenByToken(token);
        if (tokenObj != null){
            User user = tokenObj.getUser();
            List<GrantedAuthority> authorities = createGrantedAuthorities();
            return new UserDetailsImpl(user.getName(), user.getPassword(), authorities);
        }
        return null;
    }

    public static List<GrantedAuthority> createGrantedAuthorities() {
        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("USER"));
        return authorities;
    }

}
