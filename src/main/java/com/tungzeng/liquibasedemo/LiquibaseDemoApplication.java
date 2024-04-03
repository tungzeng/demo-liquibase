package com.tungzeng.liquibasedemo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class LiquibaseDemoApplication {

    public static void main(String[] args) {
        //http://localhost:8080/swagger-ui/index.html?configUrl=/v3/api-docs/swagger-config#/ API doctument
        SpringApplication.run(LiquibaseDemoApplication.class, args);
    }

}
