package com.will.habit.base;

import androidx.annotation.CallSuper;
import androidx.annotation.MainThread;
import androidx.annotation.Nullable;

import java.io.Closeable;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

/**
 * Desc: 支持协程调用
 * <p>
 */
public abstract class BaseScopeModel implements IModel {

    @Nullable
    private final Map<String, Object> mBagOfTags = new HashMap<>();
    private volatile boolean mCleared = false;

    @CallSuper
    @Override
    public void onCleared() {
        clearTags();
    }

    @MainThread
    private final void clearTags() {
        mCleared = true;
        synchronized (mBagOfTags) {
            for (Object value : mBagOfTags.values()) {
                // see comment for the similar call in setTagIfAbsent
                closeWithRuntimeException(value);
            }
        }
    }

    @SuppressWarnings("unchecked")
    public <T> T setTagIfAbsent(String key, T newValue) {
        T previous;
        synchronized (mBagOfTags) {
            previous = (T) mBagOfTags.get(key);
            if (previous == null) {
                mBagOfTags.put(key, newValue);
            }
        }
        T result = previous == null ? newValue : previous;
        if (mCleared) {
            // It is possible that we'll call close() multiple times on the same object, but
            // Closeable interface requires close method to be idempotent:
            // "if the stream is already closed then invoking this method has no effect." (c)
            closeWithRuntimeException(result);
        }
        return result;
    }

    @SuppressWarnings({"TypeParameterUnusedInFormals", "unchecked"})
    public <T> T getTag(String key) {
        synchronized (mBagOfTags) {
            return (T) mBagOfTags.get(key);
        }
    }

    public static void closeWithRuntimeException(Object obj) {
        if (obj instanceof Closeable) {
            try {
                ((Closeable) obj).close();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
