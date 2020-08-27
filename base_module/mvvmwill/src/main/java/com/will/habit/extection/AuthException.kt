package com.will.habit.extection

class AuthException(val responseCode: String?,val responseMessage: String?) : RuntimeException(responseMessage)