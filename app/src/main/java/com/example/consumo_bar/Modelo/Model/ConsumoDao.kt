package com.example.consumo_bar.Modelo.Model

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.consumo_bar.Modelo.Model.Consumo


@Dao
interface ConsumoDao {

    //ya que insertare a traves de campo de textos, solo hare de a una inserción

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertarConsumo(consumo: Consumo)

    @Update
    suspend fun updateConsumo(consumo: Consumo)

    //el ejercicio solo pide borrar el registro completo

    @Query("DELETE FROM TABLA_CONSUMO")
    suspend fun deleteAll()

    @Query("SELECT * FROM tabla_consumo")
    fun getAllConsumo():LiveData<List<Consumo>>

    //por si voy a dejar la opcion de borrar un solo consumo

    @Delete
    suspend fun deleteUnConsumo(consumo: Consumo)



}