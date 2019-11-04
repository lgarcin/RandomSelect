package com.mpsi.laurent.randomselect

import android.app.Application
import androidx.lifecycle.LiveData

class EpreuveRepository(application: Application) {
    private val db = EpreuveDatabase.getDatabase(application)
    private val epreuveDao: EpreuveDao? = db.epreuveDao()
    private var allEpreuves: LiveData<List<Epreuve>>? = epreuveDao?.getAll()

    fun getAll(): LiveData<List<Epreuve>>? {
        return allEpreuves
    }

    suspend fun insert(vararg epreuves: Epreuve) {
        epreuveDao?.insert(*epreuves)
    }

    suspend fun delete(vararg epreuves: Epreuve) {
        epreuveDao?.delete(*epreuves)
    }
}