package com.will.play.aop.login.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;


/**
 * Desc:登录状态过滤
 * <p>
 * Updater:
 * Update Time:
 * Update Comments:
 *@Author: will
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
public @interface LoginFilter {
    /**
     *
     * @return 定义登录状态码 0 默认跳转登录页
     */
    int userDefine() default 0;

    /**
     *
     * @return 是否需要登录成功后，返回注解方法里面
     */
    boolean needLoginResult() default false;

    /**
     * Desc:
     * <p>
     * @Author: will
     * Date: 2020-01-09
     *
     * @return boolean 是否登录成功后，返回到新的方法里面
     */
    boolean needLoginCallBack() default false;
}
