package com.will.habit.extection

import android.os.Looper
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.will.habit.R
import com.will.habit.http.BaseResponse
import com.will.habit.licycle.BaseLifecycleObserver
import com.will.habit.utils.StringUtils
import com.will.habit.utils.ToastUtils
import kotlinx.coroutines.*
import retrofit2.HttpException
import java.net.ConnectException
import java.net.HttpURLConnection
import java.net.SocketTimeoutException
import java.net.UnknownHostException


/**
 * 默认 -- 网络异常时是否toast
 */
private var GLOBAL_TOAST_NETWORK_ERROR = true
/**
 * 默认 -- 接口异常时是否toast
 */
private var GLOBAL_TOAST_RESPONSE_ERROR = true

/**
 * Desc: 检查BaseResponse是否合法
 * <p>
 */
fun <T> BaseResponse<T>.check(): T {
    if (isOk) {
        if (status.equals("300")||status.equals("500")){
            throw AuthException(status,error)
        }
        if (data != null) {
            return data!!
        }
        throw ResponseException(-1, "response data is null")
    }
    if (status == 36|| status == 46){
        throw PermissionException(status,error)
    }
    throw ResponseException(status, error)
}

/**
 * 拓展ViewModel，统一viewModelScope调用方式
 * @param block() 挂起函数
 * @param fail() 请求失败回调，可选参数，参数t为[ResponseException]时表示接口异常
 * @param toastNetWorkError 网络异常是否toast，可选参数，默认true
 * @param toastResponseError 接口返回code!=10000时是否toast，可选参数，默认true
 *
 * 栗子：
 * launch({
 *      // api请求
 * },{
 *      // t is ResponseException -> code!=10000
 * })
 */
fun ViewModel.launch(block: suspend (coroutineScope: CoroutineScope) -> Unit,
                     fail: (t: Throwable) -> Unit = { },
                     toastNetWorkError: Boolean = GLOBAL_TOAST_NETWORK_ERROR,
                     toastResponseError: Boolean = GLOBAL_TOAST_RESPONSE_ERROR) =
        viewModelScope.safeLaunch(block, fail, toastNetWorkError, toastResponseError)

/**
 * 统一catch viewModelScope
 */
fun CoroutineScope.safeLaunch(block: suspend (coroutineScope: CoroutineScope) -> Unit,
                              fail: (t: Throwable) -> Unit = { },
                              toastNetWorkError: Boolean = true,
                              toastResponseError: Boolean = true): Job {
    val exceptionHandler = CoroutineExceptionHandler { _, exception ->
        val isMainThread = Looper.getMainLooper().thread == Thread.currentThread()
        exception.printStackTrace()
        if (isMainThread && toastNetWorkError) {
            checkToastNetWorkError(exception)
        }
        if (isMainThread && toastResponseError) {
            checkToastResponseError(exception)
        }
        fail(exception)
    }
    return launch(exceptionHandler) { block(this) }
}

fun <T> CoroutineScope.safeAsync(block: suspend () -> T,
                                 fail: (t: Throwable) -> Unit = { },
                                 toastNetWorkError: Boolean = true,
                                 toastResponseError: Boolean = true) = this.async {
    var result: T? = null
    try {
        result = block()
    } catch (t: Throwable) {
        t.printStackTrace()
        if (toastNetWorkError) {
            checkToastNetWorkError(t)
        }
        if (toastResponseError) {
            checkToastResponseError(t)
        }
        fail(t)
    }
    result
}

/**
 * Desc: LifecycleOwner可使用这个launch
 * <p>
 * Date: 2019/9/17
 */
fun LifecycleOwner.launch(block: suspend (coroutineScope: CoroutineScope) -> Unit,
                          fail: (t: Throwable) -> Unit = { },
                          toastNetWorkError: Boolean = true,
                          toastResponseError: Boolean = true): Job {
    val coroutineScope = CloseableCoroutineScope(SupervisorJob() + Dispatchers.Main)
    val job = coroutineScope.safeLaunch(block, fail, toastNetWorkError, toastResponseError)

    object : BaseLifecycleObserver(this) {
        override fun onDestroy() {
            super.onDestroy()
            coroutineScope.close()
        }
    }
    return job
}

/**
 * Desc: 接口返回 code!=10000 时toast
 * <p>
 */
fun checkToastResponseError(t: Throwable) {
    if (t is ResponseException) {
        if (t.responseMessage != null) {
            ToastUtils.showShort(t.responseMessage)
        }
    }
}

/**
 * Desc: 网络异常toast
 * <p>
 */
fun checkToastNetWorkError(t: Throwable) {
    when (t) {
        is SocketTimeoutException -> ToastUtils.showShort(StringUtils.getStringResource(R.string.network_error_timeout))
        is ConnectException -> ToastUtils.showShort(StringUtils.getStringResource(R.string.network_error_timeout))
        is UnknownHostException -> ToastUtils.showShort(StringUtils.getStringResource(R.string.network_error_timeout))
        // http status code
        is HttpException -> {
            if (t.code() == HttpURLConnection.HTTP_UNAUTHORIZED) {
                ToastUtils.showShort(StringUtils.getStringResource(R.string.network_error_login))
            } else {
                ToastUtils.showShort(StringUtils.getStringResource(R.string.network_error_timeout))
            }
        }
    }
}


/**
 * Desc: 设置网络异常时默认是否toast
 * <p>
 */
fun setGlobalToastNetworkError(globalToastNetworkError:Boolean){
    GLOBAL_TOAST_NETWORK_ERROR = globalToastNetworkError
}

/**
 * Desc: 设置接口异常时默认是否toast
 * <p>
 */
fun setGlobalToastResponseError(globalToastResponseError:Boolean){
    GLOBAL_TOAST_RESPONSE_ERROR = globalToastResponseError
}