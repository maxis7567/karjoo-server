package com.hinext.maxis7567.karjoo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.web.bind.annotation.RequestMapping;

@SpringBootApplication
public class KarjooApplication {

    public static void main(String[] args) {
        SpringApplication.run(KarjooApplication.class, args);
    }
    @SpringBootApplication
    public class SpringBootTomcatApplication extends SpringBootServletInitializer {
    }
}
