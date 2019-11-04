package com.mpsi.laurent.randomselect

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters

@Database(entities = [Epreuve::class], version = 1)
@TypeConverters(Converters::class)
abstract class EpreuveDatabase : RoomDatabase() {

    abstract fun epreuveDao(): EpreuveDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: EpreuveDatabase? = null

        fun getDatabase(context: Context): EpreuveDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                        context.applicationContext,
                        EpreuveDatabase::class.java,
                        "database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }
}