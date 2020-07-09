package com.will.habit.extection

class ResponseException(val responseCode: Int,val responseMessage: String?) : RuntimeException(responseMessage)