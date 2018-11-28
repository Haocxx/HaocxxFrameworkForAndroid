package com.haocxx.framework.haocxxaspect.aspect;

import com.haocxx.framework.system.StopWatch;
import com.haocxx.framework.util.system.LogUtil;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * Created by Haocxx
 * on 2018/11/28
 */
@Aspect
public class RunDurationSignAspect {
    private static final String POINTCUT_METHOD =
            "execution(@com.haocxx.framework.haocxxaspect.annotation.RunDurationSign * *(..))";

    private static final String POINTCUT_CONSTRUCTOR =
            "execution(@com.haocxx.framework.haocxxaspect.annotation.RunDurationSign *.new(..))";

    @Pointcut(POINTCUT_METHOD)
    public void methodAnnotatedWithRunDurationSign() {}

    @Pointcut(POINTCUT_CONSTRUCTOR)
    public void constructorAnnotatedRunDurationSign() {}

    @Around("methodAnnotatedWithRunDurationSign() || constructorAnnotatedRunDurationSign()")
    public Object waveJoinPoint(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature methodSignature = (MethodSignature) joinPoint.getSignature();
        String className = methodSignature.getDeclaringType().getSimpleName();
        String methodName = methodSignature.getName();

        final StopWatch stopWatch = new StopWatch();
        stopWatch.start();
        Object result = joinPoint.proceed();
        stopWatch.stop();

        LogUtil.INSTANCE.d(className, buildLogMessage(methodName, stopWatch.getTotalTimeMillis()));

        return result;
    }

    /**
     * Create a log message.
     *
     * @param methodName A string with the method name.
     * @param methodDuration Duration of the method in milliseconds.
     * @return A string representing message.
     */
    private static String buildLogMessage(String methodName, long methodDuration) {
        return "RunDurationSignAspect --> " + methodName
                + " --> " + "[" + methodDuration + "ms" + "]";
    }
}
