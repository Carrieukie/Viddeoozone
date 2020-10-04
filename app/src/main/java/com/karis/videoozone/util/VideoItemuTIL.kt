package com.karis.videoozone.util

import android.util.Log
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


object VideoItemuTIL {

    fun convertViews(views: Long): String? {
        if (views < 1000) return "" + views
        val exp = (Math.log(views.toDouble()) / Math.log(1000.0)).toInt()
        return String.format("%.1f %c", views / Math.pow(1000.0, exp.toDouble()), "kMGTPE"[exp - 1])
    }


    fun covertTimeToText(dataDate: String?): String? {
        var convTime: String? = null
        val prefix = ""
        try {
            val dateFormat = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
            val pasTime = dateFormat.parse(dataDate)
            val nowTime = Date()
            val dateDiff = nowTime.time - pasTime.time
            val second: Long = TimeUnit.MILLISECONDS.toSeconds(dateDiff)
            val minute: Long = TimeUnit.MILLISECONDS.toMinutes(dateDiff)
            val hour: Long = TimeUnit.MILLISECONDS.toHours(dateDiff)
            val day: Long = TimeUnit.MILLISECONDS.toDays(dateDiff)
            if (second < 60) {
                convTime = "$second Seconds "
            } else if (minute < 60) {
                convTime = "$minute Minutes "
            } else if (hour < 24) {
                convTime = "$hour Hours "
            } else if (day >= 7) {
                convTime = if (day > 360) {
                    (day / 360).toString() + " Years "
                } else if (day > 30) {
                    (day / 30).toString() + " Months "
                } else {
                    (day / 7).toString() + " Week "
                }
            } else if (day < 7) {
                convTime = if (day == 1L) "$day Day " else "$day Days "
            }
        } catch (e: ParseException) {
            e.printStackTrace()
            Log.e("ConvTimeE", e.message)
        }
        return convTime
    }


}