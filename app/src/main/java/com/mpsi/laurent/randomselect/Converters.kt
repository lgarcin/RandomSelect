package com.mpsi.laurent.randomselect

import androidx.room.TypeConverter
import java.time.LocalDate

class Converters {
    companion object {
        @TypeConverter
        @JvmStatic
        fun serialize(list: List<String>?): String? {
            return list?.joinToString(", ")
        }

        @TypeConverter
        @JvmStatic
        fun deserialize(s: String?): List<String>? {
            return s?.split(", ")
        }

        @TypeConverter
        @JvmStatic
        fun fromDatestamp(value: Long?): LocalDate? {
            return if (value == null) null else LocalDate.ofEpochDay(value)
        }

        @TypeConverter
        @JvmStatic
        fun toDatestamp(date: LocalDate?): Long? {
            return date?.toEpochDay()
        }
    }
}