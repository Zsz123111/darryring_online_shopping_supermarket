package com.zsz.darryring;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
@MapperScan("com.zsz.darryring.mapper")
public class DarryringApplication {

    public static void main(String[] args) {
        SpringApplication.run(DarryringApplication.class, args);
    }

}
