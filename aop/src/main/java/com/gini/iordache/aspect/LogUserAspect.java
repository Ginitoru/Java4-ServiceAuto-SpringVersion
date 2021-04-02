package com.gini.iordache.aspect;



import com.gini.iordache.entity.user.User;
import com.gini.iordache.util.Logs;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.*;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;





@Aspect
@Component
public class LogUserAspect {



    @AfterReturning(
            pointcut = ("com.gini.iordache.poitcut.PointcutExpression.userLoginWithEmail() || com.gini.iordache.poitcut.PointcutExpression.userLoginWithUsername()"),
            returning = "auth")
    public void logTheLogins(JoinPoint joinPoint, Authentication auth){
        Logs.loginLogoutUser(auth, "logged at");
    }



    @AfterReturning(
            pointcut = ("com.gini.iordache.poitcut.PointcutExpression.createUser()"),
            returning = "user")
    public void logCreateUser(JoinPoint joinPoint, User user){
        Logs.createUser(user, "created at");
        Logs.sendEmail(user, "sent email at:");
    }



    @AfterThrowing( pointcut = "execution(* ro.gini.iordache.email.sender.EmailService.sendEmail(..))",
                   throwing = "exc")
    public void logSendMail(JoinPoint JoinPoint, Throwable exc){
        String message = exc.getMessage();
        Logs.writeEmailException(message);
    }


    @Before("execution(* ro.gini.iordache.security.handler.SecurityLogoutHandler.logout(..))")
    public void logLogout(JoinPoint joinPoint){

        Object[] obj = joinPoint.getArgs();
        Authentication auth = (Authentication) obj[2];
        Logs.loginLogoutUser(auth, "logged out at:");
    }


    @AfterThrowing(value = "com.gini.iordache.poitcut.PointcutExpression.modulesExceptions()",
                    throwing = "exc")
    public void logAllExceptions(JoinPoint joinPoint, Throwable exc){

        var methodSignature = joinPoint.getSignature().toString();
        var message = exc.getMessage();
        var message2 = methodSignature + " " + message;

        Logs.writeExceptions(message2);


    }





}
