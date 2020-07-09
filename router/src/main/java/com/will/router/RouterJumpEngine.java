package com.will.router;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.Toast;

import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import android.app.Activity;

/**
 * Desc:路由跳转逻辑
 * <p>
 * @Author: will
 */
class RouterJumpEngine {


    private Map<String, RouterInterceptor> interceptors;

    private final static String param = "param";

    private static HashMap<String, ClassFactory> activityClassHashMap = new HashMap<>();

    private boolean enableToast = false;


    public void setEnableToast(boolean enableToast){
        this.enableToast = enableToast;
    }

    /**
     * 添加拦截器
     *
     * @param interceptor
     */
    void add(String url, RouterInterceptor interceptor) {
        if (interceptors == null) {
            interceptors = new HashMap<>();
        }
        if (interceptor != null) {
            interceptors.put(url, interceptor);
        }
    }


    private RouterJumpEngine() {
    }

    static RouterJumpEngine getInstance() {
        return SingletonHolder.INSTANCE;
    }

    void goActivitys(Context context, Router.RouterBuilder[] builders){
        if (builders != null && builders.length > 0){
            ArrayList<Intent> intents = new ArrayList<>();
            for (int i = 0; i < builders.length; i++) {
                Intent intent= getIntent(context, builders[i]);
                if (intent != null){
                    intents.add(intent);
                }
            }
            if (intents.size() > 0){
                context.startActivities(intents.toArray(new Intent[intents.size()]));
            }
        }
    }


    void go(Context context, Router.RouterBuilder builder) {
        if (context == null) {
            return;
        }
        Intent intent = getIntent(context, builder);
        if (intent != null) {
            context.startActivity(intent);
            overWriteAnim(context, builder);
        }
    }

    void goForResult(Activity context, Router.RouterBuilder builder, int requestCode) {
        Intent intent = getIntent(context, builder);
        if (intent != null) {
            context.startActivityForResult(intent, requestCode);
            overWriteAnim(context, builder);
        }
    }

    void goForResult(Fragment context, Router.RouterBuilder builder, int requestCode) {
        Intent intent = getIntent(context.getActivity(), builder);
        if (intent != null) {
            context.startActivityForResult(intent, requestCode);
        }
    }

    void overWriteAnim(Context context, Router.RouterBuilder builder){
        if (context instanceof Activity){
            ((Activity)context).overridePendingTransition(builder.enterAnim, builder.outerAnim);
        }
    }


    Intent getIntent(Context context, Router.RouterBuilder builder) {
        Class clazz = findClass(context, builder.url, builder);
        if (clazz != null) {
            Intent intent = new Intent(context, clazz);
            if (builder.bundle != null) {
                intent.putExtras(builder.bundle);
            }
            if (!(context instanceof Activity)){
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            }

            if (context instanceof Activity && builder.outerAnim != -1 && builder.enterAnim != -1){
                ((Activity)context).overridePendingTransition(builder.enterAnim, builder.outerAnim);
            }
            if (builder.intentExtra != null && builder.intentExtra.size() > 0) {
                for (int i = 0; i < builder.intentExtra.size(); i++) {
                    intent.addFlags(builder.intentExtra.get(i));
                }
            }
            return intent;
        }
        return null;
    }


    private Class findClass(Context context, String url, Router.RouterBuilder builder) {
        if (context == null){
            return null;
        }
        Uri uri = Uri.parse(url);
        String group = uri.getHost();
        String path = uri.getPath();
        String name = WRouterGenerate.getFullGenerateClassName(TextUtils.isEmpty(group) ? path : group);
        try {
            ClassFactory classFactory = activityClassHashMap.get(name);
            if (classFactory == null) {
                classFactory = (ClassFactory) Class.forName(name).newInstance();
                activityClassHashMap.put(name, classFactory);
            }
            if (classFactory != null) {
                String tempUrl = (TextUtils.isEmpty(group) ? path : group + path);
                Class clazz = classFactory.getTargetClass(tempUrl);
                if (clazz != null) {
                    String p = uri.getQueryParameter(param);
                    parseParam(p, builder.getBundle());

                    boolean shouldInterceptor = false;
                    if (builder.allowInterceptor && interceptors != null) {
                        RouterInterceptor interceptor = interceptors.get(tempUrl);
                        if (interceptor != null) {
                            shouldInterceptor = interceptor.onInterceptor(tempUrl, builder.getBundle());
                        }
                    }
                    if (!shouldInterceptor) {
                        return clazz;
                    }
                } else {
                    showError(context, url);
                }
            } else {
                showError(context, url);
            }
        } catch (ClassNotFoundException e) {
            showError(context, url);
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        }
        return null;
    }


    private void showError(Context context, String url){
        if (enableToast){
            Toast.makeText(context, url + "\nnot found!!!!!\n请检查路径是否写错？", Toast.LENGTH_LONG).show();
            Log.e("WRouter:", "url '" + url + "' not found!!!!!! 请检查路径是否写错？");
        }
    }


    private void parseParam(String json, Bundle bundle) {
        try {
            if (json == null || "".equals(json)){
                return;
            }
            JSONObject jsonObject = new JSONObject(json);
            Iterator keys = jsonObject.keys();
            while (keys.hasNext()) {
                String key = (String) keys.next();
                Object value = jsonObject.opt(key);
                if (value != null) {
                    if (value instanceof String) {
                        bundle.putString(key, (String) value);
                    } else if (value instanceof Integer) {
                        bundle.putInt(key, (Integer) value);
                    } else if (value instanceof Boolean) {
                        bundle.putBoolean(key, (Boolean) value);
                    } else if (value instanceof Float) {
                        bundle.putFloat(key, (Float) value);
                    } else if (value instanceof Long) {
                        bundle.putLong(key, (Long) value);
                    } else if (value instanceof Double) {
                        bundle.putDouble(key, (Double) value);
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    void autoWrite(Activity activity){
        if (activity != null){
            String className = activity.getClass().getSimpleName();
            try {
                Class clazz = Class.forName(ExtraGenerate.getFullClassName(className));
                if (clazz != null){
                    ExtraAutoWriter extraAutoWriter = (ExtraAutoWriter) clazz.newInstance();
                    if (extraAutoWriter != null){
                        extraAutoWriter.write(activity);
                    }
                }
                clazz.newInstance();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            }
        }
    }

    private static class SingletonHolder {
        private static final RouterJumpEngine INSTANCE = new RouterJumpEngine();
    }
}
