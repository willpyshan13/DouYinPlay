package com.will.play.pay.callback

interface IAuthCallback {
    fun success(code:String)
    fun failed(code: Int, message: String?)
    fun cancel()
}