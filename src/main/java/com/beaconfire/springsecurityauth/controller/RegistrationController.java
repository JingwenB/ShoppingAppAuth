package com.beaconfire.springsecurityauth.controller;

import com.beaconfire.springsecurityauth.domain.entity.User;
import com.beaconfire.springsecurityauth.domain.request.RegisterRequest;
import com.beaconfire.springsecurityauth.domain.response.ResponseHandler;
import com.beaconfire.springsecurityauth.exception.RegisterInfoMissingException;
import com.beaconfire.springsecurityauth.service.RegisterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RegistrationController {

    private final RegisterService registerService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public RegistrationController(RegisterService registerService,
                                  PasswordEncoder passwordEncoder) {
        this.registerService = registerService;
        this.passwordEncoder = passwordEncoder;
    }


    //User trying to log in with username and password
    @PostMapping("auth/register")
    public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
        if (request.getPassword() == null){
            throw new RegisterInfoMissingException("Missing registration required password");
        }
        if (request.getEmail() == null){
            throw new RegisterInfoMissingException("Missing registration required email");
        }
        if (request.getUsername() == null){
            throw new RegisterInfoMissingException("Missing registration required password");
        }
        User newUser = User.builder()
                .username(request.getUsername())
                .password(passwordEncoder.encode(request.getPassword()))
//                .password(request.getPassword())
                .email(request.getEmail())
                .isAdmin(false).build();

        registerService.saveUser(newUser);

        return ResponseHandler.generateResponse(
                "register succeed",
                HttpStatus.OK,
                newUser);



    }

}
