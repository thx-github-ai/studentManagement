package com.demo.qcby;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.demo.qcby.mapper")
public class QcbyApplication {

    public static void main(String[] args) {
        SpringApplication.run(QcbyApplication.class, args);
    }

}
