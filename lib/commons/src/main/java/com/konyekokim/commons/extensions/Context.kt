package com.konyekokim.commons.extensions

import android.content.Context
import android.content.res.Configuration

fun Context.isDarkTheme() = resources.configuration.uiMode and
        Configuration.UI_MODE_NIGHT_MASK == Configuration.UI_MODE_NIGHT_YES