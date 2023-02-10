package com.tpe.security.service;

import com.tpe.domain.*;
import com.tpe.domain.User;
import com.tpe.exception.*;
import com.tpe.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.security.core.authority.*;
import org.springframework.security.core.userdetails.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class UserDetailServiceImpl implements UserDetailsService {
    // !!! bu classda amacım : UserDetails(User) ve GrantedAuthorities(Role)

    @Autowired
    private UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
           User user = userRepository.findByUserName(username).orElseThrow(// domain olan user
                   ()-> new ResourceNotFoundException("user not found username : " + username));

           if(user != null) {// securuty de cok defa yapiyoruz bu kontrolu
               return new org.//bu user securuty'in anldigi user
                       springframework.
                       security.
                       core.
                       userdetails.
                       User(user.getUserName(),// ismini aldim
                            user.getPassword(),// sifreyi aldim
                               buildGrantedAuthorities(user.getRoles())// rollerini grantedauthorizes'e göre alma lazim
               );
           } else {
               throw new UsernameNotFoundException("user not found username : " + username);
           }

    }

    private static List<SimpleGrantedAuthority> buildGrantedAuthorities(final Set<Role> roles) {//final yapiyorum cagrilmadan once buna set etmek icin yaptim
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        for (Role role :roles) {
            authorities.add( new SimpleGrantedAuthority(role.getName().name()));
        }

        return authorities;
    }
}
