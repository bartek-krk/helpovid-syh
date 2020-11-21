package pl.ddcrew.helpovid.aspect.advice;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import pl.ddcrew.helpovid.exception.UnauthorizedException;
import pl.ddcrew.helpovid.security.TokenAuthDAO;
import pl.ddcrew.helpovid.service.SessionService;

import java.util.Map;

@Aspect
@Component
public class RestrictedAccessAdvice {
    @Autowired
    SessionService sessionService;

    @Around("@annotation(pl.ddcrew.helpovid.aspect.annotation.RestrictedAccess)")
    public Object authenticateToken(ProceedingJoinPoint pjp) throws Throwable{
        ObjectMapper om = new ObjectMapper();
        Map<String, Object> payload = (Map<String,Object>) pjp.getArgs()[0];
        TokenAuthDAO tokenAuthDAO = om.convertValue(payload.get("credentials"), TokenAuthDAO.class);
        if(!sessionService.tokenAndIdMatches(tokenAuthDAO) || !sessionService.isInTime(tokenAuthDAO)) throw new UnauthorizedException();
        else return pjp.proceed();
    }
}
