package com.gini.iordache;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.scheduling.annotation.EnableAsync;


@SpringBootApplication
@EnableAsync
@EnableAspectJAutoProxy
@EntityScan(basePackages = {"com.gini.iordache.entity"})
@ComponentScan(basePackages = {"ro.gini.iordache.security.*", "com.gini.iordache.*","com.gini.iordache.aspect", "ro.gini.iordache.email.*"})
public class ServiceAutoApplication {
    public static void main(String[] args) {

        SpringApplication.run(ServiceAutoApplication.class, args);






    }

}
