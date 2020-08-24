package com.will.habit.http

/**
 *
 */
class BaseResponse<T> {
    @JvmField
    var code = 0
    @JvmField
    var status: String? = null

    @JvmField
    var error: String? = null

    var data: T? = null
        private set

    val isOk: Boolean
        get() = code == 0

}