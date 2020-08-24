package com.will.habit.extection

class AuthException(val responseCode: Int,val responseMessage: String?) : RuntimeException(responseMessage)