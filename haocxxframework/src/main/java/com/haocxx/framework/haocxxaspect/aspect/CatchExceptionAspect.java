package com.haocxx.framework.haocxxaspect.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

/**
 * Created by Haocxx
 * on 2019/2/19
 */
@Aspect
public class CatchExceptionAspect {
    private static final String TAG = "CatchExceptionAspect";

    private static final String POINTCUT_METHOD =
            "execution(@com.haocxx.framework.haocxxaspect.annotation.CatchException * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.haocxx.framework.haocxxaspect.annotation.CatchException *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithCatchException() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedCatchException() {}

    @Around("methodAnnotatedWithRunDurationSign() || constructorAnnotatedRunDurationSign()")
    public Object weaveJoinPoint(ProceedingJoinPoint joinPoint) {
        Object result = null;
        try {
            result = joinPoint.proceed();
        } catch (Throwable e) {
            e.printStackTrace();
        }
        return result;
    }
}
