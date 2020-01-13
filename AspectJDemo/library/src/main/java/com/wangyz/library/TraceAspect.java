package com.wangyz.library;

import android.util.Log;
import android.view.View;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

/**
 * @author wangyz
 * @time 2020/1/13 10:17
 * @description TraceAspect
 */
@Aspect
public class TraceAspect {

    private static final String TAG = TraceAspect.class.getSimpleName();

    @Pointcut("execution(@com.wangyz.library.AddLog * *(..))")
    public void addLogPointcut() {

    }

    @Pointcut("execution(@com.wangyz.library.ExecTime * *(..))")
    public void execTimePointcut() {

    }

    @Pointcut("execution(* onClick(..))")
    public void onClickPointcut() {

    }

    @Pointcut("execution(* android.app.Activity+.onCreate(..))")
    public void activityOnCreatePointcut() {

    }

    @Pointcut("execution(* android.app.Activity+.onDestroy(..))")
    public void activityDestroyPointcut() {

    }

    @Around("onClickPointcut()")
    public void onClick(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = "";
        if (target != null) {
            className = target.getClass().getName();
        }
        Object[] args = joinPoint.getArgs();
        if (args.length > 0 && args[0] instanceof View) {
            View view = (View) args[0];
            String entryName = view.getResources().getResourceEntryName(view.getId());
            TrackPoint.onClick(className, entryName);
        }
        joinPoint.proceed();
    }

    @Around("activityOnCreatePointcut()")
    public void pageOpen(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        TrackPoint.onPageOpen(className);
        joinPoint.proceed();
    }

    @Around("activityDestroyPointcut()")
    public void pageClose(ProceedingJoinPoint joinPoint) throws Throwable {
        Object target = joinPoint.getTarget();
        String className = target.getClass().getName();
        TrackPoint.onPageClose(className);
        joinPoint.proceed();
    }

    @Around("addLogPointcut()")
    public void addLog(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        AddLog addLog = signature.getMethod().getAnnotation(AddLog.class);
        if (addLog != null) {
            Object target = joinPoint.getTarget();
            String className = "";
            if (target != null) {
                className = target.getClass().getName();
            }
            Log.d(TAG, "start execute:" + className + "-" + signature.getMethod().getName());
            joinPoint.proceed();
            Log.d(TAG, "end execute:" + className + "-" + signature.getMethod().getName());
        } else {
            joinPoint.proceed();
        }
    }

    @Around("execTimePointcut()")
    public void execTime(ProceedingJoinPoint joinPoint) throws Throwable {
        MethodSignature signature = (MethodSignature) joinPoint.getSignature();
        ExecTime execTime = signature.getMethod().getAnnotation(ExecTime.class);
        if (execTime != null) {
            long start = System.currentTimeMillis();
            joinPoint.proceed();
            long end = System.currentTimeMillis();
            Object target = joinPoint.getTarget();
            String className = "";
            if (target != null) {
                className = target.getClass().getName();
            }
            Log.d(TAG,
                    "execute time:" + className + "-" + signature.getMethod().getName() + " : " + (end - start) + "ms");
        } else {
            joinPoint.proceed();
        }
    }

}
