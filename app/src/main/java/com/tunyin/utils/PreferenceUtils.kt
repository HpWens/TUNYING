package com.tunyin.utils

import android.content.Context
import android.content.SharedPreferences
import com.tunyin.App

/**
 * SP工具类
 */

object PreferenceUtils {

    val sp: SharedPreferences by lazy {
        App.instance.getSharedPreferences("user", Context.MODE_PRIVATE)
    }

    fun get(name: String, default: Any): Any = with(sp) {
        return when (default) {
            is Int -> getInt(name, default)
            is Float -> getFloat(name, default)
            is Long -> getLong(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> throw Exception("This type can be get from Preferences")
        }
    }

    fun put(name: String, value: Any) = sp.edit().apply {
        when (value) {
            is Int -> putInt(name, value)
            is Float -> putFloat(name, value)
            is Long -> putLong(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> throw Exception("This type can be saved into Preferences")
        }
    }.apply()

    fun clean() {
        sp.edit().clear().apply()
    }
}
