package wall.aop;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindingResult;
import wall.util.Res;
import wall.util.StateCode;

import java.util.Arrays;

@Component
@Aspect
public class WallAop {


//    @Autowired
//    private StringRedisTemplate redisTemplate;


    Logger logger = LoggerFactory.getLogger(WallAop.class);

    @Pointcut(value = "execution(* wall.controller.*.aopCheckLogin*(..))")
    public void checkLoginPointcut() {
    }


    @Pointcut(value = "execution(* wall.controller.*.ordinaryAop*(..))")
    public void ordinaryPointcut() {
    }

    @Around(value = "ordinaryPointcut()")
    public Res<?> aroundPointcut2(JoinPoint joinPoint) {
        long startTime = System.currentTimeMillis();
        MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
        logger.info("original param: " + Arrays.toString(joinPoint.getArgs()));
        Res<?> proceed = new Res<>(StateCode.OPERATION_FAILURE);
        logger.info(" invoke " + methodInvocationProceedingJoinPoint.getSignature().getName());
        try {
            proceed = (Res<?>) methodInvocationProceedingJoinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable){
            throwable.printStackTrace();
            logger.error(throwable.getMessage());
        }
        long endTime = System.currentTimeMillis();
        logger.info("finish ： " + (endTime - startTime) + " ms");
        return proceed;
    }


    @Around(value = "checkLoginPointcut()")
    public Res<?> aroundPointcut1(JoinPoint joinPoint) {
        MethodInvocationProceedingJoinPoint methodInvocationProceedingJoinPoint = (MethodInvocationProceedingJoinPoint) joinPoint;
        logger.info("original param: " + Arrays.toString(joinPoint.getArgs()));
        Res<?> proceed = new Res<>(StateCode.OPERATION_FAILURE);
        BindingResult bindingResult = null;
        String session_key = null;
        for (int i = 0; i < joinPoint.getArgs().length; i++) {
            if (i == 0 )
                session_key = (String) joinPoint.getArgs()[i];
            if (joinPoint.getArgs()[i] instanceof BindingResult)
                bindingResult = (BindingResult) joinPoint.getArgs()[i];
        }
        if ( session_key == null )
            return new Res<>(StateCode.NOTLOGGEDIN);
//        if (redisTemplate.opsForValue().get(session_key) == null)
//            return new Res<>(StateCode.TOKENEXPIRE);
        if (bindingResult != null && bindingResult.hasErrors())
            return new Res<>(StateCode.INVALIDPARAMETER);
        logger.info(" invoke " + methodInvocationProceedingJoinPoint.getSignature().getDeclaringTypeName() + " " + methodInvocationProceedingJoinPoint.getSignature().getName());
        long startTime = System.currentTimeMillis();
        try {
            proceed = (Res<?>) methodInvocationProceedingJoinPoint.proceed(joinPoint.getArgs());
        }catch (Throwable throwable){
            throwable.printStackTrace();
            logger.error(throwable.getMessage());
        }
        long endTime = System.currentTimeMillis();
        logger.info("finish ： " + (endTime - startTime) + " ms");
        return proceed;
    }
}
