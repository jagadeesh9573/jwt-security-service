package com.learnner.jwtsecurityservice.controller;

import com.learnner.jwtsecurityservice.model.JWTRequest;
import com.learnner.jwtsecurityservice.model.JWTResponse;
import com.learnner.jwtsecurityservice.service.UserService;
import com.learnner.jwtsecurityservice.utility.JWTUtility;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping
@Slf4j
public class JwtController {


    private UserService userService;
    private JWTUtility jwtUtility;
    private AuthenticationManager authenticationManager;


    @GetMapping("/")
    public String getJwtSecurity(){
        log.info("welcome message from JWT Security");
        return "Welcome to Jwt security";
    }

    @PostMapping("/authenticate")
    public JWTResponse authenticate(@RequestBody JWTRequest jwtRequest) throws Exception {
        log.info("inside authenticating the user");
        try {
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            jwtRequest.getUserName(),
                            jwtRequest.getPassword()
                )
        );
        }catch (BadCredentialsException exception){
            throw new Exception("INVALID_CREDENTIALS" ,exception);
        }

        UserDetails userDetails = userService.loadUserByUsername(jwtRequest.getUserName());
        final String token = jwtUtility.generateToken(userDetails);
        return new JWTResponse(token);
    }
}
