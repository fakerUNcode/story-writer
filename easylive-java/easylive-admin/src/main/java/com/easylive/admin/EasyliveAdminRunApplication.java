package com.easylive.admin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

@SpringBootApplication(scanBasePackages = {"com.easylive"},exclude = {DataSourceAutoConfiguration.class})
public class EasyliveAdminRunApplication {
    public static void main(String[] args) {
        SpringApplication.run(EasyliveAdminRunApplication.class,args);
    }
}
