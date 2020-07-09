package com.will.router;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import android.app.Activity;

/**
 * Desc:路由跳转提供外部调用类
 * <p>
 * @Author: will
 */
public class Router {

    private static Context globalContext;

    private String scheme;

    public Router init(Context c, String scheme){
        globalContext = c;
        this.scheme = scheme;
        return this;
    }



    private Router() {
    }

    public static Router getInstance() {
        return SingletonHolder.INSTANCE;
    }

    public void addIntercepter(String url, RouterInterceptor interceptor){
        RouterJumpEngine.getInstance().add(url, interceptor);
    }

    public RouterBuilder builder(String url){
        return new RouterBuilder().withUrl(url);
    }

    public RouterBuilder builder(String group, String path){
        return new RouterBuilder().withUrl(group, path);
    }

    public void startActivitys(RouterBuilder[] routerBuilders){
        RouterJumpEngine.getInstance().goActivitys(globalContext, routerBuilders);
    }

    public void startActivitys(Context context, RouterBuilder[] routerBuilders){
        RouterJumpEngine.getInstance().goActivitys(context, routerBuilders);
    }

    public void enableToast(boolean enable){
        RouterJumpEngine.getInstance().setEnableToast(enable);
    }

    public void inject(Activity activity){
        RouterJumpEngine.getInstance().autoWrite(activity);
    }


    private static class SingletonHolder {
        private static final Router INSTANCE = new Router();
    }


    /**
     * 构建者
     */
    public class RouterBuilder{
         String url;
         Bundle bundle;
         String group;
         String path;
         boolean allowInterceptor = true;
         List<Integer> intentExtra;
         int enterAnim = -1;
         int outerAnim = -1;

         RouterBuilder(){ }

        RouterBuilder withUrl(String url){
             if (!url.contains("://")){
                 url = scheme + "://" + url;
             }
            this.url = url;
            return this;
        }

        RouterBuilder withUrl(String group, String path){
            this.group = group;
            this.path = path;
            this.url = scheme + "://" + group + path;
            return this;
        }


        public RouterBuilder withInt(String key, int value){
            getBundle().putInt(key, value);
            return this;
        }

        public RouterBuilder withString(String key, String value){
            getBundle().putString(key, value);
            return this;
        }

        public RouterBuilder withFloat(String key, float value){
            getBundle().putFloat(key, value);
            return this;
        }

        public RouterBuilder withLong(String key, long value){
            getBundle().putLong(key, value);
            return this;
        }

        public RouterBuilder withBoolean(String key, boolean value){
            getBundle().putBoolean(key, value);
            return this;
        }

        public RouterBuilder withDouble(String key, double value){
            getBundle().putDouble(key, value);
            return this;
        }

        public RouterBuilder withParcelable(String key, Parcelable parcelable){
             getBundle().putParcelable(key, parcelable);
             return this;
        }

        public RouterBuilder withParcelableArray(String key, Parcelable[] parcelable){
            getBundle().putParcelableArray(key, parcelable);
            return this;
        }

        public RouterBuilder withParcelableArrayList(String key, ArrayList<? extends Parcelable> parcelable){
            getBundle().putParcelableArrayList(key, parcelable);
            return this;
        }


        public RouterBuilder withSerializable(String key, Serializable serializable){
            getBundle().putSerializable(key, serializable);
            return this;
        }

        public RouterBuilder withStringArray(String key, String[] strings){
            getBundle().putStringArray(key, strings);
            return this;
        }

        public RouterBuilder withStringArrayList(String key, ArrayList<String> strings){
            getBundle().putStringArrayList(key, strings);
            return this;
        }

        public RouterBuilder withIntArray(String key, int[] ints){
            getBundle().putIntArray(key, ints);
            return this;
        }

        public RouterBuilder withIntegerArrayList(String key, ArrayList<Integer> integers){
            getBundle().putIntegerArrayList(key, integers);
            return this;
        }

        public RouterBuilder withFloatArray(String key, float[] floats){
            getBundle().putFloatArray(key, floats);
            return this;
        }

        public RouterBuilder withDoubleArray(String key, double[] doubles){
            getBundle().putDoubleArray(key, doubles);
            return this;
        }

        public RouterBuilder disAllowInterceptor(){
            this.allowInterceptor = false;
            return this;
        }

        public RouterBuilder addIntentExtra(int extra){
             if (intentExtra == null){
                 this.intentExtra = new ArrayList<>();
             }
             intentExtra.add(extra);
             return this;
        }


        String getUrl(){
            return url;
        }

        public RouterBuilder withExtras(Bundle bundle){
            this.bundle = bundle;
            return this;
        }


        Bundle getBundle(){
            if (bundle == null){
                bundle = new Bundle();
                if (scheme != null){
                    bundle.putString("scheme", scheme);
                }
            }
            return bundle;
        }

        public Intent getIntent(Context context){
            return RouterJumpEngine.getInstance().getIntent(context, this);
        }


        public void startActivity(){
            RouterJumpEngine.getInstance().go(globalContext,this);
        }

        public void startActivity(Context context){
            RouterJumpEngine.getInstance().go(context,this);
        }


        public void startActivityWithAnim(Activity activity, int enterAnim, int outerAnim){
             this.enterAnim = enterAnim;
             this.outerAnim = outerAnim;
             RouterJumpEngine.getInstance().go(activity, this);
        }

        public void startActivityForResultWithAnim(Activity activity, int requestCode, int enterAnim, int outerAnim){
            this.enterAnim = enterAnim;
            this.outerAnim = outerAnim;
            RouterJumpEngine.getInstance().goForResult(activity, this, requestCode);
        }

        public void startActivityForResult(Activity activity, int requestCode){
            RouterJumpEngine.getInstance().goForResult(activity,this, requestCode);
        }

        public void startActivityForResult(Fragment fragment, int requestCode){
            RouterJumpEngine.getInstance().goForResult(fragment,this, requestCode);
        }
    }


}
