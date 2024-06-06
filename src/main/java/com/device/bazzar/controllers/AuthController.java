package com.device.bazzar.controllers;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
@RequestMapping("/auth")
@Tag(name = "Auth Controller", description = "REST APIs for Auth operations")
public class AuthController {

    @GetMapping
    @Operation(summary = "display logged in User role")

    ResponseEntity<String> getLogeedInUser(Principal principal){
        String str = principal.getName();
        return new ResponseEntity<>(str, HttpStatus.OK);
    }
}
