package com.android.example.util

import android.content.Context
import android.content.res.Configuration
import java.util.*

class LocaleManager {
    companion object {

        // For Singleton instantiation
        @Volatile
        private var instance: LocaleManager? = null

        fun getInstance(): LocaleManager {
            return instance ?: synchronized(this) {
                instance ?: LocaleManager().also { instance = it }
            }
        }
    }

    fun setLocaleWithCreateNewContext(context: Context, language: String): Context = updateResources(context, language)

    fun setLocaleWithUpdateConfiguration(context: Context, language: String) {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val resources = context.resources

        val configuration = resources.configuration
        configuration.setLocale(locale)

        resources.updateConfiguration(configuration, resources.displayMetrics)
    }

    private fun updateResources(context: Context, language: String): Context {
        val locale = Locale(language)
        Locale.setDefault(locale)

        val res = context.resources
        val config = Configuration(res.configuration)
        config.setLocale(locale)
        return context.createConfigurationContext(config)
    }
}