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

    @Pointcut("execution(* ro.gini.iordache.security.provider.*.*(..))")
    private void securityModule(){

    }

    @Pointcut("execution(* com.gini.iordache.*.*.*.*.*(..))")
    private void persistenceModule(){

    }

    @Pointcut("execution(* com.gini.iordache.service.*.*(..))")
    private void invoiceModule(){

    }

    @Pointcut("securityModule() || persistenceModule() || invoiceModule()")
    public void modulesExceptions(){

    }








}
