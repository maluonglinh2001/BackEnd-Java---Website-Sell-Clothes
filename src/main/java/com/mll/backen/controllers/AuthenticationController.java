package com.mll.backen.controllers;


import com.mll.backen.DTO.Request.AuthenticationRequest;
import com.mll.backen.DTO.Request.RegisterRequest;
import com.mll.backen.DTO.Response.AuthenticationResponse;
import com.mll.backen.DTO.Response.ResponseObject;
import com.mll.backen.models.User.User;
import com.mll.backen.repository.UserRepository;
import com.mll.backen.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.ResolverStyle;
import java.util.Optional;
import java.util.TimeZone;

@RestController
@Validated
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService service;

    private final UserRepository userRepository;
    @PostMapping("/register")
    public ResponseEntity<Object> register(@RequestBody @Valid  RegisterRequest request) throws Exception {
        Optional<User> existingUser = userRepository.findByEmail(request.getEmail());

        try {
            LocalDate date = LocalDate.parse(request.getBirthday(),
                    DateTimeFormatter.ofPattern("uuuu-M-d")
                            .withResolverStyle(ResolverStyle.STRICT)
            );
        }catch (Exception ex)
        {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(
                    new ResponseObject("400",ex.getMessage(),"")
           );
        }
        if(request.getEmail()==null)
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("409","Email Address is Null","")
            );
        }
        else
        if(existingUser.isPresent())
        {
            return ResponseEntity.status(HttpStatus.CONFLICT).body(
                    new ResponseObject("409","Email Address is already exist taken","")
            );
        }
        else
        {
            System.out.println(request.getEmail());
            System.out.println(request.getBirthday());



            TimeZone timeZone = TimeZone.getDefault();
            System.out.println("Current Time Zone: " + timeZone.getID());
            //return ResponseEntity.ok(service.register(request));
            return new ResponseEntity<>(service.register(request),HttpStatus.CREATED);
        }

    }
    @PostMapping("/authenticate")
    public ResponseEntity<AuthenticationResponse> authenticate(@RequestBody AuthenticationRequest request)
    {
        return ResponseEntity.ok(service.authenticate(request));
    }
    @PostMapping("/refresh-token")
    public void refreshToken(
            HttpServletRequest request,
            HttpServletResponse response
    ) throws IOException{
        service.refreshToken(request,response);
    }
}
