package com.will.router;

import android.os.Bundle;

/**
 * Desc:路由跳转拦截
 * <p>
 * @Author: will
 */
public interface RouterInterceptor {
    boolean onInterceptor(String url, Bundle extras);
}
