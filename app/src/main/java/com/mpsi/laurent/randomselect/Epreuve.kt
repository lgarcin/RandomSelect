package com.mpsi.laurent.randomselect

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate

@Entity(tableName = "epreuve")
data class Epreuve(
        @PrimaryKey(autoGenerate = true) var id: Long = 0,
        @ColumnInfo(name = "nom") var nom: String,
        @ColumnInfo(name = "eleves") var eleves: List<String>,
        @ColumnInfo(name = "date") var date: LocalDate
)