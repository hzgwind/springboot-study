package com.windrain.windrainmain;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class WindrainMainApplication {

    public static void main(String[] args) {
        SpringApplication.run(WindrainMainApplication.class, args);
    }

}
