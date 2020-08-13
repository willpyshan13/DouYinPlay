package com.will.play.aop.login.core;

import android.content.Context;

import com.will.play.aop.login.annotation.LoginCallBackFilter;
import com.will.play.aop.login.annotation.LoginFailedFilter;
import com.will.play.aop.login.annotation.LoginFilter;
import com.will.play.aop.login.execption.AnnotationException;
import com.will.play.aop.login.execption.NoInitException;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * Desc:登录过滤切面
 *
 * @Author: will
 */
@Aspect
public class LoginFilterAspect {

    @Pointcut("execution(@com.will.play.aop.login.annotation.LoginFilter * *(..))")
    public void loginFilter() {
        //Do nothing
    }

    @Around("loginFilter()")
    public void aroundLoginPoint(final ProceedingJoinPoint joinPoint) throws Throwable {
        final Object object = joinPoint.getThis();
        if (object == null) {
            return;
        }
        final ILogin iLogin = LoginAssistant.getInstance().getiLogin();
        if (iLogin == null) {
            throw new NoInitException("LoginSdk 没有初始化！");
        }

        Signature signature = joinPoint.getSignature();
        if (!(signature instanceof MethodSignature)) {
            throw new AnnotationException("LoginFilter 注解只能用于方法上");
        }
        MethodSignature methodSignature = (MethodSignature) signature;
        LoginFilter loginFilter = methodSignature.getMethod().getAnnotation(LoginFilter.class);
        if (loginFilter == null) {
            return;
        }

        final Context param = LoginAssistant.getInstance().getApplicationContext();
        initLoginStatus(joinPoint, object, iLogin, loginFilter, param);
        if (iLogin.isLogin(param)) {
            try {
                joinPoint.proceed();
            } catch (Exception ex) {
            }
        } else {
            iLogin.login(param, loginFilter.userDefine());
            if (!loginFilter.needLoginResult()) {
                noLogin(object);
            }
        }
    }

    /**
     * Desc:构建aop切面回调到Activity进行业务刷新
     * <p>
     * Author: will
     * Date: 2019-12-12
     */
    private void initLoginStatus(final ProceedingJoinPoint joinPoint, final Object object, final ILogin iLogin, final LoginFilter loginFilter, final Context param) {
        if (loginFilter.needLoginResult()) {
            LoginAssistant.getInstance().setiLoginStatus(new ILoginStatus() {
                @Override
                public void loginSuccess() {
                    if (iLogin.isLogin(param)) {
                        try {
                            if (loginFilter.needLoginCallBack()) {
                                loginCallBack(object);
                            } else {
                                joinPoint.proceed();
                            }
                        } catch (Throwable throwable) {
                        }
                    }
                    //结束后将callback移除
                    LoginAssistant.getInstance().removeLoginStatus();
                }

                @Override
                public void loginFailed() {
                    noLogin(object);
                    //结束后将callback移除
                    LoginAssistant.getInstance().removeLoginStatus();
                }
            });
        }
    }


    /**
     * Desc:注解返回到新的方法体
     * <p>
     * Author: will
     * Date: 2020-01-09
     *
     */
    public void loginCallBack(Object object) {
        Class<?> cls = object.getClass();
        Method[] methods = cls.getDeclaredMethods();
        if (methods.length == 0) {
            return;
        }
        for (Method method : methods) {
            //过滤不含自定义注解PermissionDenied的方法
            boolean isHasAnnotation = method.isAnnotationPresent(LoginCallBackFilter.class);
            if (isHasAnnotation) {
                method.setAccessible(true);
                //获取方法上的注解
                LoginCallBackFilter aInfo = method.getAnnotation(LoginCallBackFilter.class);
                if (aInfo == null) {
                    return;
                }

                try {
                    method.invoke(object);
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                } catch (InvocationTargetException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    public void noLogin(Object object) {
        Class<?> cls = object.getClass();
        Method[] methods = cls.getDeclaredMethods();
        if (methods.length == 0) {
            return;
        }
        for (Method method : methods) {
            //过滤不含自定义注解PermissionDenied的方法
            boolean isHasAnnotation = method.isAnnotationPresent(LoginFailedFilter.class);
            if (isHasAnnotation) {
                method.setAccessible(true);
                //获取方法上的注解
                LoginFailedFilter aInfo = method.getAnnotation(LoginFailedFilter.class);
                if (aInfo == null) {
                    return;
                }

                try {
                    method.invoke(object);
                } catch (Exception e) {
                }
            }
        }
    }

}
