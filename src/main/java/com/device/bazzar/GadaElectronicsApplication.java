package com.device.bazzar;

import com.device.bazzar.services.IMPL.UserServiceImpl;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@SpringBootApplication
@EnableWebMvc
public class GadaElectronicsApplication {

	public static void main(String[] args) {

		SpringApplication.run(GadaElectronicsApplication.class, args);
	}

}
