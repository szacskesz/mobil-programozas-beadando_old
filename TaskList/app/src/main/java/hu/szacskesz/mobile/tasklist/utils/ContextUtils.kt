package hu.szacskesz.mobile.tasklist.utils

import android.content.Context
import android.content.ContextWrapper
import android.content.res.Configuration
import android.content.res.Resources
import android.os.Build

import android.os.LocaleList
import java.util.Locale


class ContextUtils(base: Context?) : ContextWrapper(base) {
    companion object {
        fun updateLocale(ctx: Context, localeToSwitchTo: Locale): ContextWrapper {
            var context = ctx
            val resources: Resources = context.resources
            val configuration: Configuration = resources.configuration

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                val localeList = LocaleList(localeToSwitchTo)
                LocaleList.setDefault(localeList)
                configuration.setLocales(localeList)
            } else {
                @Suppress("DEPRECATION") //For older phones this is the way
                configuration.locale = localeToSwitchTo
            }

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1) {
                context = context.createConfigurationContext(configuration)
            } else {
                @Suppress("DEPRECATION") //For older phones this is the way
                resources.updateConfiguration(configuration, resources.displayMetrics)
            }
            return ContextUtils(context)
        }
    }
}
