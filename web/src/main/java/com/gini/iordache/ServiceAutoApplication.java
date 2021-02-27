package com.gini.iordache;



import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;

@SpringBootApplication
@EntityScan(basePackages = {"com/gini/iordache/entity"})
public class ServiceAutoApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceAutoApplication.class, args);

    }

}
