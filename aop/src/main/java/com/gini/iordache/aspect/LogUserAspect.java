package com.gini.iordache.aspect;



import com.gini.iordache.entity.user.User;
import com.gini.iordache.util.Logs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;

import java.net.UnknownHostException;
import java.util.logging.Logger;


@Aspect
@Component
public class LogUserAspect {

        private Logger logger = Logger.getLogger(LogUserAspect.class.getName());

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
        Logs.sendEmail(user, "sent email");
    }



    @AfterThrowing( pointcut = "execution(* ro.gini.iordache.email.sender.EmailService.sendEmail(..))",
                   throwing = "exc")
    public void logSendMail(JoinPoint JoinPoint, Throwable exc){

        String message = exc.getMessage();
        Logs.writeException(message);



    }





}
