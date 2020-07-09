package com.will.habit.utils

import java.io.Closeable
import java.io.IOException

/**
 * @author will
 * 关闭相关工具类
 */
class CloseUtils private constructor() {
    companion object {
        /**
         * 关闭IO
         *
         * @param closeables closeables
         */
        @JvmStatic
        fun closeIO(vararg closeables: Closeable?) {
            for (closeable in closeables) {
                if (closeable != null) {
                    try {
                        closeable.close()
                    } catch (e: IOException) {
                        e.printStackTrace()
                    }
                }
            }
        }

        /**
         * 安静关闭IO
         *
         * @param closeables closeables
         */
        fun closeIOQuietly(vararg closeables: Closeable?) {
            for (closeable in closeables) {
                if (closeable != null) {
                    try {
                        closeable.close()
                    } catch (ignored: IOException) {
                    }
                }
            }
        }
    }

    init {
        throw UnsupportedOperationException("u can't instantiate me...")
    }
}