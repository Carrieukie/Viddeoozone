package com.karis.videoozone.data.room

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.karis.videoozone.model.Snippet
import com.karis.videoozone.model.Statistics
import javax.inject.Inject


class Converters @Inject constructor(){

    var gson: Gson = Gson()

    @TypeConverter
    fun fromSnippet(snippet: Snippet): String {
        return gson.toJson(snippet)
    }

    @TypeConverter
    fun toSnippet(snippet: String): Snippet? {
        return gson.fromJson(snippet,Snippet::class.java)
    }

    @TypeConverter
    fun fromStatistics(statistics: Statistics):  String{
        return gson.toJson(statistics)
    }

    @TypeConverter
    fun toStatistics(statistics: String): Statistics {
        return gson.fromJson(statistics,Statistics::class.java)
    }
}