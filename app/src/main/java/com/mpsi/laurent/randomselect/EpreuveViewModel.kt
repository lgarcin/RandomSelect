package com.mpsi.laurent.randomselect

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class EpreuveViewModel(application: Application) : AndroidViewModel(application) {
    private val epreuveRepository = EpreuveRepository(application)
    private val allEpreuves: LiveData<List<Epreuve>>? = epreuveRepository.getAll()

    fun getAll(): LiveData<List<Epreuve>>? {
        return allEpreuves
    }

    fun insert(vararg epreuves: Epreuve) = viewModelScope.launch {
        epreuveRepository.insert(*epreuves)
    }

    fun delete(vararg epreuves: Epreuve) = viewModelScope.launch {
        epreuveRepository.delete(*epreuves)
    }
}