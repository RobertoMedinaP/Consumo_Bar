package com.example.consumo_bar.ViewModel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.example.consumo_bar.Modelo.ConsumoRepository
import com.example.consumo_bar.Modelo.Model.Consumo
import com.example.consumo_bar.Modelo.Model.ConsumoDatabase
import kotlinx.coroutines.launch


class ConsumoViewModel(application: Application):AndroidViewModel(application) {

    private val repository: ConsumoRepository

    val todoelconsumo: LiveData<List<Consumo>>

    init {
        val ConsumoDao=ConsumoDatabase.getDatabase(application).getConsumoDao()
        repository= ConsumoRepository(ConsumoDao)

        todoelconsumo=repository.listAllConsumo
    }

    //a lanzar lo del repositrio

    fun insertConsumo(consumo: Consumo)=viewModelScope.launch {
        repository.insertarConsumo(consumo)
    }

    fun updateConsumo(consumo: Consumo)=viewModelScope.launch {
        repository.updateConsumo(consumo)
    }

    fun deleteAllConsumo()=viewModelScope.launch {
        repository.deleteAll()
    }

    fun deleteOneConsumo(consumo: Consumo)=viewModelScope.launch {
        repository.deleteUnConsumo(consumo)
    }

    /** para seleccionar un elemento de la lista
     *
     */

    private val consumoSeleccionado: MutableLiveData<Consumo?> = MutableLiveData()

    fun consumoSeleccionado(): LiveData<Consumo?> = consumoSeleccionado

    fun seleccionado(consumo: Consumo){
        consumoSeleccionado.value=consumo
    }
}