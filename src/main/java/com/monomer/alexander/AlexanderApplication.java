package com.monomer.alexander;

import com.spring4all.swagger.EnableSwagger2Doc;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableSwagger2Doc
@Slf4j
public class AlexanderApplication {

    public static void main(String[] args) {
        SpringApplication.run(AlexanderApplication.class, args);
        log.info("http://localhost:8080/swagger-ui.html");
    }

}
