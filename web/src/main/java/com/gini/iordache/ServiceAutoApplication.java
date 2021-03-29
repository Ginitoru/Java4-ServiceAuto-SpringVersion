package com.gini.iordache;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;


@SpringBootApplication
@EntityScan(basePackages = {"com.gini.iordache.entity"})
@ComponentScan(basePackages = {"ro.gini.iordache.security.*", "com.gini.iordache.*","com.gini.iordache.aspect", "ro.gini.iordache.email.*"})
public class ServiceAutoApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceAutoApplication.class, args);






    }

}
