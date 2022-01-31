package com.learnner.jwtsecurityservice.service;

import com.learnner.jwtsecurityservice.entityse.User;
import com.learnner.jwtsecurityservice.repository.UserRepository;
import com.learnner.jwtsecurityservice.utility.CustomUserDetails;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;


@Service
@AllArgsConstructor
@Slf4j
public class UserService implements UserDetailsService {



    private UserRepository userRepository;

    @Override
    public CustomUserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("get user details from db");
        User user = userRepository.findByUsername(username);

        if(user==null){
            throw new UsernameNotFoundException("User Not Found");
        }
        //return new User("admin","password",new ArrayList<>());
        return new CustomUserDetails(user);
    }
}
