package com.gini.iordache.aspect;



import com.gini.iordache.entity.user.User;
import com.gini.iordache.util.Logs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Aspect
@Component
public class LogUserAspect {



    @AfterReturning(
            pointcut = ("com.gini.iordache.poitcut.PointcutExpression.userLoginWithEmail() || com.gini.iordache.poitcut.PointcutExpression.userLoginWithUsername()"),
            returning = "auth"
    )
    public void logTheLogins(JoinPoint joinPoint, Authentication auth){
        Logs.loginLogoutUser(auth, "logged at");

    }



    @AfterReturning(
            pointcut = ("com.gini.iordache.poitcut.PointcutExpression.createUser()"),
            returning = "user"
    )
    public void logCreateUser(JoinPoint joinPoint, User user){
        Logs.createUser(user, "created at");

    }



    @After("execution(* ro.gini.iordache.email.sender.EmailService.sendEmail(..))")
    public void logSendMail(JoinPoint joinPoint){

            Object[] obj = joinPoint.getArgs();
            User user = (User)obj[0];
            Logs.sendEmail(user, "sent email");



    }





}
