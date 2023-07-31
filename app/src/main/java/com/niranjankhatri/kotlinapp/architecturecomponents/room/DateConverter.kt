package com.niranjankhatri.kotlinapp.architecturecomponents.room

import androidx.room.TypeConverter
import java.util.*

class DateConverter {

    @TypeConverter
    fun from(value: Long?): Date?{
        return value?.let {
            Date(it)
        }
    }

    @TypeConverter
    fun to(date:Date?): Long?{
        return date?.time
    }

}