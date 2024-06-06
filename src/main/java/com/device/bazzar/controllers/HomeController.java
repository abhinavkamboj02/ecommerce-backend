package com.device.bazzar.controllers;

import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.net.MalformedURLException;
import java.net.URL;

@RestController
@RequestMapping("/")
public class HomeController {

    @GetMapping
    ResponseEntity<String> home() throws MalformedURLException {
        URL home = new URL("https://ecommerce-backend-production-3e08.up.railway.app/swagger-ui/index.html#/");
        String str = " Link to documentation for this project ->  " + home;
        return new ResponseEntity<>(str, HttpStatus.OK);
    }

}
