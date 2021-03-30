package com.gini.iordache.aspect;



import com.gini.iordache.util.Logs;
import lombok.AllArgsConstructor;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Component;


@Aspect
@AllArgsConstructor
@Component
public class LogUserAspect {






//    @Before("com.gini.iordache.poitcut.PointcutExpression.controllersPackage() && " +
//                         "!com.gini.iordache.poitcut.PointcutExpression.userPackage()")
//    public void logActionTime(){
//
//
//    }


    @AfterReturning(
            pointcut = ("com.gini.iordache.poitcut.PointcutExpression.userLoginWithEmail() || com.gini.iordache.poitcut.PointcutExpression.userLoginWithUsername()"),
            returning = "auth"
    )
    public void logTheLogins(JoinPoint joinPoint, Authentication auth){
        Logs.loginLogoutUser(auth);

    }





}
