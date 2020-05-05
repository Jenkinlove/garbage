package com.xiong.common.utils;

import com.google.common.base.Throwables;
import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;

@Aspect
@Slf4j
public class ServiceAop {

    @Around(value = "execution(* com.xiong.*.server.impl.*Impl.*(..))")
    public Object doApi(ProceedingJoinPoint joinPoint) {
        try {
            Object[] args = joinPoint.getArgs();

            if (args != null && args.length == 1 && args[0] instanceof AbstractRequest) {
                AbstractRequest request = (AbstractRequest) args[0];
                request.checkParam();
            }

            return joinPoint.proceed();
        } catch (ServiceException e) {
            return Response.fail(e.getMessage());
        } catch (Throwable e) {
            log.error("fail to invoke interface with: {}, cause: {}", joinPoint, Throwables.getStackTraceAsString(e));
            return Response.fail(e.getMessage());
        }
    }
}
