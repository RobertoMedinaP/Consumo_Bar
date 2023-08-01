package com.example.consumo_bar.Modelo

import androidx.lifecycle.LiveData
import com.example.consumo_bar.Modelo.Model.Consumo
import com.example.consumo_bar.Modelo.Model.ConsumoDao


class ConsumoRepository (private val consumoDao: ConsumoDao){

    val listAllConsumo: LiveData<List<Consumo>> = consumoDao.getAllConsumo()

    suspend fun insertarConsumo(consumo: Consumo){
        consumoDao.insertarConsumo(consumo)
    }

    suspend fun updateConsumo(consumo: Consumo){
        consumoDao.updateConsumo(consumo)
    }

    suspend fun deleteAll(){
        consumoDao.deleteAll()
    }

    suspend fun deleteUnConsumo(consumo: Consumo){
        consumoDao.deleteUnConsumo(consumo)
    }
}