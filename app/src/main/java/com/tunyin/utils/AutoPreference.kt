package com.tunyin.utils

import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

/**
 * 委托的SharedPreferences
 */

class AutoPreference<T>(val name: String, val default: T) : ReadWriteProperty<Any?, T> {


    override fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return get(name, default)
    }

    override fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        set(name, value)
    }

    private fun get(name: String, default: T): T = with(PreferenceUtils.sp) {
        val value: Any = when (default) {
            is Int -> getInt(name, default)
            is Float -> getFloat(name, default)
            is Long -> getLong(name, default)
            is Boolean -> getBoolean(name, default)
            is String -> getString(name, default)
            else -> { }
        }
        @Suppress("UNCHECKED_CAST")
        return value as T
    }

    private fun set(name: String, value: T) = PreferenceUtils.sp.edit().apply {
        when (value) {
            is Int -> putInt(name, value)
            is Float -> putFloat(name, value)
            is Long -> putLong(name, value)
            is Boolean -> putBoolean(name, value)
            is String -> putString(name, value)
            else -> { }
        }
    }.apply()
}
