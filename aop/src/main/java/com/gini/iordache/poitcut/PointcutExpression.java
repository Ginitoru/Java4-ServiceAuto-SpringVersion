package com.gini.iordache.poitcut;


import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;



@Aspect
public class PointcutExpression {



    @Pointcut("execution(* ro.gini.iordache.security.provider.UserNamePasswordProvider.authenticate(..))")
    public void userLoginWithUsername(){

    }

    @Pointcut("execution(* ro.gini.iordache.security.provider.EmailProvider.authenticate(..))")
    public void userLoginWithEmail(){

    }

    @Pointcut("execution(* com.gini.iordache.services.impl.user.UserServiceImpl.createUser(..))")
    public void createUser(){

    }








}
