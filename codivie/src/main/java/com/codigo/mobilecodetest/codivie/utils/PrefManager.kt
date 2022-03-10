package com.codigo.mobilecodetest.codivie.utils

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

private const val KEY_SAVED_AT = "key_saved_at"

class PrefManager(context: Context) {

    private val appContext = context.applicationContext

    private val preference: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    fun setLastFetchedTime(savedAt: Long) {
        preference.edit().putLong(
            KEY_SAVED_AT,
            savedAt
        ).apply()
    }

    fun getLastFetchedTime(): Long {
        return preference.getLong(KEY_SAVED_AT, 0)
    }
}