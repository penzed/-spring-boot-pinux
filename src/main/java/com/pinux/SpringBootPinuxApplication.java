package com.pinux;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.scheduling.annotation.EnableScheduling;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

@SpringBootApplication
@MapperScan("com.pinux.mapper")
@EnableSwagger2
@EnableScheduling
@EnableAsync
public class SpringBootPinuxApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootPinuxApplication.class, args);
    }

}
