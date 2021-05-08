package com.prueba.andyrios.util

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

fun currentDate(): Date {
    val calendar = Calendar.getInstance()
    return calendar.time
}

fun isConnected(context: Context): Boolean {
    val cm = context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
    val activeNetwork: NetworkInfo? = cm.activeNetworkInfo
    return activeNetwork?.isConnectedOrConnecting == true
}

fun convertStringtoDate(stringDate: String): Date{
    val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    return sdf.parse(stringDate)
}

fun getTimeAgo(stringDate: String): String {
    val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
    val date = sdf.parse(stringDate)

    val SECOND_MILLIS = 1000
    val MINUTE_MILLIS = 60 * SECOND_MILLIS
    val HOUR_MILLIS = 60 * MINUTE_MILLIS
    val DAY_MILLIS = 24 * HOUR_MILLIS

    var time = date.time
    if (time < 1000000000000L) {
        time *= 1000
    }

    val now = currentDate().time
    if (time > now || time <= 0) {
        return "in the future"
    }

    val diff = now - time
    return when {
        diff < MINUTE_MILLIS -> "moments ago"
        diff < 2 * MINUTE_MILLIS -> "1m"
        diff < 60 * MINUTE_MILLIS -> "${diff / MINUTE_MILLIS}m"
        diff < 2 * HOUR_MILLIS -> "1h"
        diff < 24 * HOUR_MILLIS -> "${diff / HOUR_MILLIS}h"
        diff < 48 * HOUR_MILLIS -> "yesterday"
        else -> "${diff / DAY_MILLIS} days ago"
    }
}

    fun getDatetoLong(stringDate: String): Long {
        val sdf: DateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSXXX")
        val date = sdf.parse(stringDate)

        val SECOND_MILLIS = 1000
        val MINUTE_MILLIS = 60 * SECOND_MILLIS
        val HOUR_MILLIS = 60 * MINUTE_MILLIS
        val DAY_MILLIS = 24 * HOUR_MILLIS

        var time = date.time
        if (time < 1000000000000L) {
            time *= 1000
        }

        val now = currentDate().time
        if (time > now || time <= 0) {
            return 0
        }

        val diff = now - time
        return diff
    }