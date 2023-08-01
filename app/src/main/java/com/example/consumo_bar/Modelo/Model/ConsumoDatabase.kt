package com.example.consumo_bar.Modelo.Model

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase


@Database(entities = [Consumo::class], version = 1)//no es necesario definir si exporto esquema
abstract class ConsumoDatabase: RoomDatabase() {

    abstract fun getConsumoDao(): ConsumoDao


    companion object {
        // ESTA VARIABLE ESTE SIEMPRE DISPONIBLE
        @Volatile
        private var INSTANCE: ConsumoDatabase? = null


        fun getDatabase(context: Context): ConsumoDatabase {
            val tempInntance = INSTANCE
            if (tempInntance != null) {

                return tempInntance
            }


            synchronized(this) {
                val instance = Room.databaseBuilder(
                    // la bade datos sea una para toda la app
                    context.applicationContext,
                    ConsumoDatabase::class.java,
                    "ConsumoDB"
                )
                    .build()
                INSTANCE = instance
                return instance

            }
        }
    }

}

