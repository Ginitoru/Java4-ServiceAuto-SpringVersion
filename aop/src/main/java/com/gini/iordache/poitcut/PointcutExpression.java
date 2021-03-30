package com.gini.iordache.poitcut;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;



@Aspect
public class PointcutExpression {



    @Pointcut("execution(* com.gini.iordache.controllers.user.UserController.loginProcessing())")
    public void userLoginProcessing(){

    }

    @Pointcut("execution(* com.gini.iordache.controllers.user.*.*(..))")
    public void userPackage(){
    }

    @Pointcut("execution(* com.gini.iordache.controllers.*.*.*(..))")
    public void controllersPackage(){

    }

    @Pointcut("execution(* ro.gini.iordache.security.provider.UserNamePasswordProvider.authenticate(..))")
    public void userLoginWithUsername(){

    }

    @Pointcut("execution(* ro.gini.iordache.security.provider.EmailProvider.authenticate(..))")
    public void userLoginWithEmail(){

    }






}
