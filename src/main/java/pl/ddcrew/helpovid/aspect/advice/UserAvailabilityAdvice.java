package pl.ddcrew.helpovid.aspect.advice;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ddcrew.helpovid.controller.register.RegistrationDataDTO;
import pl.ddcrew.helpovid.exception.UnsupportedMediaTypeException;
import pl.ddcrew.helpovid.exception.UserAlreadyExistsException;
import pl.ddcrew.helpovid.model.User;
import pl.ddcrew.helpovid.service.UserService;

@Aspect
@Component
public class UserAvailabilityAdvice {
    @Autowired
    UserService userService;

    @Around("@annotation(pl.ddcrew.helpovid.aspect.annotation.CheckUserAvailability)")
    public void checkUserAvailability(ProceedingJoinPoint pjp) throws Throwable{
        User user = (User) pjp.getArgs()[0];
        boolean isValidUser = user.geteMail()!=null &&
                user.getPhoneNumber()!=null &&
                user.getName()!=null &&
                user.getPassword()!=null &&
                user.getLocation()!=null;
        if(!isValidUser) throw new UnsupportedMediaTypeException();
        if(userService.checkUserAvailability(user)) pjp.proceed();
        else throw new UserAlreadyExistsException();
    }
}
