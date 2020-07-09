package com.will.play.aop.login.core;

import android.content.Context;

/**
 * Desc:
 * <p>
 *
 * @Author: will
 */
public class LoginAssistant {
    private LoginAssistant() {
    }

    private static LoginAssistant instance;

    public static LoginAssistant getInstance() {
        LoginAssistant tmp = instance;
        if (tmp == null) {
            synchronized (LoginAssistant.class) {
                tmp = instance;
                if (tmp == null) {
                    instance = tmp = new LoginAssistant();
                }
            }
        }
        return tmp;
    }

    private ILogin iLogin;
    private ILoginStatus iLoginStatus;

    public ILogin getiLogin() {
        return iLogin;
    }

    public void setiLogin(ILogin iLogin) {
        this.iLogin = iLogin;
    }

    private Context applicationContext;

    public Context getApplicationContext() {
        return applicationContext;
    }

    public void setApplicationContext(Context applicationContext) {
        this.applicationContext = applicationContext;
    }

    /**
     * Author: will
     *
     * @param userDefine 状态码
     */
    public void serverTokenInvalidation(int userDefine) {
        if (iLogin == null) {
            return;
        }
        iLogin.clearLoginStatus(applicationContext);
        iLogin.login(applicationContext, userDefine);
    }

    /**
     * <p>
     * Author: will
     */
    public void setLoginSuccessStatus() {
        if (iLoginStatus != null) {
            iLoginStatus.loginSuccess();
        }
    }


    /**
     */
    public void removeLoginStatus() {
        iLoginStatus = null;
    }

    /**
     * Author: will
     */
    public void setLoginFailedStatus() {
        if (iLoginStatus != null) {
            iLoginStatus.loginFailed();
        }
    }

    /**
     * Author: will
     *
     */
    public void setiLoginStatus(ILoginStatus iLoginStatus) {
        this.iLoginStatus = iLoginStatus;
    }

}
