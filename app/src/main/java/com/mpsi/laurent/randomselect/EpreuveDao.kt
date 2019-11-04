package com.mpsi.laurent.randomselect

import androidx.lifecycle.LiveData
import androidx.room.*


@Dao
interface EpreuveDao {
    @Query("SELECT * FROM epreuve")
    fun getAll(): LiveData<List<Epreuve>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(vararg epreuves: Epreuve)

    @Delete
    suspend fun delete(vararg epreuves: Epreuve)
}