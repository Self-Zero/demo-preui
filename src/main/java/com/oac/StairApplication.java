package com.oac;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication(exclude = {DataSourceAutoConfiguration.class})
@MapperScan("com.oac.project.*.*.mapper")
@EnableTransactionManagement
public class StairApplication {

    public static void main(String[] args) {
        SpringApplication.run(StairApplication.class, args);
    }

}
