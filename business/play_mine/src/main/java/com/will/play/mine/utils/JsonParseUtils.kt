package com.will.play.mine.utils

import android.content.Context
import android.content.res.AssetManager
import java.io.BufferedReader
import java.io.IOException
import java.io.InputStreamReader

object JsonParseUtils {
    @JvmStatic
    fun getJson(context: Context, fileName: String?): String? {
        val stringBuilder = StringBuilder()
        try {
            val assetManager: AssetManager = context.assets
            val bf = BufferedReader(InputStreamReader(
                    fileName?.let { assetManager.open(it) }))
            var line: String? = null
            while (bf.readLine().also { line = it } != null) {
                stringBuilder.append(line)
            }
        } catch (e: IOException) {
            e.printStackTrace()
        }
        return stringBuilder.toString()
    }
}