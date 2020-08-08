package com.will.habit.http

/**
 *
 */
class BaseResponse<T> {
    @JvmField
    var code = 0
    @JvmField
    var error: String? = null

    var result: T? = null
        private set

    fun setResult(result: T) {
        this.result = result
    }

    val isOk: Boolean
        get() = code == 0

}