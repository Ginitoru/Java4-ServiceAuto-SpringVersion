package com.gini.iordache.aspect;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LogoutAspect {



    @Before("com.gini.iordache.poitcut.PointcutExpression.controllersPackage() && " +
                         "!com.gini.iordache.poitcut.PointcutExpression.userPackage()")
    public void logActionTime(){
        System.out.println("Loggggggggggggg time of action");
    }

    @Before("com.gini.iordache.poitcut.PointcutExpression.userLoginProcessing()")
    public void startLogoutTimer(){
        System.out.println("Staaaaaaaaaart logout Timeeeeeeeeeeeeeer");
    }


}
