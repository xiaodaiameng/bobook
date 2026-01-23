package com.sp001;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.sp001.mapper")
public class Sp001Application {

    public static void main(String[] args) {

        SpringApplication.run(Sp001Application.class, args);
    }

}
