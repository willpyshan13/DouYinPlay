package com.will.habit.extection

class PermissionException(val responseCode: Int, val responseMessage: String?) : RuntimeException(responseMessage)