package com.device.bazzar.controllers;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
public class AuthController {

    @GetMapping
    ResponseEntity<String> getLogeedInUser(Principal principal){
        String str = principal.getName();
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
