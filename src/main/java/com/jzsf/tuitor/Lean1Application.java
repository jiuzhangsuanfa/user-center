package com.jzsf.tuitor;

import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@EnableEncryptableProperties
public class Lean1Application {

    public static void main(String[] args) {
        SpringApplication.run(Lean1Application.class, args);
    }

}

