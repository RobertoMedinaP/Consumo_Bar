package com.example.consumo_bar.Modelo.Model

import androidx.room.Entity
import androidx.room.PrimaryKey
import org.jetbrains.annotations.NotNull


@Entity(tableName = "tabla_consumo")
data class Consumo (

    @PrimaryKey(autoGenerate = true)
    @NotNull
    val id: Int=0,
    val producto: String,
    val precio: Int,
    val cantidad: Int,
    val total: Int

)